package chatgui4;

import java.sql.*;
import javax.swing.JOptionPane;

public class DatabaseFunctions {

    PreparedStatement stat;
    String host;
    Connection con;

    public DatabaseFunctions(String host) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/login", "root", "root");
            //stat = con.createStatement();

            this.host = host;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    protected ResultSet getResultSet(String rquery, String uname) {
        ResultSet result=null;
        try {
            System.out.println(uname);
            stat = con.prepareStatement(rquery);
            stat.setString(1, uname);
            System.out.println("hyhyhy");
            result = stat.executeQuery();
        } catch (Exception e) {
            System.out.println("error =" + e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return (result);

    }

    protected boolean updateQuery(String uquery) throws SQLException {
        stat=con.prepareStatement(uquery);
        
        int a = stat.executeUpdate(uquery);
        if (a > 0) {
            return (true);
        } else {
            return (false);
        }
    }
}
