package com.prasan.weatherapi.bootstrap.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class City {

    @JsonProperty("CityCode")
    private int cityCode;

    @JsonProperty("CityName")
    private String cityName;

    @JsonProperty("Temp")
    private double temp;

    @JsonProperty("Status")
    private String status;

    public String getStatus() {
        return status;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
