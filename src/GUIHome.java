import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIHome {
    private JPanel mainPanel;
    private JButton BasicMedicalInformation;

    public GUIHome() {
        BasicMedicalInformation.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);// 医疗基本信息维护 按钮被按下

            }
        });

    }
}
