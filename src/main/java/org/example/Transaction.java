package org.example;

import java.sql.SQLOutput;

public class Transaction {

String date;
String time;
String description;
String vendor;
double amount;

       //Creating Constructors


    public Transaction(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    //Creating Getters & Setters

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    //Creating ToString()
//override

    @Override
    public String toString() {
        return String.format("%-12s %-22s %-15s %10.2f",
                date, description, vendor, amount);
    }

    }










