import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIHome {
    private JPanel mainPanel;
    private JButton BasicMedicalInformation;
    private JButton medicalTreatmentApproval;
    private JButton IntegratedQuery;
    private JButton BasicInfo;
    private JButton CenterReimbursement;
    private JTextField textField2;
    private JButton login;
    private JPasswordField passwordField1;

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
                MedicalTreatmentApprovalGUI.main(new String[10]);
            }
        });
        IntegratedQuery.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);// 医疗人员费用查询 按钮被按下
                IndividualAnnualCostGUI.main(new String[10]);
            }
        });
        BasicInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);// 基本信息查询 按钮被按下
                BasicInfoGUI.main(new String[10]);
            }
        });
        CenterReimbursement.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);// 医保中心报销 按钮被按下
                CenterReimbursementGUI.main(new String[10]);
            }
        });

        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);// 登录按钮被按下
                BasicMedicalInformation.setEnabled(true);
                medicalTreatmentApproval.setEnabled(true);
                CenterReimbursement.setEnabled(true);
                IntegratedQuery.setEnabled(true);
                BasicInfo.setEnabled(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("医疗保险中心信息管理系统");
        frame.setContentPane(new GUIHome().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
