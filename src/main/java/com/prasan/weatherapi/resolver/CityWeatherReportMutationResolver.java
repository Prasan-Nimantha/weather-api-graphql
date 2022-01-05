package com.prasan.weatherapi.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.prasan.weatherapi.Service.WeatherInfoService;
import com.prasan.weatherapi.model.CityWeatherReport;
import com.prasan.weatherapi.repository.CityWeatherReportRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class CityWeatherReportMutationResolver implements GraphQLMutationResolver {

    private CityWeatherReportRepository repository;
    private WeatherInfoService service;

    @Autowired
    public CityWeatherReportMutationResolver(CityWeatherReportRepository repository, WeatherInfoService service) {
        this.repository = repository;
        this.service = service;
    }

    public CityWeatherReport updateReportById(int cityId) {
        CityWeatherReport existingReport = repository.findByCityId(cityId);
        BeanUtils.copyProperties(service.getReport(cityId), existingReport, "cityId, date");
        return repository.save(existingReport);
    }

    public CityWeatherReport updateReport(CityWeatherReport report) {
        CityWeatherReport existingReport = repository.findByCityId(report.getCityId());
        report.setDate(new Date());
        BeanUtils.copyProperties(report, existingReport, "cityId, date");
        return repository.save(report);
    }

    public CityWeatherReport createNewReportById(int cityId) {
        return repository.save(service.getReport(cityId));
    }

    public List<CityWeatherReport> createNewReportsByIds(List<Integer> cityIds) {
        List<CityWeatherReport> reportList = service.getReports(cityIds);
        return repository.saveAll(reportList);
    }

    public CityWeatherReport createNewReport(CityWeatherReport report) {
        report.setDate(new Date());
        return repository.save(report);
    }

    public String deleteReportById(int cityId) {
        repository.deleteById(cityId);

        if (repository.findByCityId(cityId) != null) {
            return "Operation failed";
        }
        return "Operation was Successful";
    }
}
