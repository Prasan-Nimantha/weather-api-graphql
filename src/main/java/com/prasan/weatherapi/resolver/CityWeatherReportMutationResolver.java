package com.prasan.weatherapi.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.prasan.weatherapi.Service.WeatherInfoService;
import com.prasan.weatherapi.model.CityWeatherReport;
import com.prasan.weatherapi.repository.CityWeatherReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class CityWeatherReportMutationResolver implements GraphQLMutationResolver {

    private CityWeatherReportRepository repository;
    private WeatherInfoService service;

    @Autowired
    public CityWeatherReportMutationResolver(CityWeatherReportRepository repository, WeatherInfoService service) {
        this.repository = repository;
        this.service = service;
    }

    public CityWeatherReport createNewReportById(int cityId) {
        return repository.save(service.getReport(cityId));
    }

    public CityWeatherReport createNewReport(CityWeatherReport report) {
        report.setDate(new Date());
        return repository.save(report);
    }
}
