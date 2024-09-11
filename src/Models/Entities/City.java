package Models.Entities;

public class City {

    private int id;
    private String cityName;

    public City() {
    }

    public City(int id, String cityName) {
        this.id = id;
        this.cityName = cityName;
    }

    // getters and setters

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id ;
    }
    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
