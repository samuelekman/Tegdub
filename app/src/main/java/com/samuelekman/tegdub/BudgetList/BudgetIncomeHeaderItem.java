package com.samuelekman.tegdub.BudgetList;

/**
 * Created by samuel on 2017-12-06.
 */

public class BudgetIncomeHeaderItem extends BudgetItem {
    private String mainCategory;

    @Override
    public int getType() {
        return TYPE_INCOME_HEADER;
    }

    public BudgetIncomeHeaderItem(String mainCategory){
        this.mainCategory = mainCategory;
    }

    public String getHeader(){
        return mainCategory;
    }
}

