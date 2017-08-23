import BasicMedicalInformation.Disease;
import BasicMedicalInformation.Medicine;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;


public class BasicMedicalInformationGUI {
    private JPanel mainpanel;
    private JTabbedPane tabbedPane1;
    private JPanel medicine;
    private JPanel treatment;
    private JPanel serviceFacilities;
    private JPanel fixedMedicalInstitution;
    private JPanel diseases;
    private JTextField medicineCoding;
    private JTextField ChineseName;
    private JTextField EnglishName;
    private JTextField dosageUnit;
    private JTextField maximumPrice;
    private JButton inquireMedicine;
    private JButton saveMedicine;
    private JButton deleteMedicine;
    private JButton addMedicine;
    private JComboBox chargeCategory;
    private JComboBox prescriptionMark;
    private JComboBox hospitalPreparationSigns;
    private JComboBox needApproval;
    private JComboBox hospitalGrade;
    private JComboBox feeLevel;
    private JTextField dosageForm;
    private JTextField frequency;
    private JTextField unit;
    private JTextField usage;
    private JTextField specification;
    private JTextField limitDays;
    private JTextField tradeName;
    private JTextField factory;
    private JTextField ChineseMedicineProspectiveWord;
    private JTextField remarks;
    private JTextField nationalCatelogCode;
    private JTextField limitUsage;
    private JTextField origin;
    private JButton resetMedicine;

    private JList medicineSearchResults;
    private DefaultListModel medicineListModel;

    private JTextField diseasesCoding;
    private JComboBox diseasesCategory;
    private JComboBox diseaseReimbursementSigns;
    private JTextField diseasesRemark;
    private JButton addDisease;
    private JButton inquireDisease;
    private JTextField diseasesName;

    private JList diseasesSearchResults;
    private JButton saveDisease;
    private JButton deleteDisease;
    private JButton resetDisease;
    private DefaultListModel diseaseListModel;

    private boolean isMedicineInquired = false;
    private boolean isDiseaseInquired = false;

    public static void main(String[] args) {
        JFrame frame = new JFrame("医疗基本信息维护");
        frame.setContentPane(new BasicMedicalInformationGUI().mainpanel);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void initMedicine() {
        ChineseName.setText("");
        EnglishName.setText("");
        chargeCategory.setSelectedIndex(0);
        prescriptionMark.setSelectedIndex(0);
        feeLevel.setSelectedIndex(0);
        dosageUnit.setText("");
        maximumPrice.setText("");
        hospitalPreparationSigns.setSelectedIndex(0);
        needApproval.setSelectedIndex(0);
        hospitalGrade.setSelectedIndex(0);
        dosageForm.setText("");
        frequency.setText("");
        usage.setText("");
        unit.setText("");
        specification.setText("");
        limitDays.setText("");
        tradeName.setText("");
        factory.setText("");
        ChineseMedicineProspectiveWord.setText("");
        remarks.setText("");
        nationalCatelogCode.setText("");
        limitUsage.setText("");
        origin.setText("");

        isMedicineInquired = false;

        medicineSearchResults.setModel(new DefaultListModel());
    }

    public BasicMedicalInformationGUI() {

        inquireMedicine.addMouseListener(new MouseAdapter() {//查询药品 按钮
            private boolean readFlag = false;

            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);  // 查询药品 按钮被按下
                // 通过 药品编码 或 药品名称 查询
                if (!medicineCoding.getText().equals(""))//如果编码非空
                {
                    Medicine data = new BasicMedicalInformation.Medicine();
                    try {
                        readFlag = data.readCSV(medicineCoding.getText());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "文件读写错误", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                    if (readFlag) {
                        MedicineToGUI(data);
                    } else // if (!readFlag)
                    {
                        JOptionPane.showMessageDialog(null, "未找到该药品", "警告", JOptionPane.WARNING_MESSAGE);
                        initMedicine();
                    }
                } else // 药品编码为空，尝试通过药品名称查找
                {
                    // 逐条读取，判断用户输入的名称是不是其子串，如果是则用toString将其展示在列表上；当选中列表的某一项时，执行readCSV
                    String item[] = new String[25];
                    BufferedReader reader = null;
                    try {
                        reader = new BufferedReader(new FileReader("data/Medicine.csv"));
                        reader.readLine();//第一行是标题
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "文件未找到", "错误", JOptionPane.ERROR_MESSAGE);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "文件读写错误", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                    String line;
                    medicineListModel = new DefaultListModel();
                    medicineSearchResults.setModel(new DefaultListModel<>());
                    try {
                        while ((line = reader.readLine()) != null) {
                            item = line.split(",");
                            if (item[1].contains(ChineseName.getText()))//判断文本框中的内容是否是item[1]的子串
                            {
                                Medicine medicine = new Medicine();
                                medicine.readCSV(item[0]);
                                // 展示到界面右侧列表
                                medicineListModel.addElement(medicine);
                                medicineSearchResults.setModel(medicineListModel);
                            }
                        }
                        reader.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "文件读写错误", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        addMedicine.addMouseListener(new MouseAdapter() {
            boolean writeFlag = true;

            @Override
            public void mouseClicked(MouseEvent e) {

                super.mouseClicked(e);  // 添加药品 按钮被按下

                if (isMedicineCompleted()) // 如果信息完整
                {
                    try {
                        Medicine data = new Medicine();
                        GUItoMedicine(data);
                        try {
                            writeFlag = data.writeCSV();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(null, "文件读写错误", "错误", JOptionPane.ERROR_MESSAGE);
                        }
                        if (!writeFlag) {// 在writeCSV方法中，coding已存在的药品不会被添加
                            JOptionPane.showMessageDialog(null, "该药品已存在", "警告", JOptionPane.WARNING_MESSAGE);
                        } else {
                            isMedicineInquired = true;
                            JOptionPane.showMessageDialog(null, "添加药品信息成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                            medicineSearchResults.setModel(new DefaultListModel());
                        }
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(null, "输入的数字格式有误", "警告", JOptionPane.WARNING_MESSAGE);
                    }
                } else  // 如果信息不完整
                {
                    JOptionPane.showMessageDialog(null, "请输入完整的信息", "警告", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        saveMedicine.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);  // 保存药品 按钮被按下
                // 先判断数据是否完整，如果完整，再判断该编码是否存在CSV中，如果存在则删除原数据、添加新数据
                if (!isMedicineCompleted()) //判断信息是否完整
                {
                    JOptionPane.showMessageDialog(null, "请输入完整的信息", "警告", JOptionPane.WARNING_MESSAGE);
                } else {
                    Medicine data = new Medicine();
                    GUItoMedicine(data);
                    Medicine findCSV = new Medicine();
                    try {
                        if (!findCSV.readCSV(medicineCoding.getText())) //判断当前编码是否存在于CSV
                        {
                            JOptionPane.showMessageDialog(null, "未找到该药品", "错误", JOptionPane.ERROR_MESSAGE);
                        } else { //删除原数据、添加新数据
                            File file = new File("data/Medicine.csv");
                            File temp = new File("data/Medicine.temp.csv");
                            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(temp)));
                            String line;
                            String[] item;
                            while ((line = reader.readLine()) != null) {
                                item = line.split(",");
                                if (!item[0].equals(medicineCoding.getText()))
                                    writer.write(line + "\n");
                            }
                            reader.close();
                            writer.close();
                            file.delete();
                            temp.renameTo(file);

                            data.writeCSV();
                            isMedicineInquired = true;
                            medicineSearchResults.setModel(new DefaultListModel());
                            JOptionPane.showMessageDialog(null, "保存成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "文件读写错误", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        deleteMedicine.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);  // 删除药品 按钮被按下
                //readCSV，先查询，后删除
                if (!isMedicineInquired)
                    JOptionPane.showMessageDialog(null, "请指定一种药品", "警告", JOptionPane.WARNING_MESSAGE);
                else // if(isMedicineInquired)
                {
                    if (JOptionPane.showConfirmDialog(null, "确定删除？", "确认", JOptionPane.YES_NO_OPTION) == 0)//用户点击 确定
                    {
                        File file = new File("data/Medicine.csv");
                        File temp = new File("data/Medicine.temp.csv");
                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(temp)));
                            String line;
                            String[] item;
                            while ((line = reader.readLine()) != null) {
                                item = line.split(",");
                                if (!item[0].equals(medicineCoding.getText()))
                                    writer.write(line + "\n");
                            }
                            reader.close();
                            writer.close();
                            file.delete();
                            temp.renameTo(file);
                            isMedicineInquired = false;
                            medicineCoding.setText("");
                            initMedicine();
                            JOptionPane.showMessageDialog(null, "删除成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                        } catch (FileNotFoundException e1) {
                            JOptionPane.showMessageDialog(null, "文件未找到", "错误", JOptionPane.ERROR_MESSAGE);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(null, "文件读写错误", "错误", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    //若用户点击“取消”，无需任何动作
                }
            }
        });

        resetMedicine.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);  // 重置按钮被按下
                initMedicine();
                medicineCoding.setText("");
            }
        });

        medicineCoding.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            // 药品编码文本框 内容被修改
            @Override
            public void insertUpdate(DocumentEvent e) {
                isMedicineInquired = false;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                isMedicineInquired = false;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                isMedicineInquired = false;
            }
        });

        medicineSearchResults.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) // 列表元素被选中
            {
                Medicine data;
                if ((data = (Medicine) medicineSearchResults.getSelectedValue()) != null)
                    MedicineToGUI(data);
            }
        });

        inquireDisease.addMouseListener(new MouseAdapter() {// 查询病种 按钮
            private boolean readFlag = false;

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);// 查询病种 按钮被按下
                //通过 疾病编码 或 病种名称 查询
                if (!diseasesCoding.getText().equals(""))//如果编码非空
                {
                    Disease data = new Disease();
                    try {
                        readFlag = data.readCSV(diseasesCoding.getText());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "文件读写错误", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                    if (readFlag) {
                        DiseaseToGUI(data);
                    } else // if(!readFlag)
                    {
                        JOptionPane.showMessageDialog(null, "未找到该病种信息", "警告", JOptionPane.WARNING_MESSAGE);
                        initDisease();
                    }
                } else // 疾病编码为空，尝试通过疾病名称查找
                {
                    String item[];
                    BufferedReader reader = null;
                    try {
                        reader = new BufferedReader(new FileReader("data/Disease.csv"));
                        reader.readLine();//第一行是标题
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "文件未找到", "错误", JOptionPane.ERROR_MESSAGE);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "文件读写错误", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                    String line;
                    diseaseListModel = new DefaultListModel();
                    diseasesSearchResults.setModel(new DefaultListModel());
                    try {
                        while ((line = reader.readLine()) != null) {
                            item = line.split(",");
                            if (item[1].contains(diseasesName.getText()))//判断文本框中的内容是否是item[1]的子串
                            {
                                Disease disease = new Disease();
                                disease.readCSV(item[0]);
                                //展示到界面右侧列表
                                diseaseListModel.addElement(disease);
                                diseasesSearchResults.setModel(diseaseListModel);
                            }
                        }
                        reader.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "文件读写错误", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        addDisease.addMouseListener(new MouseAdapter() {
            boolean writeFlag = true;

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);//添加病种 按钮被按下
                if (isDiseaseCompleted()) //如果信息完整
                {
                    try {
                        Disease data = new Disease();
                        GUItoDisease(data);
                        writeFlag = data.writeCSV();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "文件读写错误", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                    if (!writeFlag) {//在writeCSV方法中，coding已存在的病种不会被添加
                        JOptionPane.showMessageDialog(null, "该病种已存在", "警告", JOptionPane.WARNING_MESSAGE);
                    } else {
                        isDiseaseInquired = true;
                        JOptionPane.showMessageDialog(null, "添加病种信息成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                        diseasesSearchResults.setModel(new DefaultListModel());
                    }
                } else // 如果信息不完整
                {
                    JOptionPane.showMessageDialog(null, "请输入完整的信息", "警告", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        saveDisease.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);// 保存病种 按钮被按下
                if (!isDiseaseCompleted()) {
                    JOptionPane.showMessageDialog(null, "请输入完整的信息", "警告", JOptionPane.WARNING_MESSAGE);
                } else {
                    Disease data = new Disease();
                    GUItoDisease(data);
                    Disease findCSV = new Disease();
                    try {
                        if (!findCSV.readCSV(diseasesCoding.getText())) {
                            JOptionPane.showMessageDialog(null, "未找到该药品", "错误", JOptionPane.ERROR_MESSAGE);
                        } else {
                            File file = new File("data/Disease.csv");
                            File temp = new File("data/Disease.temp.csv");
                            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(temp)));
                            String line;
                            String[] item;
                            while ((line = reader.readLine()) != null) {
                                item = line.split(",");
                                if (!item[0].equals(diseasesCoding.getText()))
                                    writer.write(line + "\n");
                            }
                            reader.close();
                            writer.close();
                            file.delete();
                            temp.renameTo(file);

                            data.writeCSV();
                            isDiseaseInquired = true;
                            diseasesSearchResults.setModel(new DefaultListModel());
                            JOptionPane.showMessageDialog(null, "保存成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "文件读写错误", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });


        deleteDisease.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);// 删除病种 按钮被按下
                if (!isDiseaseInquired)
                    JOptionPane.showMessageDialog(null, "请指定病种", "警告", JOptionPane.WARNING_MESSAGE);
                else {
                    if (JOptionPane.showConfirmDialog(null, "确定删除？", "确认", JOptionPane.YES_NO_OPTION) == 0)//用户点击 确定
                    {
                        File file = new File("data/Disease.csv");
                        File temp = new File("data/Disease.temp.csv");
                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(temp)));
                            String line;
                            String[] item;
                            while ((line = reader.readLine()) != null) {
                                item = line.split(",");
                                if (!item[0].equals(diseasesCoding.getText()))
                                    writer.write(line + "\n");
                            }
                            reader.close();
                            writer.close();
                            file.delete();
                            temp.renameTo(file);
                            isDiseaseInquired = false;
                            diseasesCoding.setText("");
                            initDisease();
                            JOptionPane.showMessageDialog(null, "删除成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                        } catch (FileNotFoundException e1) {
                            JOptionPane.showMessageDialog(null, "文件未找到", "错误", JOptionPane.ERROR_MESSAGE);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(null, "文件读写错误", "错误", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        resetDisease.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);  // 重置病种 按钮被按下
                initDisease();
                diseasesCoding.setText("");
            }
        });

        diseasesCoding.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            // 病种编码文本框 内容被修改
            @Override
            public void insertUpdate(DocumentEvent e) {
                isDiseaseInquired = false;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                isDiseaseInquired = false;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                isDiseaseInquired = false;
            }
        });

        diseasesSearchResults.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) // 列表元素被选中
            {
                Disease data;
                if ((data = (Disease) diseasesSearchResults.getSelectedValue()) != null)
                    DiseaseToGUI(data);
            }
        });
    }

    public void MedicineToGUI(Medicine data) {
        medicineCoding.setText(data.getCoding());
        ChineseName.setText(data.getChineseName());
        EnglishName.setText(data.getEnglishName());
        chargeCategory.setSelectedIndex(data.getChargeCategory() + 1);
        prescriptionMark.setSelectedIndex(data.getPrescriptionMark() + 1);
        feeLevel.setSelectedIndex(data.getFeeLevel() + 1);
        dosageUnit.setText(data.getDosageUnit());
        maximumPrice.setText(Double.toString(data.getMaximumPrice()));
        if (data.isHospitalPreparationSigns())
            hospitalPreparationSigns.setSelectedIndex(1);
        else hospitalPreparationSigns.setSelectedIndex(2);
        if (data.isNeedApproval())
            needApproval.setSelectedIndex(1);
        else needApproval.setSelectedIndex(2);
        hospitalGrade.setSelectedIndex(data.getHospitalGrade() + 1);
        dosageForm.setText(data.getDosageForm());
        frequency.setText(data.getFrequency());
        usage.setText(data.getUsage());
        unit.setText(data.getUnit());
        specification.setText(data.getSpecification());
        limitDays.setText(data.getLimitDays());
        tradeName.setText(data.getTradeName());
        factory.setText(data.getFactory());
        ChineseMedicineProspectiveWord.setText(data.getChineseMedicineProspectiveWord());
        remarks.setText(data.getRemarks());
        nationalCatelogCode.setText(data.getNationalCatelogCode());
        limitUsage.setText(data.getLimitUsage());
        origin.setText(data.getOrigin());

        isMedicineInquired = true;
    }

    public void GUItoMedicine(Medicine data) {
        data.setCoding(medicineCoding.getText());
        data.setChineseName(ChineseName.getText());
        data.setEnglishName(EnglishName.getText());
        data.setChargeCategory(chargeCategory.getSelectedIndex() - 1);
        data.setPrescriptionMark(prescriptionMark.getSelectedIndex() - 1);
        data.setFeeLevel(feeLevel.getSelectedIndex() - 1);
        data.setDosageUnit(dosageUnit.getText());
        data.setMaximumPrice(Double.parseDouble(maximumPrice.getText()));
        if (hospitalPreparationSigns.getSelectedIndex() == 1)
            data.setHospitalPreparationSigns(true);
        else if (hospitalPreparationSigns.getSelectedIndex() == 2)
            data.setHospitalPreparationSigns(false);
        if (needApproval.getSelectedIndex() == 1)
            data.setNeedApproval(true);
        else if (needApproval.getSelectedIndex() == 2)
            data.setNeedApproval(false);
        data.setHospitalGrade(hospitalGrade.getSelectedIndex() - 1);
        data.setDosageForm(dosageForm.getText());
        data.setFrequency(frequency.getText());
        data.setUsage(usage.getText());
        data.setUnit(unit.getText());
        data.setSpecification(specification.getText());
        data.setLimitDays(limitDays.getText());
        data.setTradeName(tradeName.getText());
        data.setFactory(factory.getText());
        data.setChineseMedicineProspectiveWord(ChineseMedicineProspectiveWord.getText());
        data.setRemarks(remarks.getText());
        data.setNationalCatelogCode(nationalCatelogCode.getText());
        data.setLimitUsage(limitUsage.getText());
        data.setOrigin(origin.getText());
    }

    public boolean isMedicineCompleted() {
        if (medicineCoding.getText().equals("") || ChineseName.getText().equals("") || EnglishName.getText().equals("") || dosageUnit.getText().equals("") || maximumPrice.getText().equals("") || chargeCategory.getSelectedIndex() == 0 || prescriptionMark.getSelectedIndex() == 0 || hospitalPreparationSigns.getSelectedIndex() == 0 || needApproval.getSelectedIndex() == 0 || needApproval.getSelectedIndex() == 0 || feeLevel.getSelectedIndex() == 0 || dosageForm.getText().equals("") || frequency.getText().equals("") || unit.getText().equals("") || usage.getText().equals("") || specification.getText().equals("") || limitDays.getText().equals("") || tradeName.getText().equals("") || factory.getText().equals("") || ChineseMedicineProspectiveWord.getText().equals("") || remarks.getText().equals("") || nationalCatelogCode.getText().equals("") || limitUsage.getText().equals("") || origin.getText().equals("") || hospitalGrade.getSelectedIndex() == 0)
            return false;
        return true;
    }

    public void DiseaseToGUI(Disease data) {
        diseasesCoding.setText(data.getCoding());
        diseasesName.setText(data.getName());
        diseasesCategory.setSelectedIndex(data.getCategory());
        diseaseReimbursementSigns.setSelectedIndex(data.getReimbursementSigns());
        diseasesRemark.setText(data.getRemarks());

        isDiseaseInquired = true;
    }

    public void GUItoDisease(Disease data) {
        data.setCoding(diseasesCoding.getText());
        data.setName(diseasesName.getText());
        data.setCategory(diseasesCategory.getSelectedIndex());
        data.setReimbursementSigns(diseaseReimbursementSigns.getSelectedIndex());
        data.setRemarks(diseasesRemark.getText());
    }

    public boolean isDiseaseCompleted() {
        if (diseasesCoding.getText().equals("") || diseasesName.getText().equals("") || diseasesCategory.getSelectedIndex() == 0 || diseaseReimbursementSigns.getSelectedIndex() == 0 || diseasesRemark.equals(""))
            return false;
        return true;
    }

    public void initDisease() {
        diseasesCoding.setText("");
        diseasesName.setText("");
        diseaseReimbursementSigns.setSelectedIndex(0);
        diseasesCategory.setSelectedIndex(0);
        diseasesRemark.setText("");

        isDiseaseInquired = false;

        diseasesSearchResults.setModel(new DefaultListModel());
    }
}
