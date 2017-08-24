import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IndividualAnnualCostGUI {
    private JPanel mainPanel;
    private JTextField textField1;
    private JButton inquirePerson;
    private JButton inquireTreatment;
    private JList treatmentList;
    private JTextField 年度累计报销次数TextField;
    private JTabbedPane tabbedPane1;
    private JTextField unitCoding;
    private JTextField unitName;
    private JTextField unitAddress;
    private JTextField unitZipCode;
    private JTextField unitTel;
    private JComboBox unitType;
    private JPanel prescription;

    public static void main(String[] args) {
        JFrame frame = new JFrame("医疗人员费用查询");
        frame.setContentPane(new IndividualAnnualCostGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
