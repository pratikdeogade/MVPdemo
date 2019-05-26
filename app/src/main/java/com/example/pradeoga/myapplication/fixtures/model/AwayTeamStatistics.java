
package com.example.pradeoga.myapplication.fixtures.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AwayTeamStatistics {

    @SerializedName("attempts_on_goal")
    @Expose
    private Integer attemptsOnGoal;
    @SerializedName("on_target")
    @Expose
    private Integer onTarget;
    @SerializedName("off_target")
    @Expose
    private Integer offTarget;
    @SerializedName("blocked")
    @Expose
    private Integer blocked;
    @SerializedName("woodwork")
    @Expose
    private Integer woodwork;
    @SerializedName("corners")
    @Expose
    private Integer corners;
    @SerializedName("offsides")
    @Expose
    private Integer offsides;
    @SerializedName("ball_possession")
    @Expose
    private Integer ballPossession;
    @SerializedName("pass_accuracy")
    @Expose
    private Integer passAccuracy;
    @SerializedName("num_passes")
    @Expose
    private Integer numPasses;
    @SerializedName("passes_completed")
    @Expose
    private Integer passesCompleted;
    @SerializedName("distance_covered")
    @Expose
    private Integer distanceCovered;
    @SerializedName("balls_recovered")
    @Expose
    private Integer ballsRecovered;
    @SerializedName("tackles")
    @Expose
    private Integer tackles;
    @SerializedName("clearances")
    @Expose
    private Integer clearances;
    @SerializedName("yellow_cards")
    @Expose
    private Integer yellowCards;
    @SerializedName("red_cards")
    @Expose
    private Integer redCards;
    @SerializedName("fouls_committed")
    @Expose
    private Integer foulsCommitted;
    @SerializedName("tactics")
    @Expose
    private String tactics;
    @SerializedName("starting_eleven")
    @Expose
    private List<StartingEleven> startingEleven = null;
    @SerializedName("substitutes")
    @Expose
    private List<Substitute> substitutes = null;
    @SerializedName("country")
    @Expose
    private String country;

    public Integer getAttemptsOnGoal() {
        return attemptsOnGoal;
    }

    public void setAttemptsOnGoal(Integer attemptsOnGoal) {
        this.attemptsOnGoal = attemptsOnGoal;
    }

    public Integer getOnTarget() {
        return onTarget;
    }

    public void setOnTarget(Integer onTarget) {
        this.onTarget = onTarget;
    }

    public Integer getOffTarget() {
        return offTarget;
    }

    public void setOffTarget(Integer offTarget) {
        this.offTarget = offTarget;
    }

    public Integer getBlocked() {
        return blocked;
    }

    public void setBlocked(Integer blocked) {
        this.blocked = blocked;
    }

    public Integer getWoodwork() {
        return woodwork;
    }

    public void setWoodwork(Integer woodwork) {
        this.woodwork = woodwork;
    }

    public Integer getCorners() {
        return corners;
    }

    public void setCorners(Integer corners) {
        this.corners = corners;
    }

    public Integer getOffsides() {
        return offsides;
    }

    public void setOffsides(Integer offsides) {
        this.offsides = offsides;
    }

    public Integer getBallPossession() {
        return ballPossession;
    }

    public void setBallPossession(Integer ballPossession) {
        this.ballPossession = ballPossession;
    }

    public Integer getPassAccuracy() {
        return passAccuracy;
    }

    public void setPassAccuracy(Integer passAccuracy) {
        this.passAccuracy = passAccuracy;
    }

    public Integer getNumPasses() {
        return numPasses;
    }

    public void setNumPasses(Integer numPasses) {
        this.numPasses = numPasses;
    }

    public Integer getPassesCompleted() {
        return passesCompleted;
    }

    public void setPassesCompleted(Integer passesCompleted) {
        this.passesCompleted = passesCompleted;
    }

    public Integer getDistanceCovered() {
        return distanceCovered;
    }

    public void setDistanceCovered(Integer distanceCovered) {
        this.distanceCovered = distanceCovered;
    }

    public Integer getBallsRecovered() {
        return ballsRecovered;
    }

    public void setBallsRecovered(Integer ballsRecovered) {
        this.ballsRecovered = ballsRecovered;
    }

    public Integer getTackles() {
        return tackles;
    }

    public void setTackles(Integer tackles) {
        this.tackles = tackles;
    }

    public Integer getClearances() {
        return clearances;
    }

    public void setClearances(Integer clearances) {
        this.clearances = clearances;
    }

    public Integer getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(Integer yellowCards) {
        this.yellowCards = yellowCards;
    }

    public Integer getRedCards() {
        return redCards;
    }

    public void setRedCards(Integer redCards) {
        this.redCards = redCards;
    }

    public Integer getFoulsCommitted() {
        return foulsCommitted;
    }

    public void setFoulsCommitted(Integer foulsCommitted) {
        this.foulsCommitted = foulsCommitted;
    }

    public String getTactics() {
        return tactics;
    }

    public void setTactics(String tactics) {
        this.tactics = tactics;
    }

    public List<StartingEleven> getStartingEleven() {
        return startingEleven;
    }

    public void setStartingEleven(List<StartingEleven> startingEleven) {
        this.startingEleven = startingEleven;
    }

    public List<Substitute> getSubstitutes() {
        return substitutes;
    }

    public void setSubstitutes(List<Substitute> substitutes) {
        this.substitutes = substitutes;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
