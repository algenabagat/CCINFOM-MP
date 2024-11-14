public class Hotel {
    private int hotelId;
    private String hotelName;
    private String hotelEmail;
    private int contactNo;
    private Location location;

    // Constructor
    public Hotel(int hotelId, String hotelName, String hotelEmail, int contactNo, Location location) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelEmail = hotelEmail;
        this.contactNo = contactNo;
        this.location = location;
    }

    // Getters and Setters
    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelEmail() {
        return hotelEmail;
    }

    public void setHotelEmail(String hotelEmail) {
        this.hotelEmail = hotelEmail;
    }

    public int getContactNo() {
        return contactNo;
    }

    public void setContactNo(int contactNo) {
        this.contactNo = contactNo;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}