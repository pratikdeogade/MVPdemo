
package com.example.pradeoga.myapplication.fixtures.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllMatches {

    @SerializedName("venue")
    @Expose
    private String venue;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("fifa_id")
    @Expose
    private String fifaId;
    @SerializedName("weather")
    @Expose
    private Weather weather;
    @SerializedName("attendance")
    @Expose
    private String attendance;
    @SerializedName("officials")
    @Expose
    private List<String> officials = null;
    @SerializedName("stage_name")
    @Expose
    private String stageName;
    @SerializedName("home_team_statistics")
    @Expose
    private HomeTeamStatistics homeTeamStatistics;
    @SerializedName("away_team_statistics")
    @Expose
    private AwayTeamStatistics awayTeamStatistics;
    @SerializedName("home_team_country")
    @Expose
    private String homeTeamCountry;
    @SerializedName("away_team_country")
    @Expose
    private String awayTeamCountry;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("winner")
    @Expose
    private String winner;
    @SerializedName("winner_code")
    @Expose
    private String winnerCode;
    @SerializedName("home_team")
    @Expose
    private HomeTeam homeTeam;
    @SerializedName("away_team")
    @Expose
    private AwayTeam awayTeam;
    @SerializedName("home_team_events")
    @Expose
    private List<TeamEvent> homeTeamEvents = null;
    @SerializedName("away_team_events")
    @Expose
    private List<TeamEvent> awayTeamEvents = null;
    @SerializedName("last_event_update_at")
    @Expose
    private String lastEventUpdateAt;
    @SerializedName("last_score_update_at")
    @Expose
    private Object lastScoreUpdateAt;

    public String getVenue() {
        return venue;
    }


    public String getLocation() {
        return location;
    }


    public String getStatus() {
        return status;
    }


    public String getTime() {
        return time;
    }


    public String getFifaId() {
        return fifaId;
    }


    public Weather getWeather() {
        return weather;
    }


    public String getAttendance() {
        return attendance;
    }


    public List<String> getOfficials() {
        return officials;
    }


    public String getStageName() {
        return stageName;
    }


    public HomeTeamStatistics getHomeTeamStatistics() {
        return homeTeamStatistics;
    }


    public AwayTeamStatistics getAwayTeamStatistics() {
        return awayTeamStatistics;
    }


    public String getHomeTeamCountry() {
        return homeTeamCountry;
    }


    public String getAwayTeamCountry() {
        return awayTeamCountry;
    }


    public String getDatetime() {
        return datetime;
    }


    public String getWinner() {
        return winner;
    }


    public String getWinnerCode() {
        return winnerCode;
    }


    public HomeTeam getHomeTeam() {
        return homeTeam;
    }


    public AwayTeam getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(AwayTeam awayTeam) {
        this.awayTeam = awayTeam;
    }

    public List<TeamEvent> getHomeTeamEvents() {
        return homeTeamEvents;
    }

    public void setHomeTeamEvents(List<TeamEvent> homeTeamEvents) {
        this.homeTeamEvents = homeTeamEvents;
    }

    public List<TeamEvent> getAwayTeamEvents() {
        return awayTeamEvents;
    }

    public void setAwayTeamEvents(List<TeamEvent> awayTeamEvents) {
        this.awayTeamEvents = awayTeamEvents;
    }

    public String getLastEventUpdateAt() {
        return lastEventUpdateAt;
    }

    public void setLastEventUpdateAt(String lastEventUpdateAt) {
        this.lastEventUpdateAt = lastEventUpdateAt;
    }

    public Object getLastScoreUpdateAt() {
        return lastScoreUpdateAt;
    }

    public void setLastScoreUpdateAt(Object lastScoreUpdateAt) {
        this.lastScoreUpdateAt = lastScoreUpdateAt;
    }

}
