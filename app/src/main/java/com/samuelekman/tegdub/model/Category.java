package com.samuelekman.tegdub.model;

/**
 * Created by samuel on 2017-11-13.
 */

public class Category {
    private String color;
    private String subCategory;
    private int icon;
    MainCategory mainCategory;


    public Category(MainCategory mainCategory, String subCategory, int icon){
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.icon = icon;

    }

    public int getIcon(){
        return icon;
    }


}
