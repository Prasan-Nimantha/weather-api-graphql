package com.prasan.weatherapi.bootstrap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prasan.weatherapi.bootstrap.json.Cities;
import com.prasan.weatherapi.bootstrap.json.City;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    private final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    public List<Long> getCityIdList() {

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
}
