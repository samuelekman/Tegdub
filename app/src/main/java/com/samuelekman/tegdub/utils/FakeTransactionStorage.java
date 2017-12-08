package com.samuelekman.tegdub.utils;

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
}
