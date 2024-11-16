import java.sql.*;

public class JDBC {
    public static void main(String[] args) {
        // SQL Query
        String sql = "SELECT * FROM country"; // Query Statement

        try (Connection con = DatabaseConnection.getConnection();  // Using the custom connection class
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            // Process the result set
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