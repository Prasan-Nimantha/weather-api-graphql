package com.prasan.weatherapi.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.prasan.weatherapi.Service.WeatherInfoService;
import com.prasan.weatherapi.model.CityWeatherReport;
import com.prasan.weatherapi.repository.CityWeatherReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CityWeatherReportResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    private CityWeatherReportRepository repository;
    private WeatherInfoService service;

    @Autowired
    public CityWeatherReportResolver(CityWeatherReportRepository repository, WeatherInfoService service) {
        this.repository = repository;
        this.service = service;
    }

    public List<CityWeatherReport> getAllReports() {
        return repository.findAll();
    }

    public CityWeatherReport getReportByCityId(int cityId) {
       return repository.findByCityId(cityId);
    }

    public CityWeatherReport createNewReport(int cityId) {
        return repository.save(service.getReport(cityId));
    }
}
