package com.example.weatherapplication;

class MeteoItem {
    private String date,image;
    private float mintemp,maxtemp,pression;
    private int humidity ;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getMintemp() {
        return mintemp;
    }

    public void setMintemp(float mintemp) {
        this.mintemp = mintemp;
    }

    public float getMaxtemp() {
        return maxtemp;
    }

    public void setMaxtemp(float maxtemp) {
        this.maxtemp = maxtemp;
    }

    public float getPression() {
        return pression;
    }

    public void setPression(float pression) {
        this.pression = pression;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}
