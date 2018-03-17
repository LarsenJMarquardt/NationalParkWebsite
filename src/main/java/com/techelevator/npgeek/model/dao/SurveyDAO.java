package com.techelevator.npgeek.model.dao;

import com.techelevator.npgeek.model.ParkRank;
import com.techelevator.npgeek.model.Survey;

import java.util.List;

public interface SurveyDAO {

    public List<ParkRank> getAllSurveys();

    public void save(Survey survey);
}
