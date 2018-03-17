package com.techelevator.npgeek.model.dao;

import com.techelevator.npgeek.model.Park;

import java.util.List;

public interface ParkDAO {

    public List<Park> getAllParks();

    public Park getParkByParkCode(String parkCode);

}
