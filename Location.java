public class Location {
    private int locationId;
    private String streetAddress;
    private String city;
    private int postalCode;
    private String stateProvince;
    private int countryId;

    // Constructor
    public Location(int locationId, String streetAddress, String city, int postalCode, String stateProvince, int countryId) {
        this.locationId = locationId;
        this.streetAddress = streetAddress;
        this.city = city;
        this.postalCode = postalCode;
        this.stateProvince = stateProvince;
        this.countryId = countryId;
    }

    // Getters and Setters
    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public int getcountryId() {
        return countryId;
    }

    public void setcountryId(int countryId) {
        this.countryId = countryId;
    }
}