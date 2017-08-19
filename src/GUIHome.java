import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIHome {
    private JPanel mainPanel;
    private JButton BasicMedicalInformation;
    private JButton medicalTreatmentApproval;

    public GUIHome() {
        BasicMedicalInformation.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);// 医疗基本信息维护 按钮被按下
                BasicMedicalInformationGUI.main(new String[10]);
            }
        });

        medicalTreatmentApproval.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);// 医疗待遇审批 按钮被按下
                MedicalTreatmentApprovalGUI.main(new String [10]);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUIHome");
        frame.setContentPane(new GUIHome().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
