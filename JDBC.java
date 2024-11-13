import java.sql.*;

public class JDBC {
    public static void main(String[] args) throws SQLException {

        String sql = "Select"; //

        String url = "jdbc:mysql:";
        String username = "mysql";
        String password = "";

        Connection con = DriverManager.getConnection(url, username, password);

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        con.close();
    }
}
