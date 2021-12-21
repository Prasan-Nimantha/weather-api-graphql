package com.prasan.weatherapi.controller;

import java.util.List;

import com.prasan.weatherapi.Service.WeatherInfoService;
import com.prasan.weatherapi.bootstrap.DataLoader;
import com.prasan.weatherapi.model.CityWeatherReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private DataLoader dataLoader;
    private WeatherInfoService service;

    @Autowired
    public HomeController(DataLoader dataLoader, WeatherInfoService service) {
        this.dataLoader = dataLoader;
        this.service = service;
    }

    @GetMapping("/api/home")
    public String home(Model model) {
        List<CityWeatherReport> reports = service.getReports(dataLoader.getCityIdList());
        model.addAttribute("reports", reports);
        return "home";
    }
}
