public class Guest {
    private int guest_id;
    private String guest_name;
    private int contact_no;
    private String email;

    public Guest(int guestId, String guestName, int contactNo) {
        this.guest_id = guestId;
        this.guest_name = guestName;
        this.contact_no = contactNo;
    }

    public Guest(int guestId, String guestName, int contactNo, String email) {
        this.guest_id = guestId;
        this.guest_name = guestName;
        this.contact_no = contactNo;
        this.email = email;
    }

    public int getGuestId() {
        return guest_id;
    }

    public void setGuestId(int guestId) {
        this.guest_id = guestId;
    }

    public String getGuestName() {
        return guest_name;
    }

    public void setGuestName(String guestName) {
        this.guest_name = guestName;
    }

    public int getContactNo() {
        return contact_no;
    }

    public void setContactNo(int contactNo) {
        this.contact_no = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}