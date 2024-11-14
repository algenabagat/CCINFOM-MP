import java.util.Date;

public class Reservation {
    private int reservation_id;
    private Guest guest;
    private Date checkIn_date;
    private Date checkOut_date;
    private Hotel hotel;

    // Constructor
    public Reservation(int reservation_id, Guest guest, Date checkIn_date, Date checkOut_date, Hotel hotel) {
        this.reservation_id = reservation_id;
        this.guest = guest;
        this.checkIn_date = checkIn_date;
        this.checkOut_date = checkOut_date;
        this.hotel = hotel;
    }

    // Getters and Setters
    public int getreservation_id() {
        return reservation_id;
    }

    public void setreservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Date getcheckIn_date() {
        return checkIn_date;
    }

    public void setcheckIn_date(Date checkIn_date) {
        this.checkIn_date = checkIn_date;
    }

    public Date getcheckOut_date() {
        return checkOut_date;
    }

    public void setcheckOut_date(Date checkOut_date) {
        this.checkOut_date = checkOut_date;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}