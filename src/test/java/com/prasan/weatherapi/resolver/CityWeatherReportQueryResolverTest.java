package com.prasan.weatherapi.resolver;

import com.prasan.weatherapi.Service.BeanUtil;
import com.prasan.weatherapi.model.CityWeatherReport;
import com.prasan.weatherapi.repository.CityWeatherReportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BeanUtil.class, PrettyTime.class})
class CityWeatherReportQueryResolverTest {

    CityWeatherReport report;
    CityWeatherReport report2;
    CityWeatherReport report3;

    List<CityWeatherReport> reportList;

    @Mock
    CityWeatherReportRepository repository;
    CityWeatherReportQueryResolver resolver;

    @BeforeEach
    public void setReports() {
        report = new CityWeatherReport();
        report.setCityId(1232);
        report.setName("colombo");
        report.setDescription("cloudy");
        report.setTemperature(87.5);
        report.setDate(new Date());

        report2 = new CityWeatherReport();
        report2.setCityId(3567);
        report2.setName("mumbai");
        report2.setDescription("sunny");
        report2.setTemperature(90.0);
        report2.setDate(new Date());

        report3 = new CityWeatherReport();
        report3.setCityId(5678);
        report3.setName("sydney");
        report3.setDescription("rainy");
        report3.setTemperature(30.0);
        report3.setDate(new Date());

        reportList = new ArrayList<>();
        reportList.add(report);
        reportList.add(report2);
        reportList.add(report3);

        MockitoAnnotations.openMocks(this);
        resolver = new CityWeatherReportQueryResolver(repository);
    }

    @Test
    void getAllReports() {
        when(repository.findAll()).thenReturn(reportList);

        List<CityWeatherReport> reports = resolver.getAllReports();
        assertEquals(reports.size(), 3);
    }

    @Test
    void getReportByCityId() {
        when(repository.findByCityId(1232)).thenReturn(report);

        CityWeatherReport weatherReport = resolver.getReportByCityId(1232);
        assertEquals(weatherReport.getName(), report.getName());
    }
}