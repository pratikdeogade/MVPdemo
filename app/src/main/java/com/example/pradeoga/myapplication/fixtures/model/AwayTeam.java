
package com.example.pradeoga.myapplication.fixtures.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AwayTeam {

    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("goals")
    @Expose
    private Integer goals;
    @SerializedName("penalties")
    @Expose
    private Integer penalties;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getGoals() {
        return goals;
    }

    public void setGoals(Integer goals) {
        this.goals = goals;
    }

    public Integer getPenalties() {
        return penalties;
    }

    public void setPenalties(Integer penalties) {
        this.penalties = penalties;
    }

}
