import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IndividualAnnualCostGUI {
    private JPanel mainPanel;
    private JTextField textField1;
    private JButton inquirePerson;
    private JButton inquireTreatment;
    private JList treatmentList;
    private JTextField textField2;

    public IndividualAnnualCostGUI() {
        inquirePerson.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);// 查询个人信息 按钮被按下
                BasicInfoGUI.main(new String[10]);
            }
        });

        treatmentList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2)//双击单条病人就诊资料信息数据，查看处方明细信息
                    PrescriptionGUI.main(new String[10]);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("医疗人员费用查询");
        frame.setContentPane(new IndividualAnnualCostGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
