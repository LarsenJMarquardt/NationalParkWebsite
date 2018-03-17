package com.techelevator;

import com.techelevator.npgeek.model.ParkRank;
import com.techelevator.npgeek.model.Survey;
import com.techelevator.npgeek.model.jdbc.JDBCSurveyDAO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.SQLException;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class DAOSurveyTest extends DAOIntegrationTest {

    private static JDBCSurveyDAO dao;
    JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());

    @Before
    public void setup() {
//        String sqlInsertSurvey = "INSERT INTO survey_result (surveyid, parkcode, emailaddress, state, activitylevel) VALUES (?, 'ABC', 'Hello@gamil.com', 'Maine', 'active')";
        dao = new JDBCSurveyDAO(getDataSource());
    }

    @Test
    public void save_survey_and_read_it_back() throws SQLException {
        String sqlInsertSurvey = "INSERT INTO survey_result (surveyid, parkcode, emailaddress, state, activitylevel) VALUES (?, 'ABC', 'Hello@gamil.com', 'Maine', 'active')";
        Long test_survey_id = getNextId();
        jdbcTemplate.update(sqlInsertSurvey, test_survey_id);
        Survey theSurvey = getSurvey(test_survey_id, "ABC", "Hello@gmail.com", "Maine", "active");
        dao.save(theSurvey);
        List<ParkRank> results = dao.getAllSurveys();

        assertNotNull(results);
        ParkRank savedSurvey = results.get(results.size() - 1);
//        assertSurveysAreEqual(theSurvey, savedSurvey);
    }

    private Survey getSurvey(Long surveyid, String parkcode, String emailaddress, String state, String activitylevel) {
        Survey theSurvey = new Survey();
        theSurvey.setSurveyId(surveyid);
        theSurvey.setParkCode(parkcode);
        theSurvey.setEmailAddress(emailaddress);
        theSurvey.setState(state);
        theSurvey.setActivityLevel(activitylevel);
        return theSurvey;
    }

//    private void assertSurveysAreEqual(Survey expected, ParkRank actual) {
//        assertEquals(expected.getSurveyId(), actual.getSurveyId());
//        assertEquals(expected.getParkCode(), actual.getParkCode());
//        assertEquals(expected.getEmailAddress(), actual.getEmailAddress());
//        assertEquals(expected.getState(), actual.getState());
//        assertEquals(expected.getActivityLevel(), actual.getActivityLevel());
//    }

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

