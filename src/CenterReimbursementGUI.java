import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CenterReimbursementGUI {
    private JButton IndividualAnnualCost;
    private JTextField textField1;
    private JPanel mainPanel;
    private JButton 入院信息查询Button;
    private JTextArea textArea1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("医保中心报销");
        frame.setContentPane(new CenterReimbursementGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public CenterReimbursementGUI() {
        IndividualAnnualCost.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);// 人员信息查询 按钮被按下

            }
        });
    }
}
