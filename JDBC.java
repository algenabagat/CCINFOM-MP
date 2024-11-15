import java.sql.*;

public class JDBC {
    public static void main(String[] args) {
        String sql = "SELECT * FROM country"; // Query Statement

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoteldb?user=root&password=abagat1124");
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                int countryId = rs.getInt("country_id");
                String countryName = rs.getString("country_name");
                Country country = new Country(countryId, countryName);
                System.out.println("Country ID: " + country.getCountryId() + ", Country Name: " + country.getCountryName());
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace to diagnose the issue
        }
    }
}
