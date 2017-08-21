import com.sun.corba.se.impl.protocol.InfoOnlyServantCacheLocalCRDImpl;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class BasicMedicalInformationGUI {
    private JPanel mainpanel;
    private JTabbedPane tabbedPane1;
    private JPanel medicine;
    private JPanel treatment;
    private JPanel serviceFacilities;
    private JPanel fixedMedicalInstitution;
    private JPanel diseases;
    private JPanel medicalTreatmentCalculationParameters;
    private JTextField medicineCoding;
    private JTextField ChineseName;
    private JTextField EnglishName;
    private JTextField dosageUnit;
    private JTextField maximumPrice;
    private JButton inquire;
    private JButton saveData;
    private JButton deleteData;
    private JButton addData;
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
    private JButton reset;
    private JComboBox comboBox1;
    private JTextField textField1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("医疗基本信息维护");
        frame.setContentPane(new BasicMedicalInformationGUI().mainpanel);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void init() {
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
    }

    public BasicMedicalInformationGUI() {

        //监听器
        inquire.addMouseListener(new MouseAdapter() {
            private boolean readflag = false;

            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);  // 查询按钮被按下
                // 通过 药品编码 或 药品名称 查询
                if (!medicineCoding.getText().equals(""))//如果编号非空
                {
                    BasicMedicalInformation.Medicine data = new BasicMedicalInformation.Medicine();
                    try {
                        readflag = data.readCSV(medicineCoding.getText());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "文件读入错误", "警告", JOptionPane.ERROR_MESSAGE);
                    }
                    if (readflag) {
                        ChineseName.setText(data.getChineseName());
                        EnglishName.setText(data.getEnglishName());
                        switch (data.getChargeCategory()) {
                            case 0:
                                chargeCategory.setSelectedIndex(1);
                                break;
                            case 1:
                                chargeCategory.setSelectedIndex(2);
                                break;
                            case 2:
                                chargeCategory.setSelectedIndex(3);
                                break;
                        }
                        switch (data.getPrescriptionMark()) {
                            case 0:
                                prescriptionMark.setSelectedIndex(1);
                                break;
                            case 1:
                                prescriptionMark.setSelectedIndex(2);
                                break;
                            case 2:
                                prescriptionMark.setSelectedIndex(3);
                                break;
                        }
                        switch (data.getFeeLevel()) {
                            case 0:
                                feeLevel.setSelectedIndex(1);
                                break;
                            case 1:
                                feeLevel.setSelectedIndex(2);
                                break;
                            case 2:
                                feeLevel.setSelectedIndex(3);
                                break;
                        }
                        dosageUnit.setText(data.getDosageUnit());
                        maximumPrice.setText(Double.toString(data.getMaximumPrice()));
                        if (data.isHospitalPreparationSigns())
                            hospitalPreparationSigns.setSelectedIndex(1);
                        else hospitalPreparationSigns.setSelectedIndex(2);
                        if (data.isNeedApproval())
                            needApproval.setSelectedIndex(1);
                        else needApproval.setSelectedIndex(2);
                        switch (data.getHospitalGrade()) {
                            case 0:
                                hospitalGrade.setSelectedIndex(1);
                                break;

                            case 1:
                                hospitalGrade.setSelectedIndex(2);
                                break;

                            case 2:
                                hospitalGrade.setSelectedIndex(3);
                                break;

                            case 3:
                                hospitalGrade.setSelectedIndex(4);
                                break;
                        }
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
                    } else // if (!readFlag)
                    {
                        JOptionPane.showMessageDialog(null, "未找到该药品", "警告", JOptionPane.ERROR_MESSAGE);
                        init();
                    }
                } else // 查询编码为空
                {
                    JOptionPane.showMessageDialog(null, "请键入药品编码", "警告", JOptionPane.ERROR_MESSAGE);
                    init();
                }
            }
        });

        addData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);  // 添加 按钮被按下
                boolean writeFlag = true;
                if (!medicineCoding.getText().equals("")) {
                    BasicMedicalInformation.Medicine data = new BasicMedicalInformation.Medicine();
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
                    try {
                        writeFlag = data.writeCSV(data.getCoding());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "文件写出错误", "警告", JOptionPane.ERROR_MESSAGE);
                    }
                    if (!writeFlag) {
                        JOptionPane.showMessageDialog(null, "该药品已存在", "警告", JOptionPane.ERROR_MESSAGE);
                    }

                } else // 如果coding为空
                {
                    JOptionPane.showMessageDialog(null, "请键入药品编码", "警告", JOptionPane.ERROR_MESSAGE);
                    init();
                }
            }
        });

        saveData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);  // 保存按钮被按下
            }
        });

        deleteData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);  // 删除按钮被按下
                //readCSV，先查询，后删除
                if (!medicineCoding.getText().equals(""))//如果编号非空
                {
                    if (JOptionPane.showConfirmDialog(null, "确定删除？", "确认", JOptionPane.YES_NO_OPTION) == 0)//用户点击 确定
                    {
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "请键入药品编码", "警告", JOptionPane.ERROR_MESSAGE);
                    init();
                }
            }
        });

        reset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);  // 重置按钮被按下
                init();
                medicineCoding.setText("");
            }
        });

        medicineCoding.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                init();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                init();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                init();
            }
        });

    }
}

