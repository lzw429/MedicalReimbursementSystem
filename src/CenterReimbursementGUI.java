import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CenterReimbursementGUI {
    private JButton IndividualAnnualCost;
    private JPanel mainPanel;
    private JButton PersonnelTreatmentInfo;
    private JButton MedicalTreatmentApproval;
    private JButton inquirePerson;
    private JTextField 年度累计报销次数TextField;
    private JTextField textField1;
    private JButton 预结算Button;
    private JButton 结算Button;
    private JButton 打印单据Button;
    private JButton 录入Button;
    private JTextField textField2;
    private JTable table1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("医保中心报销");
        frame.setContentPane(new CenterReimbursementGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
