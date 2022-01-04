package com.prasan.weatherapi.Service;

import java.util.ArrayList;
import java.util.List;

import com.prasan.weatherapi.json.Result;
import com.prasan.weatherapi.model.CityWeatherReport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherInfoService {

    private Logger logger = LoggerFactory.getLogger(WeatherInfoService.class);

    private static final String BASE = "http://api.openweathermap.org/data/2.5/weather";
    private static final String KEY = "f623e6736e308dc108ee3e27d1cbcccd";

    private RestTemplate restTemplate;

    @Autowired
    public WeatherInfoService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Cacheable(value = "reportById")
    public CityWeatherReport getReport(int cityId) {
        String url = String.format("%s?id=%d&appid=%s", BASE, cityId, KEY);
        Result result = restTemplate.getForObject(url, Result.class);

        CityWeatherReport report = new CityWeatherReport();

        if (result != null) {
            report.setDescription(result.getWeather().get(0).getDescription());
            report.setTemperature(result.getMain().getTemp());
            report.setDate(result.getDate());
            report.setCityId(result.getId());
            report.setName(result.getName());
        }
        return report;
    }

    @Cacheable(value = "report")
    public List<CityWeatherReport> getReports(List<Integer> ids) {

        logger.info("called");
        List<CityWeatherReport> output = new ArrayList<>();

        for (Integer id : ids) {
            CityWeatherReport report = getReport(id);
            output.add(report);
        }
        return output;
    }
}
