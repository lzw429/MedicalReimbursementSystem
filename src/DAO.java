import java.sql.*;

public class DAO //data access object
{
    Connection con = null;
    Statement s = null;
    ResultSet rs = null;

    //获取连接
    public Connection getConnection() {
        try {
            //加载 JDBC-ODBC 桥驱动程序
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

            //通过驱动连接
            con = DriverManager.getConnection("jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=C:\\\\Users\\\\舒意恒\\\\Documents\\\\GitHub\\\\ObjectOrientedProgramDesign\\\\data\\\\database.mdb;");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    // 关闭数据源
    public void CloseConnection(Connection con, ResultSet rs, Statement s) {
        try {
            if (rs != null)
                rs.close();
            if (s != null)
                s.close();
            if (con != null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
