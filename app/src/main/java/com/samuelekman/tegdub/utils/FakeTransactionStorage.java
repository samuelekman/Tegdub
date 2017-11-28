package com.samuelekman.tegdub.utils;

import com.samuelekman.tegdub.Interfaces.TransactionStore;
import com.samuelekman.tegdub.model.Transaction;

import java.util.ArrayList;

/**
 * Created by samuel on 2017-11-27.
 */

public class FakeTransactionStorage implements TransactionStore {

    public ArrayList<Transaction> getTransactionList(){
    return null;
    }

    public void addToTransactionList();
}
