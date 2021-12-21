package com.prasan.weatherapi.bootstrap.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cities {

    @JsonProperty("List")
    private List<City> list;

    public List<City> getList() {
        return list;
    }

    public void setList(List<City> list) {
        this.list = list;
    }

}
