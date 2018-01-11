package com.samuelekman.tegdub.utils.OldClasses;

import android.util.Log;

import com.samuelekman.tegdub.Interfaces.CategoryStore;
import com.samuelekman.tegdub.Interfaces.TransactionStore;
import com.samuelekman.tegdub.model.Category;
import com.samuelekman.tegdub.model.Transaction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TreeMap;
import java.util.List;

/**
 * Created by samuel on 2017-11-27.
 */

public class FakeTransactionStorage implements TransactionStore {
    ArrayList<Transaction> tList;
    CategoryStore categoryStore = CategoryStoreFactory.categoryStore();
    private static final String TAG = "FakeTransactionStorage";

    public ArrayList<Transaction> getTransactionList(){
        if (tList == null){
            tList = new ArrayList<>();
            ArrayList<Category> cList = categoryStore.getCategoryList();
            for (Category c : cList){
                tList.add(new Transaction(135.0, Calendar.getInstance(), c));
            }
            return tList;
        }
        return tList;
    }
    @Override
    public void addToTransactionList(Transaction t) {
        tList = getTransactionList();
        tList.add(t);
    };

    public TreeMap<String, List<Transaction>> prepareList(){
        List<Transaction> tList = getTransactionList();
        TreeMap<String, List<Transaction>> transactionTreeMap = new TreeMap<>();
        for (int i = 0; i<tList.size(); i++){
            String treeMapKey = tList.get(i).getCategory().getMainCategory().toString();

            if(transactionTreeMap.containsKey(treeMapKey)){
                transactionTreeMap.get(treeMapKey).add(tList.get(i));
            } else {
                List<Transaction> list = new ArrayList<>();
                list.add(tList.get(i));
                transactionTreeMap.put(treeMapKey, list);
            }
        }
        return transactionTreeMap;
    }

    public TreeMap<String, List<Transaction>> testList(){
        List<Transaction> transactionList = getTransactionList();
        TreeMap<String, List<Transaction>> tMap = new TreeMap<>();
        String inc = "Incomes";
        String exp = "Expenses";
        for (int i = 0; i<transactionList.size(); i++){
            String tMapKey = transactionList.get(i).getCategory().getMainCategory().toString();
            Log.d(TAG, "testList: KEY: " + tMapKey);

            if(tMap.containsKey(inc) && tMapKey.equals("INCOME")){
                tMap.get(inc).add(transactionList.get(i));
            } else if(tMap.containsKey(exp) && !tMapKey.equals("INCOME")) {
                tMap.get(exp).add(transactionList.get(i));
            } else {
                Log.d(TAG, "testList: i else, key = " + tMapKey);

                if (tMapKey.equals(new String("INCOME"))) {
                    List<Transaction> list = new ArrayList<>();
                    list.add(transactionList.get(i));
                    Log.d(TAG, "testList: säger att income = true");
                    tMap.put(inc, list);

                } else {
                     List<Transaction> list = new ArrayList<>();
                    Log.d(TAG, "testList: befinner sig i else");
                    list.add(transactionList.get(i));
                    tMap.put(exp, list);
                }
            }

        }
        return tMap;
    }

}
