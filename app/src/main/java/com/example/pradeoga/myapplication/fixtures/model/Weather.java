
package com.example.pradeoga.myapplication.fixtures.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("humidity")
    @Expose
    private String humidity;
    @SerializedName("temp_celsius")
    @Expose
    private String tempCelsius;
    @SerializedName("temp_farenheit")
    @Expose
    private String tempFarenheit;
    @SerializedName("wind_speed")
    @Expose
    private String windSpeed;
    @SerializedName("description")
    @Expose
    private String description;

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTempCelsius() {
        return tempCelsius;
    }

    public void setTempCelsius(String tempCelsius) {
        this.tempCelsius = tempCelsius;
    }

    public String getTempFarenheit() {
        return tempFarenheit;
    }

    public void setTempFarenheit(String tempFarenheit) {
        this.tempFarenheit = tempFarenheit;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
