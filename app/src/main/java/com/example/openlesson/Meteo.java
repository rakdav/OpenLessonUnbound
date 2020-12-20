package com.example.openlesson;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Meteo {

    private String date;

    private String tod;

    private String pressure;

    private String temp;

    private String feel;

    private String humidity;

    private String wind;

    private String cloud;

    private String tid;

    public void setDate(String date) {
        this.date = date;
    }

    public void setTod(String tod) {
        this.tod = tod;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public void setFeel(String feel) {
        this.feel = feel;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public void setCloud(String cloud) {
        this.cloud = cloud;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getDate() {
        return date;
    }

    public String getTod() {
        return tod;
    }

    public String getPressure() {
        return pressure;
    }

    public String getTemp() {
        return temp;
    }

    public String getFeel() {
        return feel;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getWind() {
        return wind;
    }

    public String getCloud() {
        return cloud;
    }

    public String getTid() {
        return tid;
    }
}
