package com.prasan.weatherapi.bootstrap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prasan.weatherapi.Service.WeatherInfoService;
import com.prasan.weatherapi.bootstrap.json.Cities;
import com.prasan.weatherapi.bootstrap.json.City;

import com.prasan.weatherapi.model.CityWeatherReport;
import com.prasan.weatherapi.repository.CityWeatherReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner{

    private final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    private CityWeatherReportRepository repository;
    private WeatherInfoService service;

    @Autowired
    public DataLoader(CityWeatherReportRepository repository, WeatherInfoService service) {
        this.repository = repository;
        this.service = service;
    }

    private List<Long> getCityIdList() {

        ObjectMapper mapper = new ObjectMapper();
        List<Long> ids = new ArrayList<>();
        Cities list;

        try {
            list = mapper.readValue(new File("./src/main/resources/json-files/cities.json"), Cities.class);

            for (City city : list.getList()) {
                ids.add((long) city.getCityCode());
            }

            logger.info(ids.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ids;
    }


    @Override
    public void run(String... args) throws Exception {
        List<CityWeatherReport> list = service.getReports(getCityIdList());

        for (CityWeatherReport report : list) {
            repository.save(report);
        }
    }
}
