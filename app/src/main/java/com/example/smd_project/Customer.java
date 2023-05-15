package com.example.smd_project;

import java.io.Serializable;

public class Customer implements Serializable {
    private int CustomerID;
    private  String Password;
    private String Name;
    private String PhoneNumber;
    private String DOB;
    private String Email;
    private Wallet cust_wallet;
    public Customer(){}
    public Customer(String name, String phoneNumber, String dob, String email,String pass) {
        Name = name;
        PhoneNumber = phoneNumber;
        DOB = dob;
        Email = email;
        Password=pass;
    }


    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public Wallet getCust_wallet() {
        return cust_wallet;
    }

    public void setCust_wallet(Wallet cust_wallet) {
        this.cust_wallet = cust_wallet;
    }


    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
