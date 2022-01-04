package com.prasan.weatherapi.model;

import java.util.Date;
import com.prasan.weatherapi.Service.BeanUtil;
import org.ocpsoft.prettytime.PrettyTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class CityWeatherReport {
    @Id
    private int cityId;

    @Transient
    private Date date;

    private String description;
    private double temperature;
    private String name;
    private String prettyDate;

    public CityWeatherReport() {
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
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
        this.prettyDate = getPrettyTime();
    }

    public String getPrettyDate() {
        return prettyDate;
    }

    private String getPrettyTime() {
        PrettyTime pt = BeanUtil.getBean(PrettyTime.class);
        return pt.format(convertToDateViaInstant(getDate()));
    }

    private Date convertToDateViaInstant(Date dateToConvert) {
        return java.util.Date.from(dateToConvert.toInstant());
    }
}
