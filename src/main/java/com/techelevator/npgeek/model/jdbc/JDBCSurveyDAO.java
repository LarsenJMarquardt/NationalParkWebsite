package com.techelevator.npgeek.model.jdbc;

import com.techelevator.npgeek.model.ParkRank;
import com.techelevator.npgeek.model.Survey;
import com.techelevator.npgeek.model.dao.SurveyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCSurveyDAO implements SurveyDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCSurveyDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<ParkRank> getAllSurveys() {
        List<ParkRank> allRankings = new ArrayList<>();
        String sqlSelectAllSurveys = "SELECT survey_result.parkcode, park.parkname, COUNT(survey_result.parkcode) AS parkcode_occurrence " +
                "FROM survey_result JOIN park ON park.parkcode=survey_result.parkcode " +
                "GROUP BY survey_result.parkcode, park.parkname ORDER BY parkcode_occurrence DESC";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllSurveys);
        while (results.next()) {
            ParkRank rank = new ParkRank(results.getString("parkcode"), results.getString("parkname"), results.getInt("parkcode_occurrence"));
            allRankings.add(rank);
        }
        return allRankings;
    }

    @Override
    public void save(Survey survey) {
        String sqlInsertSurvey = "INSERT INTO survey_result(surveyid, parkcode, emailaddress, state, activitylevel) VALUES(?, ?, ?, ?, ?)";
        Long id = getNextId();
        jdbcTemplate.update(sqlInsertSurvey, id, survey.getParkCode(), survey.getEmailAddress(), survey.getState(), survey.getActivityLevel());
    }

    private Long getNextId() {
        String sqlSelectNextId = "SELECT NEXTVAL('seq_surveyid')";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectNextId);
        if (result.next()) {
            return result.getLong(1);
        } else {
            throw new RuntimeException("Something went wrong while getting the next product id");
        }
    }
}

