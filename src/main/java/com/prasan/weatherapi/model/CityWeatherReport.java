package com.prasan.weatherapi.model;

import java.util.Date;
import com.prasan.weatherapi.Service.BeanUtil;
import org.ocpsoft.prettytime.PrettyTime;

public class CityWeatherReport {

    private String description;
    private double temperature;
    private Date date;
    private int id;
    private String name;

    public CityWeatherReport() {
    }

    public CityWeatherReport(String description, double temperature, Date date) {
        this.description = description;
        this.temperature = temperature;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPrettyTime() {
        PrettyTime pt = BeanUtil.getBean(PrettyTime.class);
        return pt.format(convertToDateViaInstant(getDate()));
    }

    private Date convertToDateViaInstant(Date dateToConvert) {
        return java.util.Date.from(dateToConvert.toInstant());
    }
}
