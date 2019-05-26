
package com.example.pradeoga.myapplication.fixtures.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamEvent {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("type_of_event")
    @Expose
    private String typeOfEvent;
    @SerializedName("player")
    @Expose
    private String player;
    @SerializedName("time")
    @Expose
    private String time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeOfEvent() {
        return typeOfEvent;
    }

    public void setTypeOfEvent(String typeOfEvent) {
        this.typeOfEvent = typeOfEvent;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
