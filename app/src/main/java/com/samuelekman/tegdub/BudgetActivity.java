package com.samuelekman.tegdub;

import android.graphics.Color;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;


import com.samuelekman.tegdub.BudgetList.BudgetExpenseHeaderItem;
import com.samuelekman.tegdub.BudgetList.BudgetIncomeHeaderItem;
import com.samuelekman.tegdub.BudgetList.BudgetIncomeItem;
import com.samuelekman.tegdub.BudgetList.BudgetItem;
import com.samuelekman.tegdub.BudgetList.BudgetExpenseItem;
import com.samuelekman.tegdub.BudgetList.BudgetListAdapter;
import com.samuelekman.tegdub.Interfaces.TransactionStore;
import com.samuelekman.tegdub.model.MainCategory;
import com.samuelekman.tegdub.model.Transaction;
import com.samuelekman.tegdub.utils.TransactionStoreFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class BudgetActivity extends AppCompatActivity {

 //TransactionStore transactionStore = TransactionStoreFactory.transactionStore();
 //RecyclerView recView;
 //BudgetListAdapter adapter;
 //TreeMap <String, List<Transaction>> treeMap;
    FragmentStatePagerAdapter fragmentPagerAdapter;
    Toolbar toolbar;

 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
     ViewPager viewPager = (ViewPager) findViewById(R.id.budgetViewPager);
     fragmentPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
     viewPager.setAdapter(fragmentPagerAdapter);

     toolbar = (Toolbar) findViewById(R.id.toolbar);
     toolbar.setTitle("Budget");
     toolbar.setTitleTextColor(Color.WHITE);
        //treeMap = transactionStore.testList();

        //the following is just a test print :-:
/*
        for (Map.Entry<String, List<Transaction>> entry : treeMap.entrySet()) {
            String key = entry.getKey();
            System.out.println(key);
            for (Transaction t : entry.getValue()) {
                System.out.println(t.toString());
            }
        }
*/      /*
        //List<BudgetItem> mBudgetItem = makeListItems(treeMap);
        adapter = new BudgetListAdapter(this, mBudgetItem);
        recView = (RecyclerView) findViewById(R.id.budgetList);
        recView.setLayoutManager(new LinearLayoutManager(this));
        recView.setAdapter(adapter);
        //Initiating Default values with a calendar object.
        changeSummary(Calendar.getInstance());
        */
/*
     ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.RIGHT, ItemTouchHelper.LEFT) {
         @Override
         public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
             return false;
         }

         @Override
         public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
             if(direction == ItemTouchHelper.RIGHT){
                Calendar c = Calendar.getInstance();
                c.add(Calendar.MONTH, 1);
                changeSummary(c);
             }

             if (direction == ItemTouchHelper.LEFT){
                 Calendar c = Calendar.getInstance();
                 c.add(Calendar.MONTH, -1);
                 changeSummary(c);
             }
         }
     };

     ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
     itemTouchHelper.attachToRecyclerView(recView);
    */
    }
/*
    public List<BudgetItem> makeListItems(TreeMap<String, List<Transaction>> tMap){
        List<BudgetItem> mListItems = new ArrayList<>();


        for(String header : tMap.keySet()) {

            if (!header.equals("Incomes")) {
                BudgetExpenseHeaderItem headerItem = new BudgetExpenseHeaderItem(header);
                mListItems.add(headerItem);
            } else {
                BudgetIncomeHeaderItem headerItem = new BudgetIncomeHeaderItem(header);
                mListItems.add(headerItem);
            }

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
*/

/*
    public void changeSummary(Calendar c){
        TextView expenses = (TextView) findViewById(R.id.expenseSummaryTxt);
        TextView incomes = (TextView) findViewById(R.id.incomeSummaryTxt);
        TextView summary = (TextView) findViewById(R.id.summaryTxt);
        TextView month = (TextView) findViewById(R.id.monthTxt);
        double exp = 0;
        double inc = 0;
        double tot;

        if (treeMap != null){
            for (String mCat : treeMap.keySet()){

                for (Transaction t : treeMap.get(mCat)){
                    if(t.getCategory().getMainCategory() != MainCategory.INCOME){
                        exp = exp +t.getSum();
                    } else {
                        inc = inc + t.getSum();
                    }

                }

            }
        } else {
            // THis is an error, need to handle this.
        }
        tot = inc-exp;
        String sExpence = String.valueOf(exp);
        String sInc = String.valueOf(inc);
        String sTot = String.valueOf(tot);

        expenses.setText("Expenses: "+sExpence);
        incomes.setText("Incomes: " +sInc);
        summary.setText("Balance " +sTot);
        //Calendar c = Calendar.getInstance();
        month.setText(c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()));
    }
    */
}
