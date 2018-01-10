package com.samuelekman.tegdub.utils;

import android.content.Context;
import android.os.AsyncTask;

import com.samuelekman.tegdub.model.Transaction;

import java.util.List;

/**
 * Created by samuel on 2018-01-10.
 * This class should also handle expections, last minute fixes here.
 * TO-DO fix exceptions.
 */

public class GetTransactionsTask extends AsyncTask<Void, Integer, List<Transaction>> {
    private AppDatabase database;
    private Context context;

    public GetTransactionsTask(Context context){
        this.context = context;
    }

    @Override
    protected List<Transaction> doInBackground(Void... voids) {
        database = AppDatabase.getDatabase(context);
        return database.transactionDao().getAllTransactions();
    }
}
