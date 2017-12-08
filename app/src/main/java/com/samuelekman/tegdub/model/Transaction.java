package com.samuelekman.tegdub.model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by samuel on 2017-11-07.
 */

public class Transaction {
    private double sum;
    //private String owner;
    private Calendar date;
    private String note;
    private Category category;


    public Transaction(double sum, Calendar date, Category category){
        this.sum = sum;
        this.date = date;
        this.category = category;
    }
    public Transaction(double sum, Calendar date, Category category, String note){
        this.sum = sum;
        this.date = date;
        this.category = category;
        this.note = note;
    }
    public Category getCategory(){
        return category;
    }

    public double getSum(){
        return sum;
    }

    public String toString(){
        return "The amount" + this.sum + "\n the subCategory:\n" + this.category.getSubCategory().toString();
    }
}

