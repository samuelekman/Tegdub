package com.samuelekman.tegdub.model;

import java.util.Calendar;
import java.util.Date;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.samuelekman.tegdub.TypeConverters.CalendarDateConverter;

/**
 * Created by samuel on 2017-11-07.
 */

@Entity(foreignKeys = {
        @ForeignKey(
                entity = Category.class,
                parentColumns = "cid",
                childColumns = "cat_id",
                onDelete = ForeignKey.CASCADE
        )
}, tableName = "transactions")
public class Transaction {
    //private String owner;
    @PrimaryKey(autoGenerate = true)
    private int t_id;
    private double sum;
    @TypeConverters(CalendarDateConverter.class)
    private Calendar date;
    private String note;
    //trying to ignore category as part of testing (build Cat obj when extracting with help from cat_id?)
    @Ignore
    private Category category;
    private int cat_id;


    @Ignore
    public Transaction(double sum, Calendar date, Category category){
        this.sum = sum;
        this.date = date;
        this.category = category;
    }
    @Ignore
    public Transaction(int t_id, double sum, Calendar date, Category category){
        this.t_id = t_id;
        this.sum = sum;
        this.date = date;
        this.category = category;
    }
    @Ignore
    public Transaction(double sum, Calendar date, Category category, String note){
        this.sum = sum;
        this.date = date;
        this.category = category;
        this.note = note;
    }

    public Transaction(double sum, Calendar date, String note, int cat_id){
        this.sum = sum;
        this.date = date;
        this.note = note;
        this.cat_id = cat_id;
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

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public Calendar getDate() {
        return date;
    }
}

