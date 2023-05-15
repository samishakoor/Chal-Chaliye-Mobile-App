package com.example.smd_project;

import android.location.Location;

import java.io.Serializable;
import java.util.ArrayList;

public class Service implements Serializable {
    public ArrayList<Rating> getRatings() {
        return Ratings;
    }

    public void setRatings(ArrayList<Rating> ratings) {
        Ratings = ratings;
    }

    private ArrayList<Rating> Ratings;
    private String name;
    private float price;
    private String location;
    private float AvgRating;

    public serviceType getType() {
        return Type;

    }

    public void setType(serviceType type) {
        Type = type;
    }

    private serviceType Type;

    public Service(){
        name="";
        price=0;
        location="";
        AvgRating=0;
        Ratings=new ArrayList<>();
        Type=null;
    }
    public Service(String n,float p,String loc){
        name=n;
        price=p;
        location=loc;
        AvgRating=0;
        Ratings=new ArrayList<>();
    }
    public void AddToRatings(Rating R){
        this.Ratings.add(R);
        SetAvgRating();
    }
    public void SetAvgRating(){
        float temp=0;
        if (Ratings.size()==0){
            AvgRating=temp;
        }
        else {
            for (int i = 0; i < Ratings.size(); i++) {
                temp = temp + Ratings.get(i).getRatingValue();
            }
            AvgRating=temp/Ratings.size();

        }


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getAvgRating() {
        return AvgRating;
    }

    public void setAvgRating(float avgRating) {
        AvgRating = avgRating;
    }
}
