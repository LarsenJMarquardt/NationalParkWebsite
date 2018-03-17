package com.techelevator.npgeek.model;

import java.util.HashMap;
import java.util.Map;

public class Recommendation {
    private static final Map<String, String> RECOMMENDATION = new HashMap() {
        {
            put("snow", "We recommend to pack snowshoes.");
            put("rain", "We recommend to pack rain gear and wear waterproof shoes.");
            put("thunderstorms", "We recommend to seek shelter and avoid hiking on exposed ridges.");
            put("sun", "We recommend to pack sunblock");
            put("temperature high", "We recommend to bring an extra gallon of water.");
            put("temperature difference", "We recommend to wear breathable layers.");
            put("temperature low", "Warning: Dangers of exposure to frigid temperatures.");
        }
    };

    public static Map<String, String> getRECOMMENDATION() {
        return RECOMMENDATION;
    }
}
