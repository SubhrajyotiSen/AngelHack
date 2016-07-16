package com.example.angelhack;


import java.util.ArrayList;

public class PlaceModel {

    private ArrayList<String> places;
    private String lat;
    private String lon;

    public ArrayList<String> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<String> places) {
        this.places = new ArrayList<>();
        for (String place : places)
            this.places.add(place);
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
