package com.prasan.weatherapi.model;

import com.prasan.weatherapi.Service.BeanUtil;
import com.prasan.weatherapi.WeatherapiApplication;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BeanUtil.class, PrettyTime.class})
class CityWeatherReportTest {

    CityWeatherReport report;
    PrettyTime prettyTime;

    @BeforeEach
    public void setReport() {
        report = new CityWeatherReport();
        prettyTime = new PrettyTime();
    }

    @Test
    void getCityId() {
        report.setCityId(12345);
        assertEquals(12345, report.getCityId());
    }

    @Test
    void getName() {
        report.setName("colombo");
        assertEquals("colombo", report.getName());
    }

    @Test
    void getDate() {
        Date date = new Date();
        report.setDate(date);
        assertEquals(date, report.getDate());
    }

    @Test
    void getDescription() {
        report.setDescription("cloudy");
        assertEquals("cloudy", report.getDescription());
    }

    @Test
    void getTemperature() {
        report.setTemperature(87.5);
        assertEquals(87.5, report.getTemperature());
    }

    @Test
    void getPrettyDate() {
        Date date = new Date();
        report.setDate(date);

        String prettyDate = prettyTime.format(java.util.Date.from(date.toInstant()));

        assertEquals(prettyDate, report.getPrettyDate());
    }
}