package com.prasan.weatherapi.controller;

import java.util.List;

import com.prasan.weatherapi.Service.WeatherInfoService;
import com.prasan.weatherapi.model.City;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CityWeatherController {

    private final Logger logger = LoggerFactory.getLogger(CityWeatherController.class);

    private WeatherInfoService service;

    @Autowired
    public CityWeatherController(WeatherInfoService service) {
        this.service = service;
    }

    @GetMapping("/api/{ids}")
    public String getCityWeatherReports(@PathVariable List<Long> ids, Model model) {
        logger.info("testing the cache");
        model.addAttribute("reports", service.getReports(ids));
        return "reportlist";
    }

    @GetMapping("/api/form")
    public String getCityIdForm(Model model, @ModelAttribute City city) {
        model.addAttribute("city", city);
        return "cityidform";
    }

    @PostMapping("/api/city")
    public String getCityWeatherReport(City city, Model model) {

        if (city == null) {
            logger.info("There were errors try again!");
            model.addAttribute("city", city);
            return "cityidform";
        } else {
            model.addAttribute("report", service.getReport(city.getCityId()));
            return "reportbycity";
        }
    }
}
