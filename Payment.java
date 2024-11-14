import java.util.Date;

public class Payment {
    private int payment_id;
    private Date payment_date;
    private float amount;
    private PaymentMethod payment_method;
    private Discount discount;
    private Reservation reservation;

    // Constructor
    public Payment(int payment_id, Date payment_date, float amount, PaymentMethod payment_method, Discount discount, Reservation reservation) {
        this.payment_id = payment_id;
        this.payment_date = payment_date;
        this.amount = amount;
        this.payment_method = payment_method;
        this.discount = discount;
        this.reservation = reservation;
    }

    // Getters and Setters
    public int getpayment_id() {
        return payment_id;
    }

    public void setpayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public Date getpayment_date() {
        return payment_date;
    }

    public void setpayment_date(Date payment_date) {
        this.payment_date = payment_date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public PaymentMethod getPaymentMethod() {
        return payment_method;
    }

    public void setPaymentMethod(PaymentMethod payment_method) {
        this.payment_method = payment_method;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}