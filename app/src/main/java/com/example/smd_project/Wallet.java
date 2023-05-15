package com.example.smd_project;

import java.io.Serializable;

public class Wallet implements Serializable {
    private String Amount;

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }
}
