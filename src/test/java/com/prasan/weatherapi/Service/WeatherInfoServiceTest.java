package com.prasan.weatherapi.Service;

import com.prasan.weatherapi.model.CityWeatherReport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.ocpsoft.prettytime.PrettyTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BeanUtil.class, PrettyTime.class})
class WeatherInfoServiceTest {

    private final Logger logger = LoggerFactory.getLogger(WeatherInfoServiceTest.class);

    private WeatherInfoService service;

    @Test
    void getReport() {
        service = new WeatherInfoService(new  RestTemplateBuilder());
        CityWeatherReport report = service.getReport(524901);
        logger.info(report.toString());
        assertEquals(report.getName(), "Moscow");
    }

    @Test
    void getReports() {
        service = new WeatherInfoService(new  RestTemplateBuilder());
        List<Integer> idList = new ArrayList<>();
        idList.add(524901);
        idList.add(703448);
        List<CityWeatherReport> reportList = service.getReports(idList);
        logger.info(reportList.get(0).toString());
        assertEquals(idList.size(), 2);
        assertEquals(reportList.get(0).getName(), "Moscow");
    }
}