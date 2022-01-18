package com.prasan.weatherapi.model;

import java.util.Date;
import com.prasan.weatherapi.Service.BeanUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ocpsoft.prettytime.PrettyTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@NoArgsConstructor
public class CityWeatherReport {
    @Id @Getter @Setter
    private int cityId;

    @Transient @Getter
    private Date date;

    @Getter @Setter
    private String description;

    @Getter @Setter
    private double temperature;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String prettyDate;

    public void setDate(Date date) {
        this.date = date;
        this.prettyDate = getPrettyTime();
    }

    private String getPrettyTime() {
        PrettyTime pt = BeanUtil.getBean(PrettyTime.class);
        return pt.format(convertToDateViaInstant(getDate()));
    }

    private Date convertToDateViaInstant(Date dateToConvert) {
        return java.util.Date.from(dateToConvert.toInstant());
    }
}
