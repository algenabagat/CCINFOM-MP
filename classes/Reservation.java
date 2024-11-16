package classes;

import java.time.LocalDate;

public class Reservation {
    private int reservationId;
    private int guestId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int hotelId;
    private int paymentId;

    // Constructor
    public Reservation(int reservationId, int guestId, LocalDate checkInDate, LocalDate checkOutDate, int hotelId, int paymentId) {
        this.reservationId = reservationId;
        this.guestId = guestId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.hotelId = hotelId;
        this.paymentId = paymentId;
    }

    // Getters and Setters
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
}