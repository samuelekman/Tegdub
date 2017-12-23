package com.samuelekman.tegdub;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Calendar;

/**
 * Created by samuel on 2017-12-23.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {

    public MyPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position){
        int tempmin;
        int tempmax;
        int inst = Calendar.getInstance().get(Calendar.MONTH);
        /*
        if (position == 0){
            return BudgetFragment.newInstance(inst);
        }

        if (position < 0){
            return BudgetFragment.newInstance(inst-1);

        }

        if(position > 1){
            return BudgetFragment.newInstance(inst+1);
        }
        return null;
        */
        switch(position){
            case 0:
                return BudgetFragment.newInstance(inst);

            case 1:
                return BudgetFragment.newInstance(inst+1);

            case 2:
                return BudgetFragment.newInstance(inst+2);
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }
}
