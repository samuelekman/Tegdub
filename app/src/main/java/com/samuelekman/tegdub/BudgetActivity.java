package com.samuelekman.tegdub;

import android.graphics.Color;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class BudgetActivity extends AppCompatActivity {

    //TransactionStore transactionStore = TransactionStoreFactory.transactionStore();
    //RecyclerView recView;
    //BudgetListAdapter adapter;
    //TreeMap <String, List<EditTransaction>> treeMap;
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

    }
}
