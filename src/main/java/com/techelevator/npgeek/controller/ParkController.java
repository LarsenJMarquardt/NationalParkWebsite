package com.techelevator.npgeek.controller;

import com.techelevator.npgeek.model.Recommendation;
import com.techelevator.npgeek.model.Survey;
import com.techelevator.npgeek.model.TemperaturePreference;
import com.techelevator.npgeek.model.Weather;
import com.techelevator.npgeek.model.dao.ParkDAO;
import com.techelevator.npgeek.model.dao.SurveyDAO;
import com.techelevator.npgeek.model.dao.WeatherDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("temperaturePreference")
public class ParkController {
    ParkDAO parkDAO;
    SurveyDAO surveyDAO;
    WeatherDAO weatherDAO;

    @Autowired
    public ParkController(ParkDAO parkDAO, SurveyDAO surveyDAO, WeatherDAO weatherDAO) {
        this.parkDAO = parkDAO;
        this.surveyDAO = surveyDAO;
        this.weatherDAO = weatherDAO;
    }

    @RequestMapping("/")
    public String displayHomePage(HttpServletRequest request, ModelMap model) {
        if (model.get("temperaturePreference") == null) {
            TemperaturePreference temperaturePreference = new TemperaturePreference();
            temperaturePreference.setTemperaturePreference("Fahrenheit");
            request.setAttribute("temperaturePreference", temperaturePreference.getTemperaturePreference());
            request.setAttribute("getAllParks", parkDAO.getAllParks());
            model.addAttribute("temperaturePreference", temperaturePreference);
        } else {
            TemperaturePreference temperaturePreference = (TemperaturePreference) model.get("temperaturePreference");
            request.setAttribute("temperaturePreference", temperaturePreference.getTemperaturePreference());
            request.setAttribute("getAllParks", parkDAO.getAllParks());
        }
        return "homePage";
    }

    @RequestMapping("/parkDetail")
    public String displayParkDetailPage(HttpServletRequest request, ModelMap model) {
        TemperaturePreference temperaturePreference = (TemperaturePreference) model.get("temperaturePreference");
        String parkCode = request.getParameter("parkCode");
        List<Weather> weatherForRecommendation = weatherDAO.getWeatherByParkCode(parkCode);
        List<String> recommendationResult = new ArrayList<>();
        for (int i = 0; i < weatherForRecommendation.size(); i++) {
            String dayIndicator = "Recommendation for Day " + (i + 1) + ": ";
            String recommendationGivenWeather = Recommendation.getRECOMMENDATION().get(weatherForRecommendation.get(i).getForecast());
            String recommendationGivenTemperature;
            Integer high = weatherForRecommendation.get(i).getHigh();
            Integer low = weatherForRecommendation.get(i).getLow();
            Integer difference = high - low;
            recommendationResult.add(dayIndicator);
            if (recommendationGivenWeather == null && high <= 75 && low >= 20 && difference >= 20) {
                recommendationResult.add("Enjoy your trip!");
            } else {
                if(recommendationGivenWeather != null) {
                    recommendationResult.add(recommendationGivenWeather);
                }
                if (high > 75) {
                    recommendationGivenTemperature = Recommendation.getRECOMMENDATION().get("temperature high");
                    recommendationResult.add(recommendationGivenTemperature);
                }
                if (low < 20) {
                    recommendationGivenTemperature = Recommendation.getRECOMMENDATION().get("temperature low");
                    recommendationResult.add(recommendationGivenTemperature);
                }
                if (difference < 20) {
                    recommendationGivenTemperature = Recommendation.getRECOMMENDATION().get("temperature difference");
                    recommendationResult.add(recommendationGivenTemperature);
                }
            }
        }

        if (request.getParameter("temperaturePreference").equals("Fahrenheit")) {
            request.setAttribute("park", parkDAO.getParkByParkCode(parkCode));
            request.setAttribute("weather", weatherForRecommendation);
            request.setAttribute("recommendation", recommendationResult);
            temperaturePreference.setTemperaturePreference(request.getParameter("temperaturePreference"));
        }

        if (request.getParameter("temperaturePreference").equals("Celsius")) {
            request.setAttribute("park", parkDAO.getParkByParkCode(parkCode));
            request.setAttribute("recommendation", recommendationResult);
            for (Weather element : weatherForRecommendation) {
                element.setHigh((element.getHigh() - 32) * 5 / 9);
                element.setLow((element.getLow() - 32) * 5 / 9);
            }
            request.setAttribute("weather", weatherForRecommendation);
            temperaturePreference.setTemperaturePreference(request.getParameter("temperaturePreference"));
        }
        return "parkDetail";
    }

    @RequestMapping(path = "/survey", method = RequestMethod.GET)
    public String displaySurveyPage(Model modelHolder) {
        if (!modelHolder.containsAttribute("newSurvey")) {
            modelHolder.addAttribute("newSurvey", new Survey());
        }
        return "survey";
    }

    @RequestMapping(path = "/survey", method = RequestMethod.POST)
    public String displaySurveyPage(@Valid @ModelAttribute("newSurvey") Survey newSurvey,
                                    BindingResult result,
                                    @RequestParam String parkCode,
                                    @RequestParam String emailAddress,
                                    @RequestParam String state,
                                    @RequestParam String activityLevel) {
        if (result.hasErrors()) {
            return "/survey";
        }
        newSurvey = new Survey();
        newSurvey.setParkCode(parkCode);
        newSurvey.setEmailAddress(emailAddress);
        newSurvey.setState(state);
        newSurvey.setActivityLevel(activityLevel);
        surveyDAO.save(newSurvey);
        return "redirect:/surveyResult";
    }

    @RequestMapping("/surveyResult")
    public String displaySurveyResultPage(HttpServletRequest request) {
        request.setAttribute("getAllSurveys", surveyDAO.getAllSurveys());
        return "surveyResult";
    }
}

