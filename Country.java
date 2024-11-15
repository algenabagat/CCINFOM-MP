public class Country {
    private int country_id;
    String country_name;

    public Country(int countryId, String countryName){
        this.country_id = countryId;
        this.country_name = countryName;
    }

    public int getCountryId(){
        return country_id;
    }

    public String getCountryName(){
        return country_name;
    }

    public String countryName(){
        return country_name;
    }

    public void setCountryId(int countryId){
        this.country_id = countryId;
    }

    public void setCountryName(String countryName){
        this.country_name = countryName;
    }
}
