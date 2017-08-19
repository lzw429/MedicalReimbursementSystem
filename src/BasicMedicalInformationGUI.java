import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
                    }
                    else // if (!readFlag)
                        init();
                } else // 查询编码为空
                    init();
            }
        });

        addData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);  // 添加按钮被按下
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
    }
}
