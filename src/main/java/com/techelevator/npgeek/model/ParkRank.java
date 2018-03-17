package com.techelevator.npgeek.model;

public class ParkRank {

    private String parkCode;
    private String parkName;
    private Integer parkCount;

    public ParkRank(String parkCode, String parkName, Integer parkCount) {
        this.parkCode = parkCode;
        this.parkName = parkName;
        this.parkCount = parkCount;
    }

    public String getParkCode() {
        return parkCode;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public Integer getParkCount() {
        return parkCount;
    }

    public void setParkCount(Integer parkCount) {
        this.parkCount = parkCount;
    }
}
