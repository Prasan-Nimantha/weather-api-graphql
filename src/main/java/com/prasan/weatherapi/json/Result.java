package com.prasan.weatherapi.json;

import java.util.Date;
import java.util.List;

public class Result {

    private long dt;
    private int id;
    private String name;
    private Main main;
    private List<WeatherObj> weather;

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WeatherObj> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherObj> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Date getDate() {
        return new Date(dt * 1000);
    }
}
