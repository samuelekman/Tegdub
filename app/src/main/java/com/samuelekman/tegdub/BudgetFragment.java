package com.samuelekman.tegdub;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
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
import com.samuelekman.tegdub.model.MainCategory;
import com.samuelekman.tegdub.model.Transaction;
import com.samuelekman.tegdub.utils.AppDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;


public class BudgetFragment extends Fragment {
    AppDatabase database = AppDatabase.getDatabase(getContext());
    RecyclerView recView;
    BudgetListAdapter adapter;
    private TreeMap<String, List<Transaction>> treeMap;
    TextView expenses;
    TextView incomes;
    TextView summary;
    TextView monthTxt;

    private int month;
    private static final String TAG = "BudgetFragment";


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        month = getArguments().getInt("monthInt");
    }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
      View v = inflater.inflate(R.layout.fragment_budget, container, false);

      Calendar c = Calendar.getInstance();
      List<Transaction> mTlist = database.transactionDao().getTransactionMonth(month);
      treeMap = convertList(mTlist);
      List<BudgetItem> mBudgetItem = makeListItems(treeMap);

      expenses = (TextView) v.findViewById(R.id.expenseSummaryTxt);
      incomes = (TextView) v.findViewById(R.id.incomeSummaryTxt);
      summary = (TextView) v.findViewById(R.id.summaryTxt);
      monthTxt = (TextView) v.findViewById(R.id.monthTxt);

      adapter = new BudgetListAdapter(mBudgetItem);

      recView = (RecyclerView) v.findViewById(R.id.budgetList);
      recView.setLayoutManager(new LinearLayoutManager(getContext()));
      recView.setAdapter(adapter);
      changeSummary(month);


      return v;

  }



    public void changeSummary(int month){

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
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, month);
        monthTxt.setText(c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()));
    }

    public static BudgetFragment newInstance(int month) {
        BudgetFragment budgetFragment = new BudgetFragment();
        Bundle args = new Bundle();
        args.putInt("monthInt", month);
        budgetFragment.setArguments(args);
        return budgetFragment;
    }

    public TreeMap<String, List<Transaction>> convertList(List<Transaction> transactionList){

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

}