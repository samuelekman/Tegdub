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
}
