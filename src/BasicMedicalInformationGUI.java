import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

    public static void main(String[] args) {
        JFrame frame = new JFrame("BasicMedicalInformation");
        frame.setContentPane(new BasicMedicalInformationGUI().mainpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public BasicMedicalInformationGUI() {

        BasicMedicalInformation.Medicine data = new BasicMedicalInformation.Medicine();

        //监听器
        inquire.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);  // 查询按钮被按下
                // 通过 药品编码 或 药品名称 查询
                if(!medicineCoding.getText().equals(""))//如果编号非空
                {

                }

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
    }
}
