import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CenterReimbursementGUI {
    private JButton IndividualAnnualCost;
    private JTextField textField1;
    private JPanel mainPanel;
    private JButton PersonnelTreatmentInfo;
    private JTextArea 市基本医疗保险报销医疗费用结算清单TextArea;
    private JButton MedicalTreatmentApproval;

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
                IndividualAnnualCostGUI.main(new String[10]);
            }
        });
        MedicalTreatmentApproval.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);// 校验审批信息 按钮被按下
                MedicalTreatmentApprovalGUI.main(new String[10]);
            }
        });
        PersonnelTreatmentInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);// 入院信息查询 按钮被按下
                PersonnelTreatmentInfoGUI.main(new String[10]);
            }
        });
    }
}
