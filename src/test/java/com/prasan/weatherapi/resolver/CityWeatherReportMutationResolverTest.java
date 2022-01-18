package com.prasan.weatherapi.resolver;

import com.prasan.weatherapi.Service.BeanUtil;
import com.prasan.weatherapi.Service.WeatherInfoService;
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

@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = {BeanUtil.class, PrettyTime.class})
class CityWeatherReportMutationResolverTest {

    private CityWeatherReport report;
    private CityWeatherReport report2;
    private List<CityWeatherReport> reportList;

    @Mock
    private CityWeatherReportRepository repository;

    @Mock
    private WeatherInfoService service;

    private CityWeatherReportMutationResolver resolver;

    @BeforeEach
    public void setUp() {
        report = new CityWeatherReport();
        report.setCityId(1232);
        report.setName("colombo");
        report.setDescription("cloudy");
        report.setTemperature(87.5);
        report.setDate(new Date());

        report2 = new CityWeatherReport();
        report2.setCityId(3567);
        report2.setName("colombo");
        report2.setDescription("sunny");
        report2.setTemperature(90.0);
        report2.setDate(new Date());

        reportList = new ArrayList<>();
        reportList.add(report);
        reportList.add(report2);

        MockitoAnnotations.openMocks(this);
        resolver = new CityWeatherReportMutationResolver(repository, service);
    }

    @Test
    void updateReportById() {

        CityWeatherReport reportUpdated = new CityWeatherReport();
        reportUpdated.setCityId(1232);
        reportUpdated.setName("colombo");
        reportUpdated.setDescription("dark clouds");
        reportUpdated.setTemperature(40.0);
        reportUpdated.setDate(new Date());

        when(repository.findByCityId(1232)).thenReturn(report);
        when(service.getReport(1232)).thenReturn(reportUpdated);
        when(repository.save(report)).thenReturn(report);

        CityWeatherReport weatherReport = resolver.updateReportById(1232);

        assertNotEquals(weatherReport.getDescription(), "cloudy");
    }

    @Test
    void updateReport() {

        CityWeatherReport reportUpdated = new CityWeatherReport();
        reportUpdated.setCityId(1232);
        reportUpdated.setName("colombo");
        reportUpdated.setDescription("dark clouds");
        reportUpdated.setTemperature(40.0);

        when(repository.findByCityId(reportUpdated.getCityId())).thenReturn(report);
        when(repository.save(report)).thenReturn(report);

        CityWeatherReport weatherReport = resolver.updateReport(reportUpdated);

        assertNotEquals(weatherReport.getDescription(), "cloudy");
    }

    @Test
    void createNewReportById() {
        when(service.getReport(1232)).thenReturn(report);
        when(repository.save(report)).thenReturn(report);

        CityWeatherReport weatherReport = resolver.createNewReportById(1232);

        assertEquals(weatherReport.getName(), report.getName());
    }

    @Test
    void createNewReportsByIds() {
        List<Integer> list = new ArrayList<>();
        list.add(1232);
        list.add(3567);

        when(service.getReports(list)).thenReturn(reportList);
        when(repository.saveAll(reportList)).thenReturn(reportList);

        List<CityWeatherReport> weatherReports = resolver.createNewReportsByIds(list);

        assertEquals(weatherReports.size(), 2);
    }

    @Test
    void createNewReport() {
        when(repository.save(report)).thenReturn(report);

        CityWeatherReport weatherReport = resolver.createNewReport(report);

        assertEquals(weatherReport.getName(), report.getName());
    }

    @Test
    void deleteReportById() {
        when(repository.findByCityId(1232)).thenReturn(null);

        String msg = resolver.deleteReportById(1232);
        assertEquals(msg, "Operation was Successful");

        when(repository.findByCityId(1232)).thenReturn(report);

        String msg2 = resolver.deleteReportById(1232);
        assertEquals(msg2, "Operation failed");
    }
}