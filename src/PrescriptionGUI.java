import javax.swing.*;

public class PrescriptionGUI {
    private JPanel prescription;
    private JTextField textField1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("处方明细");
        frame.setContentPane(new PrescriptionGUI().prescription);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
