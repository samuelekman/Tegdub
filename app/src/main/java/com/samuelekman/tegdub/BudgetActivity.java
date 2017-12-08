package com.samuelekman.tegdub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.samuelekman.tegdub.BudgetList.BudgetHeaderItem;
import com.samuelekman.tegdub.BudgetList.BudgetIncomeItem;
import com.samuelekman.tegdub.BudgetList.BudgetItem;
import com.samuelekman.tegdub.BudgetList.BudgetExpenseItem;
import com.samuelekman.tegdub.Interfaces.TransactionStore;
import com.samuelekman.tegdub.model.MainCategory;
import com.samuelekman.tegdub.model.Transaction;
import com.samuelekman.tegdub.utils.TransactionStoreFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BudgetActivity extends AppCompatActivity {
 TransactionStore transactionStore = TransactionStoreFactory.transactionStore();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        TreeMap<String, List<Transaction>> treeMap = transactionStore.prepareList();

        //the following is just a test print :-:
        for (Map.Entry<String, List<Transaction>> entry : treeMap.entrySet()) {
            String key = entry.getKey();
            System.out.println(key);
            for (Transaction t : entry.getValue()) {
                System.out.println(t.toString());
            }
        }
        List<BudgetItem> mBudgetItem = makeListItems(treeMap);
    }

    public List<BudgetItem> makeListItems(TreeMap<String, List<Transaction>> tMap){
        List<BudgetItem> mListItems = new ArrayList<>();

        for(String header : tMap.keySet()) {
            BudgetHeaderItem headerItem = new BudgetHeaderItem(header);
            mListItems.add(headerItem);

            for(Transaction t : tMap.get(header)) {
                if (t.getCategory().getMainCategory() == MainCategory.INCOME) {
                    BudgetIncomeItem budgetIncomeItem = new BudgetIncomeItem(t);
                    mListItems.add(budgetIncomeItem);
                } else {
                    BudgetExpenseItem transactionItem = new BudgetExpenseItem(t);
                    mListItems.add(transactionItem);
                }
            }
        }
        return mListItems;
    }

}
