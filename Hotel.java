public class Hotel {
    private int hotel_id;
    private String hotel_name;
    private String hotel_email;
    private int contact_no;
    private Location location;

    // Constructor
    public Hotel(int hotel_id, String hotel_name, String hotel_email, int contact_no, Location location) {
        this.hotel_id = hotel_id;
        this.hotel_name = hotel_name;
        this.hotel_email = hotel_email;
        this.contact_no = contact_no;
        this.location = location;
    }

    // Getters and Setters
    public int gethotel_id() {
        return hotel_id;
    }

    public void sethotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String gethotel_name() {
        return hotel_name;
    }

    public void sethotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String gethotel_email() {
        return hotel_email;
    }

    public void sethotel_email(String hotel_email) {
        this.hotel_email = hotel_email;
    }

    public int getcontact_no() {
        return contact_no;
    }

    public void setcontact_no(int contact_no) {
        this.contact_no = contact_no;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}