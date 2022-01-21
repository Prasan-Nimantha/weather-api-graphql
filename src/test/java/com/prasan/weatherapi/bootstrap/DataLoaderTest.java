package com.prasan.weatherapi.bootstrap;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ObjectMapper.class, BeanUtil.class, PrettyTime.class})
class DataLoaderTest {

    private CityWeatherReport existingReport;
    private CityWeatherReport existingReport2;

    private CityWeatherReport report;
    private CityWeatherReport report2;
    private CityWeatherReport report3;

    private DataLoader loader;
    @Mock
    private CityWeatherReportRepository repository;
    @Mock
    private WeatherInfoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        loader = new DataLoader(repository, service);

        existingReport = new CityWeatherReport();
        existingReport.setCityId(1248991);
        existingReport.setName("Colombo");
        existingReport.setDescription("cloudy");
        existingReport.setTemperature(40.0);

        existingReport2 = new CityWeatherReport();
        existingReport2.setCityId(1850147);
        existingReport2.setName("Tokyo");
        existingReport2.setDescription("dark clouds");
        existingReport2.setTemperature(67.8);

        report = new CityWeatherReport();
        report.setCityId(1248991);
        report.setName("Colombo");
        report.setDescription("rainy");
        report.setTemperature(32.6);
        report.setDate(new Date());

        report2 = new CityWeatherReport();
        report2.setCityId(1850147);
        report2.setName("Tokyo");
        report2.setDescription("dark clouds");
        report2.setTemperature(67.8);
        report2.setDate(new Date());

        report3 = new CityWeatherReport();
        report3.setCityId(2644210);
        report3.setName("Liverpool");
        report3.setDescription("no clouds");
        report3.setTemperature(70.2);
        report3.setDate(new Date());
    }

    @Test
    void run() throws Exception {

        when(repository.findByCityId(1248991)).thenReturn(existingReport);
        when(repository.findByCityId(1850147)).thenReturn(existingReport2);
        when(repository.findByCityId(2644210)).thenReturn(null);

        when(service.getReport(1248991)).thenReturn(report);
        when(repository.save(existingReport)).thenReturn(existingReport);
        when(service.getReport(1850147)).thenReturn(report2);
        when(repository.save(existingReport2)).thenReturn(existingReport2);

        when(service.getReport(2644210)).thenReturn(report3);
        when(repository.save(report3)).thenReturn(report3);

        loader.run();

        assertEquals(existingReport.getDescription(), report.getDescription());
        assertEquals("no clouds", report3.getDescription());

        verify(repository, times(1)).findByCityId(1248991);
        verify(repository, times(1)).findByCityId(1850147);
        verify(repository, times(1)).findByCityId(2644210);
        verify(service, times(1)).getReport(1248991);
        verify(service, times(1)).getReport(1850147);
        verify(service, times(1)).getReport(2644210);
        verify(repository, times(1)).save(existingReport);
        verify(repository, times(1)).save(existingReport2);
        verify(repository, times(1)).save(report3);
    }
}