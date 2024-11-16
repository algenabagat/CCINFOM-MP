package classes;

public class Country {
    private int countryID;
    private String countryName;

    public Country(int countryId, String countryName){
        this.countryID = countryId;
        this.countryName = countryName;
    }

    public int getCountryId(){
        return countryID;
    }

    public String getCountryName(){
        return countryName;
    }

    public String countryName(){
        return countryName;
    }

    public void setCountryId(int countryId){
        this.countryID = countryId;
    }

    public void setCountryName(String countryName){
        this.countryName = countryName;
    }
}
