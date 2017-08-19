import javax.swing.*;

public class PersonnelTreatmentInfoGUI {
    private JPanel mainPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("人员就诊信息");
        frame.setContentPane(new PersonnelTreatmentInfoGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
