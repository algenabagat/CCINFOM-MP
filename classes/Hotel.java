package classes;

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

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
}