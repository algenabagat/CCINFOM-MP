public class Hotel {
    private int hotelId;
    private String hotelName;
    private String hotelEmail;
    private int contactNo;
    private int locationId;

    // Constructor
    public Hotel(int hotelId, String hotelName, String hotelEmail, int contactNo, int locationId) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelEmail = hotelEmail;
        this.contactNo = contactNo;
        this.locationId = locationId;
    }

    // Getters and Setters
    public int gethotelId() {
        return hotelId;
    }

    public void sethotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String gethotelName() {
        return hotelName;
    }

    public void sethotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String gethotelEmail() {
        return hotelEmail;
    }

    public void sethotelEmail(String hotelEmail) {
        this.hotelEmail = hotelEmail;
    }

    public int getcontactNo() {
        return contactNo;
    }

    public void setcontactNo(int contactNo) {
        this.contactNo = contactNo;
    }

    public int getlocationId() {
        return locationId;
    }

    public void setlocationId(int locationId) {
        this.locationId = locationId;
    }
}