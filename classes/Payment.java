package classes;

import java.time.LocalDate;

public class Payment {
    private int paymentId;
    private int reservationId; // Foreign Key to reservation
    private LocalDate paymentDate;
    private float amount;
    private int discountId; // Foreign Key to discount
    private int paymentMethodId; // Foreign Key to payment method

    // Constructor
    public Payment(int paymentId, LocalDate paymentDate, float amount, int paymentMethodId, int discountId, int reservationId) {
        this.paymentId = paymentId;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.paymentMethodId = paymentMethodId;
        this.discountId = discountId;
        this.reservationId = reservationId;
    }

    // Getters and Setters
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }
}