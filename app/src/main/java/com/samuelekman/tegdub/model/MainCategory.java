package com.samuelekman.tegdub.model;

/**
 * Created by samuel on 2017-11-14.
 */

public enum MainCategory {
    ENTERTAINMENT("#FA58F4"), HOME("#58FA58"), INCOME("#2E2EFE"), MISCELLANEOUS("#FFFF00"), TRANSPORTATION("#FF8000"), HOUSING("#00FFFF"), SUSTENANCE("#FF0040");

    private String colorCode;

    MainCategory(String colorCode){
        this.colorCode = colorCode;
    }

    public String getColorCode(){
        return colorCode;
    }
}
