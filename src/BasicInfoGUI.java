import javax.swing.*;

public class BasicInfoGUI {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JTextField textField1;
    private JButton inquire;

    public static void main(String[] args) {
        JFrame frame = new JFrame("基本信息维护");
        frame.setContentPane(new BasicInfoGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
