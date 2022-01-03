package com.prasan.weatherapi.repository;

import com.prasan.weatherapi.model.CityWeatherReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityWeatherReportRepository extends JpaRepository<CityWeatherReport, Integer> {
    public CityWeatherReport findByCityId(Integer cityId);
}
