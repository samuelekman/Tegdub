package com.samuelekman.tegdub.model;

/**
 * Created by samuel on 2017-11-13.
 */

public class Category {
    private String color;
    private String subCategory;
    private String icon;
    MainCategory mainCategory;


    public Category(MainCategory mainCategory, String subCategory){
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;

    }
}
