public class Discount {
    private int discount_id;
    private String discount_name;
    private float discount_percentage;

    // Constructor
    public Discount(int discount_id, String discount_name, float discount_percentage) {
        this.discount_id = discount_id;
        this.discount_name = discount_name;
        this.discount_percentage = discount_percentage;
    }

    // Getters and Setters
    public int getdiscount_id() {
        return discount_id;
    }

    public void setdiscount_id(int discount_id) {
        this.discount_id = discount_id;
    }

    public String getdiscount_name() {
        return discount_name;
    }

    public void setdiscount_name(String discount_name) {
        this.discount_name = discount_name;
    }

    public float getdiscount_percentage() {
        return discount_percentage;
    }

    public void setdiscount_percentage(float discount_percentage) {
        this.discount_percentage = discount_percentage;
    }
}