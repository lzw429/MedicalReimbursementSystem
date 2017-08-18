import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class home {
    private JPanel mainPanel;
    private JButton BasicMedicalInformation;

    public home() {
        BasicMedicalInformation.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }
}
