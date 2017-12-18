package com.samuelekman.tegdub.model;

/**
 * Created by samuel on 2017-11-14.
 */

public enum MainCategory {
    ENTERTAINMENT(0), HOME(1), INCOME(2), MISCELLANEOUS(3), TRANSPORTATION(4), HOUSING(5), SUSTENANCE(6);

    private int code;

    MainCategory(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
