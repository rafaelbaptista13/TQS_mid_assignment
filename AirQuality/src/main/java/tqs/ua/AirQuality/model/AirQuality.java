package tqs.ua.AirQuality.model;

public class AirQuality {
    
    private double no2;
    private double pm10;
    private double pm25;
    private double co;
    private double so2;
    private double ozone;
    private int aqi;
    private String pollutant;
    private double concentration;
    private String category;
    private String city;
    private String countryCode;
    private String postalCode;

    public double getNO2() {
        return no2;
    }
    public void setNO2(double no2) {
        this.no2 = no2;
    }
    public double getPM10() {
        return pm10;
    }
    public void setPM10(double pm10) {
        this.pm10 = pm10;
    }
    public double getPM25() {
        return pm25;
    }
    public void setPM25(double pm25) {
        this.pm25 = pm25;
    }
    public double getCO() {
        return co;
    }
    public void setCO(double co) {
        this.co = co;
    }
    public double getSO2() {
        return so2;
    }
    public void setSO2(double so2) {
        this.so2 = so2;
    }
    public double getOZONE() {
        return ozone;
    }
    public void setOZONE(double ozone) {
        this.ozone = ozone;
    }
    public int getAQI() {
        return aqi;
    }
    public void setAQI(int aqi) {
        this.aqi = aqi;
    }
    public String getPollutant() {
        return pollutant;
    }
    public void setPollutant(String pollutant) {
        this.pollutant = pollutant;
    }
    public double getConcentration() {
        return concentration;
    }
    public void setConcentration(double concentration) {
        this.concentration = concentration;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountryCode() {
        return countryCode;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
}
