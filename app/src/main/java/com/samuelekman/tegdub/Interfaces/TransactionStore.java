package com.samuelekman.tegdub.Interfaces;

import com.samuelekman.tegdub.model.Transaction;

import java.util.ArrayList;

/**
 * Created by samuel on 2017-11-27.
 */

public interface TransactionStore {
    ArrayList<Transaction> getTransactionList();
    void addToTransactionList();
}
