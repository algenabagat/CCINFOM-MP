import java.sql.*;

public class HotelManagement {
    public static void viewHotelDetails() {
        String query = "SELECT " +
                "h.HOTEL_ID, h.HOTEL_NAME, " +
                "l.STREET_ADDRESS, l.CITY, l.POSTAL_CODE, l.STATE_PROVINCE, " +
                "c.COUNTRY_NAME, h.HOTEL_EMAIL, h.CONTACT_NO " +
                "FROM hotel h " +
                "JOIN location l ON h.LOCATION_ID = l.LOCATION_ID " +
                "JOIN country c ON l.COUNTRY_ID = c.COUNTRY_ID";

        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Print the table header with partitions
            System.out.println("+------------+--------------------------------+------------------------------------------+----------------------+--------------+--------------------------+---------------------+----------------------------------------------------+-----------------+");
            System.out.printf("| %-10s | %-30s | %-40s | %-20s | %-12s | %-24s | %-19s | %-50s | %-15s |\n",
                    "Hotel ID", "Hotel Name", "Street Address", "City", "Postal Code",
                    "State/Province", "Country", "Hotel Email", "Contact No");
            System.out.println("+------------+--------------------------------+------------------------------------------+----------------------+--------------+--------------------------+---------------------+----------------------------------------------------+-----------------+");

            // Loop through the result set and print each hotel record in tabular format with partitions
            while (rs.next()) {
                int hotelId = rs.getInt("HOTEL_ID");
                String hotelName = rs.getString("HOTEL_NAME");
                String streetAddress = rs.getString("STREET_ADDRESS");
                String city = rs.getString("CITY");
                String postalCode = rs.getString("POSTAL_CODE");
                String stateProvince = rs.getString("STATE_PROVINCE");
                String countryName = rs.getString("COUNTRY_NAME");
                String hotelEmail = rs.getString("HOTEL_EMAIL");
                String contactNo = rs.getString("CONTACT_NO");

                // Print the hotel details with partitions and proper borders
                System.out.printf("| %-10d | %-30s | %-40s | %-20s | %-12s | %-24s | %-19s | %-50s | %-15s |\n",
                        hotelId, hotelName, streetAddress, city, postalCode,
                        stateProvince, countryName, hotelEmail, contactNo);
                System.out.println("+------------+--------------------------------+------------------------------------------+----------------------+--------------+--------------------------+---------------------+----------------------------------------------------+-----------------+");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        
    }


}
