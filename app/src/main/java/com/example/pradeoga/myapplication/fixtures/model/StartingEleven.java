
package com.example.pradeoga.myapplication.fixtures.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartingEleven {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("captain")
    @Expose
    private Boolean captain;
    @SerializedName("shirt_number")
    @Expose
    private Integer shirtNumber;
    @SerializedName("position")
    @Expose
    private String position;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCaptain() {
        return captain;
    }

    public void setCaptain(Boolean captain) {
        this.captain = captain;
    }

    public Integer getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(Integer shirtNumber) {
        this.shirtNumber = shirtNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
