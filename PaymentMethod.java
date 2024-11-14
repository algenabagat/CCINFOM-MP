public class PaymentMethod {
    private int paymentMethod_id;
    private String method_name;

    // Constructor
    public PaymentMethod(int paymentMethod_id, String method_name) {
        this.paymentMethod_id = paymentMethod_id;
        this.method_name = method_name;
    }

    // Getters and Setters
    public int getpaymentMethod_id() {
        return paymentMethod_id;
    }

    public void setpaymentMethod_id(int paymentMethod_id) {
        this.paymentMethod_id = paymentMethod_id;
    }

    public String getmethod_name() {
        return method_name;
    }

    public void setmethod_name(String method_name) {
        this.method_name = method_name;
    }
}