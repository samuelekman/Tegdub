package com.samuelekman.tegdub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.samuelekman.tegdub.Interfaces.TransactionStore;
import com.samuelekman.tegdub.model.Category;
import com.samuelekman.tegdub.model.Transaction;
import com.samuelekman.tegdub.utils.TransactionStoreFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class BudgetActivity extends AppCompatActivity {
 TransactionStore transactionStore = TransactionStoreFactory.transactionStore();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        TreeMap<String, List<Transaction>> treeMap = prepareList();
    }

}
