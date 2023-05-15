package com.example.smd_project;

import java.io.Serializable;
import java.util.ArrayList;

public class Booking implements Serializable {
    private Customer cust;


    public Service getServices() {
        return Services;
    }

    public void setServices(Service services) {
        Services = services;
    }

    private Service Services;
    private String BookDate;

    private float TotalCost;

    public Booking(){
        this.cust = new Customer("temp","","","","");
        Services = new Service("",0,"");
        BookDate = "12345";
        TotalCost = 0;
    }
    public Booking(Customer cust, Service services, String bookDate, float totalCost) {
        this.cust = cust;
        Services = services;
        BookDate = bookDate;
        TotalCost = totalCost;
    }

    public Customer getCust() {
        return cust;
    }

    public void setCust(Customer cust) {
        this.cust = cust;
    }

    public String getBookDate() {
        return BookDate;
    }

    public void setBookDate(String bookDate) {
        BookDate = bookDate;
    }


    public float getTotalCost() {
        return TotalCost;
    }
    public void setTotalCost(){
       TotalCost=Services.getPrice();
    }

    public void setTotalCost(float totalCost) {
        TotalCost = totalCost;
    }
}
