package com.samuelekman.tegdub.Interfaces;

import com.samuelekman.tegdub.model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by samuel on 2017-11-27.
 */

public interface TransactionStore {
    ArrayList<Transaction> getTransactionList();
    void addToTransactionList(Transaction t);

    TreeMap<String,List<Transaction>> prepareList();

    TreeMap<String,List<Transaction>> testList();
}
