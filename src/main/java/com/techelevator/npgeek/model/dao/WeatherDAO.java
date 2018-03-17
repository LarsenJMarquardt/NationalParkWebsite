package com.techelevator.npgeek.model.dao;

import com.techelevator.npgeek.model.Weather;

import java.util.List;

public interface WeatherDAO {

    public List<Weather> getWeatherByParkCode(String parkCode);

}
