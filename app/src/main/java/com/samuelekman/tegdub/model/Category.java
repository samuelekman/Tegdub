package com.samuelekman.tegdub.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;


import com.samuelekman.tegdub.TypeConverters.MainCategoryConverter;

/**
 * Created by samuel on 2017-11-13.
 */
@Entity
public class Category {
    @PrimaryKey
    private int cid;
    //private String color;
    private String subCategory;
    private String icon;
    @TypeConverters(MainCategoryConverter.class)
    public MainCategory mainCategory;
    @Ignore
    private int iconId;

    @Ignore
    public Category(MainCategory mainCategory, String subCategory, String icon){
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.icon = icon;

    }

    public Category(int cid, MainCategory mainCategory, String subCategory, String icon){
        this.cid = cid;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.icon = icon;
    }

    public String getIcon(){
        return icon;
    }

    public String getSubCategory(){
        return subCategory;
    }

    public MainCategory getMainCategory(){
        return mainCategory;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public void getIconId(){

    }
}
