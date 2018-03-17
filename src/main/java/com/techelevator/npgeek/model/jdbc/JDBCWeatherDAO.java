package com.techelevator.npgeek.model.jdbc;

import com.techelevator.npgeek.model.Weather;
import com.techelevator.npgeek.model.dao.WeatherDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCWeatherDAO implements WeatherDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCWeatherDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Weather> getWeatherByParkCode(String parkCode) {
        List<Weather> weatherForecast = new ArrayList<>();
        String sqlSelectWeatherByParkCode = "SELECT * FROM weather WHERE parkcode = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectWeatherByParkCode, parkCode);
        while (results.next()) {
            Weather weather = new Weather();
            weather.setParkCode(results.getString("parkcode"));
            weather.setFiveDayForecastValue(results.getInt("fivedayforecastvalue"));
            weather.setLow(results.getInt("low"));
            weather.setHigh(results.getInt("high"));
            weather.setForecast(results.getString("forecast"));
            weatherForecast.add(weather);
        }
        return weatherForecast;
    }
}
