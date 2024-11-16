public class Guest {
    private int guestId;
    private String guestName;
    private int contactNo;
    private String email;

    public Guest(int guestId, String guestName, int contactNo) {
        this.guestId = guestId;
        this.guestName = guestName;
        this.contactNo = contactNo;
    }

    public Guest(int guestId, String guestName, int contactNo, String email) {
        this.guestId = guestId;
        this.guestName = guestName;
        this.contactNo = contactNo;
        this.email = email;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public int getContactNo() {
        return contactNo;
    }

    public void setContactNo(int contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}