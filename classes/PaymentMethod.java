package classes;

public class PaymentMethod {
    private int paymentMethodId;
    private String methodName;

    // Constructor
    public PaymentMethod(int paymentMethodId, String methodName) {
        this.paymentMethodId = paymentMethodId;
        this.methodName = methodName;
    }

    // Getters and Setters
    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}