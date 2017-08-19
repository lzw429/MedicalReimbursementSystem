import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MedicalTreatmentApprovalGUI {
    private JTabbedPane tabbedPane1;
    private JButton inquire;
    private JButton addData;
    private JButton saveData;
    private JButton deleteData;
    private JButton reset;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JPanel mainPanel;

    public MedicalTreatmentApprovalGUI() {
        inquire.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);// 查询 按钮被按下
                BasicInfoGUI.main(new String [10]);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("医疗待遇审批");
        frame.setContentPane(new MedicalTreatmentApprovalGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
