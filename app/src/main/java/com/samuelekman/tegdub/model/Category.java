package com.samuelekman.tegdub.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by samuel on 2017-11-13.
 */
@Entity
public class Category {
    @PrimaryKey
    private int cid;
    private String color;
    private String subCategory;
    private int icon;
    MainCategory mainCategory;


    public Category(MainCategory mainCategory, String subCategory, int icon){
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.icon = icon;

    }

    public Category(int cid, MainCategory mainCategory, String subCategory, int icon){
        this.cid = cid;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.icon = icon;
    }

    public int getIcon(){
        return icon;
    }

    public String getSubCategory(){
        return subCategory;
    }

    public MainCategory getMainCategory(){
        return mainCategory;
    }
}
