package com.samuelekman.tegdub.BudgetList;

import com.samuelekman.tegdub.model.Transaction;

/**
 * Created by samuel on 2017-12-06.
 */

public class BudgetIncomeItem extends BudgetItem {
    private Transaction transaction;

    public BudgetIncomeItem(Transaction transaction){
        this.transaction = transaction;
    }

    public Transaction getTransaction(){
        return transaction;
    }

    public int getType(){
        return TYPE_INCOME;
    }
}
