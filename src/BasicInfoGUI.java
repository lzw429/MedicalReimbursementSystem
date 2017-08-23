import BasicInfo.Unit;

import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class BasicInfoGUI extends DAO {
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
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);// 查询单位信息 按钮被按下
                //通过单位编号 或 单位名称 查询
                if (!unitCoding.getText().equals(""))//如果编码非空
                {
                    Unit unit = new Unit();
                    try {
                        con = getConnection();
                        s = con.createStatement();//创建SQL语句对象
                        rs = s.executeQuery("select * from Unit where unitCoding = " + unitCoding.getText());// 查询单位信息
                        while (rs.next()) {
                            unitCoding.setText(rs.getString("unitCoding"));
                            unitName.setText(rs.getString("unitName"));
                            unitType.setSelectedIndex(rs.getInt("type"));
                            unitAddress.setText(rs.getString("address"));
                            unitZipCode.setText(rs.getString("zipcode"));
                            unitTel.setText(rs.getString("tel"));
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    } finally {
                        CloseConnection(con, rs, s);
                    }
                }
            }
        });
    }

    public void add() {
        try {
            con = getConnection();
            s = con.createStatement();//创建SQL语句对象
            int result = s.executeUpdate("insert into Unit(unitCoding,unitName,type,address,zipcode,tel)");
            //if (result>0)插入成功
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseConnection(con, rs, s);
        }

    }

    public void delete() {
        try {
            con = getConnection();
            s = con.createStatement();//创建SQL语句对象
            int result = s.executeUpdate("delete from Unit where ");
            //if(result>0) 删除成功
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            CloseConnection(con, rs, s);
        }

    }
}
