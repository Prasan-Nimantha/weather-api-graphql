package com.prasan.weatherapi.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.prasan.weatherapi.model.CityWeatherReport;
import com.prasan.weatherapi.repository.CityWeatherReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CityWeatherReportQueryResolver implements GraphQLQueryResolver {

    private CityWeatherReportRepository repository;

    @Autowired
    public CityWeatherReportQueryResolver(CityWeatherReportRepository repository) {
        this.repository = repository;
    }

    public List<CityWeatherReport> getAllReports() {
        return repository.findAll();
    }

    public CityWeatherReport getReportByCityId(int cityId) {
       return repository.findByCityId(cityId);
    }
}
