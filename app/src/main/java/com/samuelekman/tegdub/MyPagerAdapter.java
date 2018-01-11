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
    public static int pos = 0;
   

    public MyPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }


    @Override
    public Fragment getItem(int position){




        int inst = Calendar.getInstance().get(Calendar.MONTH);

        switch(position){
            case 0:
                return BudgetFragment.newInstance(Calendar.getInstance().get(Calendar.MONTH));

            case 1:
                return BudgetFragment.newInstance(Calendar.getInstance().get(Calendar.MONTH) + 1);

            default:
                return null;
        }


    }

    /*
     * Tried multiple times adding fragments dynamically, with a list of fragments. (should be able to swipe through all months)
     * Didn't get it to work, only allows 2 atm.
     */
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
