package com.samuelekman.tegdub;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samuelekman.tegdub.BudgetList.BudgetExpenseHeaderItem;
import com.samuelekman.tegdub.BudgetList.BudgetExpenseItem;
import com.samuelekman.tegdub.BudgetList.BudgetIncomeHeaderItem;
import com.samuelekman.tegdub.BudgetList.BudgetIncomeItem;
import com.samuelekman.tegdub.BudgetList.BudgetItem;
import com.samuelekman.tegdub.BudgetList.BudgetListAdapter;
import com.samuelekman.tegdub.model.Category;
import com.samuelekman.tegdub.model.MainCategory;
import com.samuelekman.tegdub.model.Transaction;
import com.samuelekman.tegdub.utils.AppDatabase;
import com.samuelekman.tegdub.utils.GetTransactionsTask;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;


/*
 The fragment that is inflated within the ViewPager at the BudgetActivity.

 */

public class BudgetFragment extends Fragment {
    private AppDatabase database = AppDatabase.getDatabase(getContext());
    private RecyclerView recView;
    private BudgetListAdapter adapter;
    private TreeMap<String, List<Transaction>> treeMap;
    private TextView expenses;
    private TextView incomes;
    private TextView summary;
    private TextView monthtext;


    private int month;
    private String year;
    private static final String TAG = "BudgetFragment";
    List<Transaction> mTlist;
    List<Transaction> mFirst;
    List<BudgetItem> mBudgetItem;
    Calendar cal;
    FloatingActionButton fab;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        month = getArguments().getInt("monthInt");
        cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, month);
        year = String.valueOf(cal.get(Calendar.YEAR));

    }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
      View v = inflater.inflate(R.layout.fragment_budget, container, false);


      mFirst = database.transactionDao().getAllTransactions();
      /*
      Should not do the above (use mainThread), no Time to fix it...
      mFirst = new GetTransactionsTask(getContext()).execute().get();
      */

     if (mTlist != null && treeMap != null && mBudgetItem != null) {
         mTlist.clear();
         treeMap.clear();
         mBudgetItem.clear();
     }
      mTlist = makeMonthList(mFirst);

      treeMap = convertList(mTlist);

      mBudgetItem = makeListItems(treeMap);

      expenses = (TextView) v.findViewById(R.id.expenseSummaryTxt);
      incomes = (TextView) v.findViewById(R.id.incomeSummaryTxt);
      summary = (TextView) v.findViewById(R.id.summaryTxt);
      monthtext = (TextView) v.findViewById(R.id.monthTxt);
      fab = (FloatingActionButton) v.findViewById(R.id.fab);

      fab.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View view){
              Intent intent = new Intent(getActivity(), CreateEntry.class);
              intent.putExtra("ActivityID", "BudgetFragment");
              startActivity(intent);
          }

      });



      adapter = new BudgetListAdapter(mBudgetItem, new BudgetListAdapter.OnItemClickListener(){
          @Override
          public void onItemClick(Transaction transaction) {
              Calendar c = transaction.getDate();
              Log.d(TAG, "onItemClick: År " + String.valueOf(c.get(Calendar.YEAR)));
              Log.d(TAG, "onItemClick: månad" + c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()));

          }

      });

      recView = (RecyclerView) v.findViewById(R.id.budgetList);
      recView.setLayoutManager(new LinearLayoutManager(getContext()));
      recView.setAdapter(adapter);
      changeSummary(month);


      return v;

  }

    /*
    This method changes the Summary at the top of the view.
     */

    public void changeSummary(int monthIn){

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
            // There are no transactions, should probably say so in the UI (RecyclerView).
        }
        tot = inc-exp;
        String sExpence = String.valueOf(exp);
        String sInc = String.valueOf(inc);
        String sTot = String.valueOf(tot);

        expenses.setText("Expenses: "+sExpence);
        incomes.setText("Incomes: " +sInc);
        summary.setText("Balance " +sTot);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, monthIn);
        monthtext.setText(c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " " + String.valueOf(c.get(Calendar.YEAR)));
        year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
    }

    /*
     Method that creates a new instance of the class.
     Takes a int as a parameter (calendar.MONTH)
     */
    public static BudgetFragment newInstance(int month) {
        BudgetFragment budgetFragment = new BudgetFragment();
        Bundle args = new Bundle();
        args.putInt("monthInt", month);
        budgetFragment.setArguments(args);
        return budgetFragment;
    }

    /*
    The following method aint pretty..
    It takes a list of transactions as argument and then returns a TreeMap with Strings as Keys. either Income or Expenses.
    The TreeMap is later used to make budgetItems.
     */
    public TreeMap<String, List<Transaction>> convertList(List<Transaction> transactionList){

        TreeMap<String, List<Transaction>> tMap = new TreeMap<>();
        String inc = "Incomes";
        String exp = "Expenses";
        for (int i = 0; i<transactionList.size(); i++){

           String tMapKey = transactionList.get(i).getCategory().getMainCategory().toString();
            Log.d(TAG, "testList: KEY: " + tMapKey);
            /*
            Checks if the transactions mainCategory already is a key within the treeMap.
            If true put the transaction into correct key (Income or Expenses)
             */
            if(tMap.containsKey(inc) && tMapKey.equals("INCOME")){
                tMap.get(inc).add(transactionList.get(i));
            } else if(tMap.containsKey(exp) && !tMapKey.equals("INCOME")) {
                tMap.get(exp).add(transactionList.get(i));
            } else {

                /*
                The transactions mainCategory isn't present within the TreeMap, add it.
                 */

                if (tMapKey.equals("INCOME")) {
                    List<Transaction> list = new ArrayList<>();
                    list.add(transactionList.get(i));

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

    /*
    The following method takes a TreeMap as argument, loops through the TreeMap and returns a list with BudgetItems.
     */
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

    /*
    The following method is my QuickFix for not being able to get transactions for a certain month (read my report).
    It checks the list of transactions too see if the year and date on each transactions is this year and this month, if true add it to the list.
    The second for loop maps transactions to the correct Category.
     */

    List<Transaction> makeMonthList(List<Transaction> tList){
        List<Transaction> tranList = new ArrayList<>();
        for (Transaction t : tList){
            String mYear = String.valueOf(t.getDate().get(Calendar.YEAR));
            int mMonth = t.getDate().get(Calendar.MONTH);
            if(year.equals(mYear) && mMonth == month){
                tranList.add(t);

            }
        }// End of for loop
        for(Transaction t : tranList){
            Category c = database.categoryDao().getCategory(t.getCat_id());
            t.setCategory(c);

        }
        return tranList;
    }



}