package classes;

public class Discount {
    private int discountID;
    private String discountName;
    private float discountPercentage;

    // Constructor
    public Discount(int discountID, String discountName, float discountPercentage) {
        this.discountID = discountID;
        this.discountName = discountName;
        this.discountPercentage = discountPercentage;
    }

    // Getters and Setters
    public int getDiscountID() {
        return discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public float getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}