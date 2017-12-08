package com.samuelekman.tegdub.BudgetList;

/**
 * Created by samuel on 2017-12-06.
 */

public class BudgetHeaderItem extends BudgetItem {
    private String mainCategory;

    @Override
    public int getType() {
        return TYPE_HEADER;
    }

    public BudgetHeaderItem(String mainCategory){
        this.mainCategory = mainCategory;
    }

    public String getHeader(){
        return mainCategory;
    }
}

