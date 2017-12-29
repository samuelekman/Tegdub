package com.samuelekman.tegdub;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by samuel on 2017-12-23.
 */

public class MyPagerAdapter extends FragmentStatePagerAdapter{
    private static final String TAG = "MyPagerAdapter";


    public MyPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }


    @Override
    public Fragment getItem(int position){
        //int tempmin;
        //int tempmax;



        int inst = Calendar.getInstance().get(Calendar.MONTH);

        switch(position){
            case 0:
                return BudgetFragment.newInstance(inst);

            case 1:
                return BudgetFragment.newInstance(inst+1);

            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return 2;
    }

}
