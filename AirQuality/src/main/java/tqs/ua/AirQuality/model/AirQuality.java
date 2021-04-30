package tqs.ua.AirQuality.model;

import java.util.Date;

public class AirQuality {
    
    private double NO2;
    private double PM10;
    private double PM25;
    private double CO;
    private double SO2;
    private double OZONE;
    private int AQI;
    //private Date updatedAt;
    private String pollutant;
    private double concentration;
    private String category;

    public double getNO2() {
        return NO2;
    }
    public void setNO2(double nO2) {
        NO2 = nO2;
    }
    public double getPM10() {
        return PM10;
    }
    public void setPM10(double pM10) {
        PM10 = pM10;
    }
    public double getPM25() {
        return PM25;
    }
    public void setPM25(double pM25) {
        PM25 = pM25;
    }
    public double getCO() {
        return CO;
    }
    public void setCO(double cO) {
        CO = cO;
    }
    public double getSO2() {
        return SO2;
    }
    public void setSO2(double sO2) {
        SO2 = sO2;
    }
    public double getOZONE() {
        return OZONE;
    }
    public void setOZONE(double oZONE) {
        OZONE = oZONE;
    }
    public int getAQI() {
        return AQI;
    }
    public void setAQI(int aQI) {
        AQI = aQI;
    }
    /*
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    */
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

    
}