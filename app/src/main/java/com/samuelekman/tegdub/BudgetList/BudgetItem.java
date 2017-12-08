package com.samuelekman.tegdub.BudgetList;

/**
 * Created by samuel on 2017-12-06.
 */

public abstract class BudgetItem {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_EXPENSE = 1;
    public static final int TYPE_INCOME = 2;

    abstract public int getType();
}
