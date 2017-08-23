import BasicInfo.Unit;

import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class BasicInfoGUI {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JTextField textField1;
    private JButton inquire;
    private JTextField unitCoding;
    private JButton inquireUnit;
    private JComboBox unitType;
    private JList unitSearchResult;
    private JTextField unitName;
    private JTextField unitAddress;
    private JTextField unitZipCode;
    private JTextField unitTel;
    private JButton saveUnit;
    private JButton addUnit;
    private JButton deleteUnit;
    private JButton resetUnit;

    private Connection con = null;
    private Statement s = null;
    private ResultSet rs = null;

    public static void main(String[] args) {
        JFrame frame = new JFrame("基本信息维护");
        frame.setContentPane(new BasicInfoGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public BasicInfoGUI() //GUI 构造方法，包含监听器
    {

        inquireUnit.addMouseListener(new MouseAdapter() {
            private boolean readFlag = false;

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);// 查询单位信息 按钮被按下
                if (!unitCoding.getText().equals(""))//如果编码非空
                {
                    Unit data = new Unit();

                }
            }
        });
    }
}