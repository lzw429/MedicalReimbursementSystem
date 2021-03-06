import BasicMedicalInformation.Disease;
import BasicMedicalInformation.Institution;
import BasicMedicalInformation.Medicine;
import BasicMedicalInformation.Treatment;

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
    private JComboBox medicineNeedApproval;
    private JComboBox hospitalGrade;
    private JComboBox feeLevel;
    private JTextField dosageForm;
    private JTextField frequency;
    private JTextField unit;
    private JTextField usage;
    private JTextField specification;
    private JTextField limitDays;
    private JTextField tradeName;
    private JTextField medicineFactory;
    private JTextField ChineseMedicineProspectiveWord;
    private JTextField medicineRemark;
    private JTextField nationalCatelogCode;
    private JTextField medicineLimitUsage;
    private JTextField origin;
    private JButton resetMedicine;

    private JList medicineSearchResult;
    private DefaultListModel medicineListModel;

    private JTextField diseasesCoding;
    private JComboBox diseasesCategory;
    private JComboBox diseaseReimbursementSigns;
    private JTextField diseasesRemark;
    private JButton addDisease;
    private JButton inquireDisease;
    private JTextField diseasesName;

    private DefaultListModel diseaseListModel;

    private JList diseasesSearchResults;
    private JButton saveDisease;
    private JButton deleteDisease;
    private JButton resetDisease;
    private JTextField institutionCoding;
    private JComboBox institutionGrade;
    private JTextField institutionName;
    private JComboBox institutionType;
    private JTextField institutionZipCode;
    private JTextField LRName;
    private JTextField LRTel;
    private JTextField contactPersonName;
    private JTextField contactTel;
    private JTextField contactPersonTel;
    private JTextField address;
    private JTextField institutionRemark;
    private JButton inquireInstitution;
    private JButton addInstitution;
    private JButton saveInstitution;
    private JButton deleteInstitution;
    private JButton resetInstitution;
    private JList institutionSearchResults;
    private JTextField treatmentCoding;
    private JComboBox treatmentChargeCategory;
    private JButton inquireTreatment;
    private JButton addTreatment;
    private JButton saveTreatment;
    private JButton deleteTreatment;
    private JButton resetTreatment;
    private JList treatmentSearchResult;
    private JTextField treatmentName;
    private JComboBox treatmentFeeLevel;
    private JComboBox treatmentHospitalGrade;
    private JComboBox treatmentNeedApproval;
    private JTextField treatmentUnit;
    private JTextField treatmentFactory;
    private JTextField treatmentRemark;
    private JTextField treatmentRestriction;

    private DefaultListModel institutionListModel;

    private boolean isMedicineInquired = false;
    private boolean isDiseaseInquired = false;
    private boolean isInstitutionInquired = false;
    private DefaultListModel treatmentListModel;
    private boolean isTreatmentInquired = false;

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
        medicineNeedApproval.setSelectedIndex(0);
        hospitalGrade.setSelectedIndex(0);
        dosageForm.setText("");
        frequency.setText("");
        usage.setText("");
        unit.setText("");
        specification.setText("");
        limitDays.setText("");
        tradeName.setText("");
        medicineFactory.setText("");
        ChineseMedicineProspectiveWord.setText("");
        medicineRemark.setText("");
        nationalCatelogCode.setText("");
        medicineLimitUsage.setText("");
        origin.setText("");

        isMedicineInquired = false;

        medicineSearchResult.setModel(new DefaultListModel());
    }

    public BasicMedicalInformationGUI() {

        inquireMedicine.addMouseListener(new MouseAdapter() {//查询药品 按钮
            private boolean readFlag = false;

            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);  // 查询药品 按钮被按下
                // 通过 药品编码 或 药品名称 查询
                if (!medicineCoding.getText().equals(""))//如果编码非空
                {
                    Medicine data = new Medicine();
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
                    medicineSearchResult.setModel(new DefaultListModel<>());
                    try {
                        while ((line = reader.readLine()) != null) {
                            item = line.split(",");
                            if (item[1].contains(ChineseName.getText()))//判断文本框中的内容是否是item[1]的子串
                            {
                                Medicine medicine = new Medicine();
                                medicine.readCSV(item[0]);
                                // 展示到界面右侧列表
                                medicineListModel.addElement(medicine);
                                medicineSearchResult.setModel(medicineListModel);
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
                            medicineSearchResult.setModel(new DefaultListModel());
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
                            medicineSearchResult.setModel(new DefaultListModel());
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

        medicineSearchResult.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) // 列表元素被选中
            {
                Medicine data;
                if ((data = (Medicine) medicineSearchResult.getSelectedValue()) != null)// 小心空指针异常
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
                            JOptionPane.showMessageDialog(null, "未找到该病种信息", "错误", JOptionPane.ERROR_MESSAGE);
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

        inquireInstitution.addMouseListener(new MouseAdapter() {//查询机构 按钮
            private boolean readFlag = false;

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // 查询机构 按钮被按下
                //通过 机构编号 或 机构名称查询
                if (!institutionCoding.getText().equals(""))//如果编号非空
                {
                    Institution data = new Institution();
                    try {
                        readFlag = data.readCSV(institutionCoding.getText());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "文件读写错误", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                    if (readFlag)
                        InstitutionToGUI(data);
                    else // if(!readFlag)
                    {
                        JOptionPane.showMessageDialog(null, "未找到该机构信息", "警告", JOptionPane.WARNING_MESSAGE);
                        initInstitution();
                    }
                } else //机构编号为空，尝试通过机构名称查找
                {
                    String item[];
                    BufferedReader reader = null;
                    try {
                        reader = new BufferedReader(new FileReader("data/Institution.csv"));
                        reader.readLine();//第一行是标题
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "文件未找到", "错误", JOptionPane.ERROR_MESSAGE);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "文件读写错误", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                    String line;
                    institutionListModel = new DefaultListModel();
                    institutionSearchResults.setModel(new DefaultListModel());
                    try {
                        while ((line = reader.readLine()) != null) {
                            item = line.split(",");
                            if (item[1].contains(institutionName.getText()))//判断文本框中的内容是否是item[1]的子串
                            {
                                Institution institution = new Institution();
                                institution.readCSV(item[0]);
                                //展示到界面右侧列表
                                institutionListModel.addElement(institution);
                                institutionSearchResults.setModel(institutionListModel);
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

        addInstitution.addMouseListener(new MouseAdapter() {
            private boolean writeFlag;

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);// 添加机构 按钮被按下
                if (isInstitutionCompleted())//如果信息完整
                {
                    try {
                        Institution data = new Institution();
                        GUIToInstitution(data);
                        writeFlag = data.writeCSV();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "文件读写错误", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                    if (!writeFlag) {//在writeCSV方法中，coding已存在的机构不会被添加
                        JOptionPane.showMessageDialog(null, "该机构信息已存在", "警告", JOptionPane.WARNING_MESSAGE);
                    } else {
                        isInstitutionInquired = true;
                        JOptionPane.showMessageDialog(null, "添加机构信息成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                        institutionSearchResults.setModel(new DefaultListModel());
                    }
                }
            }
        });

        institutionCoding.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            // 机构编号文本框 内容被修改
            @Override
            public void insertUpdate(DocumentEvent e) {
                isInstitutionInquired = false;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                isInstitutionInquired = false;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                isInstitutionInquired = false;
            }
        });

        institutionSearchResults.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) // 列表元素被选中
            {
                Institution data;
                if ((data = (Institution) institutionSearchResults.getSelectedValue()) != null)
                    InstitutionToGUI(data);
            }
        });

        saveInstitution.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);// 保存机构信息 按钮被按下
                if (!isInstitutionCompleted()) {
                    JOptionPane.showMessageDialog(null, "请输入完整的信息", "警告", JOptionPane.WARNING_MESSAGE);
                } else {
                    Institution data = new Institution();
                    GUIToInstitution(data);
                    Institution findCSV = new Institution();
                    try {
                        if (!findCSV.readCSV(institutionCoding.getText())) {
                            JOptionPane.showMessageDialog(null, "未找到该机构", "错误", JOptionPane.ERROR_MESSAGE);
                        } else {
                            File file = new File("data/Institution.csv");
                            File temp = new File("data/Institution.temp.csv");
                            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(temp)));
                            String line;
                            String[] item;
                            while ((line = reader.readLine()) != null) {
                                item = line.split(",");
                                if (!item[0].equals(institutionCoding.getText()))
                                    writer.write(line + "\n");
                            }
                            reader.close();
                            writer.close();
                            file.delete();
                            temp.renameTo(file);

                            data.writeCSV();
                            isInstitutionInquired = true;
                            institutionSearchResults.setModel(new DefaultListModel());
                            JOptionPane.showMessageDialog(null, "保存成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "文件读写错误", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        deleteInstitution.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);//删除机构 按钮被按下
                if (!isInstitutionInquired)
                    JOptionPane.showMessageDialog(null, "请指定机构", "警告", JOptionPane.WARNING_MESSAGE);
                else {
                    if (JOptionPane.showConfirmDialog(null, "确定删除？", "确认", JOptionPane.YES_NO_OPTION) == 0)//用户点击 确定
                    {
                        File file = new File("data/Institution.csv");
                        File temp = new File("data/Institution.temp.csv");
                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(temp)));
                            String line;
                            String[] item;
                            while ((line = reader.readLine()) != null) {
                                item = line.split(",");
                                if (!item[0].equals(institutionCoding.getText()))
                                    writer.write(line + "\n");
                            }
                            reader.close();
                            writer.close();
                            file.delete();
                            temp.renameTo(file);
                            isInstitutionInquired = false;
                            institutionCoding.setText("");
                            initInstitution();
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

        resetInstitution.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                initInstitution();
                institutionCoding.setText("");
            }
        });

        inquireTreatment.addMouseListener(new MouseAdapter() {//查询诊疗项目 按钮
            private boolean readFlag = false;

            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);  // 查询诊疗项目 按钮被按下
                // 通过 项目编码 或 项目名称 查询
                if (!treatmentCoding.getText().equals(""))//如果编码非空
                {
                    Treatment data = new Treatment();
                    try {
                        readFlag = data.readCSV(treatmentCoding.getText());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "文件读写错误", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                    if (readFlag) {
                        TreatmentToGUI(data);
                    } else // if (!readFlag)
                    {
                        JOptionPane.showMessageDialog(null, "未找到该诊疗项目", "警告", JOptionPane.WARNING_MESSAGE);
                        initTreatment();
                    }
                } else // 项目编码为空，尝试通过项目名称查找
                {
                    // 逐条读取，判断用户输入的名称是不是其子串，如果是则用toString将其展示在列表上；当选中列表的某一项时，执行readCSV
                    String item[] = new String[15];
                    BufferedReader reader = null;
                    try {
                        reader = new BufferedReader(new FileReader("data/Treatment.csv"));
                        reader.readLine();//第一行是标题
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "文件未找到", "错误", JOptionPane.ERROR_MESSAGE);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "文件读写错误", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                    String line;
                    treatmentListModel = new DefaultListModel();
                    treatmentSearchResult.setModel(new DefaultListModel<>());
                    try {
                        while ((line = reader.readLine()) != null) {
                            item = line.split(",");
                            if (item[1].contains(treatmentName.getText()))//判断文本框中的内容是否是item[1]的子串
                            {
                                Treatment treatment = new Treatment();
                                treatment.readCSV(item[0]);
                                // 展示到界面右侧列表
                                treatmentListModel.addElement(treatment);
                                treatmentSearchResult.setModel(treatmentListModel);
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

        addTreatment.addMouseListener(new MouseAdapter() {
            boolean writeFlag = true;

            @Override
            public void mouseClicked(MouseEvent e) {

                super.mouseClicked(e);  // 添加诊疗项目 按钮被按下

                if (isTreatmentCompleted()) // 如果信息完整
                {
                    try {
                        Treatment data = new Treatment();
                        GUIToTreatment(data);
                        try {
                            writeFlag = data.writeCSV();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(null, "文件读写错误", "错误", JOptionPane.ERROR_MESSAGE);
                        }
                        if (!writeFlag) {// 在writeCSV方法中，coding已存在的药品不会被添加
                            JOptionPane.showMessageDialog(null, "该药品已存在", "警告", JOptionPane.WARNING_MESSAGE);
                        } else {
                            isTreatmentInquired = true;
                            JOptionPane.showMessageDialog(null, "添加药品信息成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                            treatmentSearchResult.setModel(new DefaultListModel());
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

        saveTreatment.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);  // 保存诊疗项目 按钮被按下
                // 先判断数据是否完整，如果完整，再判断该编码是否存在CSV中，如果存在则删除原数据、添加新数据
                if (!isTreatmentCompleted()) //判断信息是否完整
                {
                    JOptionPane.showMessageDialog(null, "请输入完整的信息", "警告", JOptionPane.WARNING_MESSAGE);
                } else {
                    Treatment data = new Treatment();
                    GUIToTreatment(data);
                    Treatment findCSV = new Treatment();
                    try {
                        if (!findCSV.readCSV(treatmentCoding.getText())) //判断当前编码是否存在于CSV
                        {
                            JOptionPane.showMessageDialog(null, "未找到该诊疗项目", "错误", JOptionPane.ERROR_MESSAGE);
                        } else { //删除原数据、添加新数据
                            File file = new File("data/Treatment.csv");
                            File temp = new File("data/Treatment.temp.csv");
                            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(temp)));
                            String line;
                            String[] item;
                            while ((line = reader.readLine()) != null) {
                                item = line.split(",");
                                if (!item[0].equals(treatmentCoding.getText()))
                                    writer.write(line + "\n");
                            }
                            reader.close();
                            writer.close();
                            file.delete();
                            temp.renameTo(file);

                            data.writeCSV();
                            isTreatmentInquired = true;
                            treatmentSearchResult.setModel(new DefaultListModel());
                            JOptionPane.showMessageDialog(null, "保存成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "文件读写错误", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        deleteTreatment.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);  // 删除诊疗项目 按钮被按下
                //readCSV，先查询，后删除
                if (!isTreatmentInquired)
                    JOptionPane.showMessageDialog(null, "请指定诊疗项目", "警告", JOptionPane.WARNING_MESSAGE);
                else // if(isTreatmentInquired)
                {
                    if (JOptionPane.showConfirmDialog(null, "确定删除？", "确认", JOptionPane.YES_NO_OPTION) == 0)//用户点击 确定
                    {
                        File file = new File("data/Treatment.csv");
                        File temp = new File("data/Treatment.temp.csv");
                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(temp)));
                            String line;
                            String[] item;
                            while ((line = reader.readLine()) != null) {
                                item = line.split(",");
                                if (!item[0].equals(treatmentCoding.getText()))
                                    writer.write(line + "\n");
                            }
                            reader.close();
                            writer.close();
                            file.delete();
                            temp.renameTo(file);

                            isTreatmentInquired = false;
                            treatmentCoding.setText("");
                            initTreatment();
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

        resetTreatment.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                initTreatment();
                treatmentCoding.setText("");
            }
        });

        treatmentCoding.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            // 诊疗项目编码文本框 内容被修改
            @Override
            public void insertUpdate(DocumentEvent e) {
                isTreatmentInquired = false;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                isTreatmentInquired = false;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                isTreatmentInquired = false;
            }
        });

        treatmentSearchResult.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) // 列表元素被选中
            {
                Treatment data;
                if ((data = (Treatment) treatmentSearchResult.getSelectedValue()) != null)
                    TreatmentToGUI(data);
            }
        });
    }

    private void initTreatment() {
        treatmentCoding.setText("");
        treatmentName.setText("");
        treatmentFeeLevel.setSelectedIndex(0);
        treatmentChargeCategory.setSelectedIndex(0);
        treatmentHospitalGrade.setSelectedIndex(0);
        treatmentNeedApproval.setSelectedIndex(0);
        treatmentUnit.setText("");
        treatmentFactory.setText("");
        treatmentRemark.setText("");
        treatmentRestriction.setText("");

        isTreatmentInquired = false;

        treatmentSearchResult.setModel(new DefaultListModel());
    }

    public boolean isTreatmentCompleted() {
        if (treatmentCoding.getText().equals("") || treatmentName.getText().equals("") || treatmentFeeLevel.getSelectedIndex() == 0 || treatmentChargeCategory.getSelectedIndex() == 0 || treatmentHospitalGrade.getSelectedIndex() == 0 || treatmentNeedApproval.getSelectedIndex() == 0 || treatmentUnit.getText().equals("") || treatmentFactory.getText().equals("") || treatmentRemark.getText().equals("") || treatmentRestriction.getText().equals(""))
            return false;
        return true;
    }

    public void TreatmentToGUI(Treatment data) {
        treatmentCoding.setText(data.getCoding());
        treatmentName.setText(data.getName());
        treatmentChargeCategory.setSelectedIndex(data.getChargeCategory());
        treatmentFeeLevel.setSelectedIndex(data.getFeeLevel());
        treatmentHospitalGrade.setSelectedIndex(data.getHospitalGrade());
        if (data.isNeedApproval()) treatmentNeedApproval.setSelectedIndex(1);
        else
            treatmentNeedApproval.setSelectedIndex(2);
        treatmentUnit.setText(data.getUnit());
        treatmentFactory.setText(data.getManufacturer());
        treatmentRemark.setText(data.getRemarks());
        treatmentRestriction.setText(data.getRestrictions());

        isTreatmentInquired = true;
    }

    public void GUIToTreatment(Treatment data) {
        data.setCoding(treatmentCoding.getText());
        data.setName(treatmentName.getText());
        data.setChargeCategory(treatmentChargeCategory.getSelectedIndex());
        data.setFeeLevel(treatmentFeeLevel.getSelectedIndex());
        data.setHospitalGrade(treatmentHospitalGrade.getSelectedIndex());
        if (treatmentNeedApproval.getSelectedIndex() == 1)
            data.setNeedApproval(true);
        else if (treatmentNeedApproval.getSelectedIndex() == 2)
            data.setNeedApproval(false);
        data.setUnit(treatmentUnit.getText());
        data.setManufacturer(treatmentFactory.getText());
        data.setRemarks(treatmentRemark.getText());
        data.setRestrictions(treatmentRestriction.getText());
    }

    public boolean isInstitutionCompleted() {
        if (institutionCoding.getText().equals("") || institutionName.getText().equals("") || institutionGrade.getSelectedIndex() == 0 || institutionType.getSelectedIndex() == 0 || institutionZipCode.getText().equals("") || LRName.getText().equals("") || LRTel.getText().equals("") || contactPersonName.getText().equals("") || contactTel.getText().equals("") || contactPersonTel.getText().equals("") || address.getText().equals("") || institutionRemark.getText().equals(""))
            return false;
        return true;
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
            medicineNeedApproval.setSelectedIndex(1);
        else medicineNeedApproval.setSelectedIndex(2);
        hospitalGrade.setSelectedIndex(data.getHospitalGrade() + 1);
        dosageForm.setText(data.getDosageForm());
        frequency.setText(data.getFrequency());
        usage.setText(data.getUsage());
        unit.setText(data.getUnit());
        specification.setText(data.getSpecification());
        limitDays.setText(data.getLimitDays());
        tradeName.setText(data.getTradeName());
        medicineFactory.setText(data.getFactory());
        ChineseMedicineProspectiveWord.setText(data.getChineseMedicineProspectiveWord());
        medicineRemark.setText(data.getRemark());
        nationalCatelogCode.setText(data.getNationalCatelogCode());
        medicineLimitUsage.setText(data.getLimitUsage());
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
        if (medicineNeedApproval.getSelectedIndex() == 1)
            data.setNeedApproval(true);
        else if (medicineNeedApproval.getSelectedIndex() == 2)
            data.setNeedApproval(false);
        data.setHospitalGrade(hospitalGrade.getSelectedIndex() - 1);
        data.setDosageForm(dosageForm.getText());
        data.setFrequency(frequency.getText());
        data.setUsage(usage.getText());
        data.setUnit(unit.getText());
        data.setSpecification(specification.getText());
        data.setLimitDays(limitDays.getText());
        data.setTradeName(tradeName.getText());
        data.setFactory(medicineFactory.getText());
        data.setChineseMedicineProspectiveWord(ChineseMedicineProspectiveWord.getText());
        data.setRemark(medicineRemark.getText());
        data.setNationalCatelogCode(nationalCatelogCode.getText());
        data.setLimitUsage(medicineLimitUsage.getText());
        data.setOrigin(origin.getText());
    }

    public boolean isMedicineCompleted() {
        if (medicineCoding.getText().equals("") || ChineseName.getText().equals("") || EnglishName.getText().equals("") || dosageUnit.getText().equals("") || maximumPrice.getText().equals("") || chargeCategory.getSelectedIndex() == 0 || prescriptionMark.getSelectedIndex() == 0 || hospitalPreparationSigns.getSelectedIndex() == 0 || medicineNeedApproval.getSelectedIndex() == 0 || medicineNeedApproval.getSelectedIndex() == 0 || feeLevel.getSelectedIndex() == 0 || dosageForm.getText().equals("") || frequency.getText().equals("") || unit.getText().equals("") || usage.getText().equals("") || specification.getText().equals("") || limitDays.getText().equals("") || tradeName.getText().equals("") || medicineFactory.getText().equals("") || ChineseMedicineProspectiveWord.getText().equals("") || medicineRemark.getText().equals("") || nationalCatelogCode.getText().equals("") || medicineLimitUsage.getText().equals("") || origin.getText().equals("") || hospitalGrade.getSelectedIndex() == 0)
            return false;
        return true;
    }

    public void DiseaseToGUI(Disease data) {
        diseasesCoding.setText(data.getCoding());
        diseasesName.setText(data.getName());
        diseasesCategory.setSelectedIndex(data.getCategory());
        diseaseReimbursementSigns.setSelectedIndex(data.getReimbursementSigns());
        diseasesRemark.setText(data.getRemark());

        isDiseaseInquired = true;
    }

    public void GUItoDisease(Disease data) {
        data.setCoding(diseasesCoding.getText());
        data.setName(diseasesName.getText());
        data.setCategory(diseasesCategory.getSelectedIndex());
        data.setReimbursementSigns(diseaseReimbursementSigns.getSelectedIndex());
        data.setRemark(diseasesRemark.getText());
    }

    public boolean isDiseaseCompleted() {
        if (diseasesCoding.getText().equals("") || diseasesName.getText().equals("") || diseasesCategory.getSelectedIndex() == 0 || diseaseReimbursementSigns.getSelectedIndex() == 0 || diseasesRemark.getText().equals(""))
            return false;
        return true;
    }

    public void initDisease() {
        diseasesName.setText("");
        diseaseReimbursementSigns.setSelectedIndex(0);
        diseasesCategory.setSelectedIndex(0);
        diseasesRemark.setText("");

        isDiseaseInquired = false;

        diseasesSearchResults.setModel(new DefaultListModel());
    }

    public void InstitutionToGUI(Institution data) {
        institutionCoding.setText(data.getCoding());
        institutionName.setText(data.getName());
        institutionGrade.setSelectedIndex(data.getHospitalGrade());
        institutionType.setSelectedIndex(data.getType());
        institutionZipCode.setText(data.getZipcode());
        LRName.setText(data.getLRName());
        LRTel.setText(data.getLRTel());
        contactPersonName.setText(data.getContactPersonName());
        contactTel.setText(data.getContactTel());
        contactPersonTel.setText(data.getContactPersonTel());
        address.setText(data.getAddress());
        institutionRemark.setText(data.getRemark());

        isInstitutionInquired = true;
    }

    public void GUIToInstitution(Institution data) {
        data.setCoding(institutionCoding.getText());
        data.setName(institutionName.getText());
        data.setHospitalGrade(institutionGrade.getSelectedIndex());
        data.setType(institutionType.getSelectedIndex());
        data.setZipcode(institutionZipCode.getText());
        data.setLRName(LRName.getText());
        data.setLRTel(LRTel.getText());
        data.setContactPersonName(contactPersonName.getText());
        data.setContactTel(contactTel.getText());
        data.setContactPersonTel(contactPersonTel.getText());
        data.setAddress(address.getText());
        data.setRemark(institutionRemark.getText());
    }

    public void initInstitution() {
        institutionName.setText("");
        institutionGrade.setSelectedIndex(0);
        institutionType.setSelectedIndex(0);
        institutionZipCode.setText("");
        LRName.setText("");
        LRTel.setText("");
        contactPersonName.setText("");
        contactTel.setText("");
        contactPersonTel.setText("");
        address.setText("");
        institutionRemark.setText("");

        isInstitutionInquired = false;

        institutionSearchResults.setModel(new DefaultListModel());
    }

}
