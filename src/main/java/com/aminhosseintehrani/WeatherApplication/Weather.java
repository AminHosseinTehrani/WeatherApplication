package com.aminhosseintehrani.WeatherApplication;

/**
 * May implement more later if implementing weather results history
 */
public class Weather {
    private String city;
    private double temperature;
    private int clouds;

    private String description;


    public Weather(String city, double temperature, int clouds, String description) {
        this.city = city;
        this.temperature = temperature;
        this.clouds = clouds;
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
