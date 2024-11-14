import java.util.Date;

public class Reservation {
    private int reservationId;
    private Guest guest;
    private Date checkinDate;
    private Date checkoutDate;
    private Hotel hotel;

    // Constructor
    public Reservation(int reservationId, Guest guest, Date checkinDate, Date checkoutDate, Hotel hotel) {
        this.reservationId = reservationId;
        this.guest = guest;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.hotel = hotel;
    }

    // Getters and Setters
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}