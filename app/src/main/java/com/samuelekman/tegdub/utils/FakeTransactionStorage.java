package com.samuelekman.tegdub.utils;

import com.samuelekman.tegdub.Interfaces.TransactionStore;
import com.samuelekman.tegdub.model.Transaction;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by samuel on 2017-11-27.
 */

public class FakeTransactionStorage implements TransactionStore {
    ArrayList<Transaction> tList;

    public ArrayList<Transaction> getTransactionList(){
        if (tList == null){
            tList = new ArrayList<>();
            return tList;
        }
        return tList;
    }
    @Override
    public void addToTransactionList(Transaction t) {
        tList = getTransactionList();
        tList.add(t);
    };
}
