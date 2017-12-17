package com.samuelekman.tegdub.model;

import java.util.Calendar;
import java.util.Date;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
/**
 * Created by samuel on 2017-11-07.
 */

@Entity(foreignKeys = {
        @ForeignKey(
                entity = Category.class,
                parentColumns = "cid",
                childColumns = "cat_id",
                onDelete = ForeignKey.CASCADE;
        )
})
public class Transaction {
    //private String owner;
    @PrimaryKey(autoGenerate = true);
    private int t_id;
    private double sum;
    private Calendar date;
    private String note;
    private Category category;
    private int cat_id;



    public Transaction(double sum, Calendar date, Category category){
        this.sum = sum;
        this.date = date;
        this.category = category;
    }

    public Transaction(int t_id, double sum, Calendar date, Category category){
        this.t_id = t_id;
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

