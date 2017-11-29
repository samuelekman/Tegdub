package com.samuelekman.tegdub.utils;

import com.samuelekman.tegdub.R;
import com.samuelekman.tegdub.model.Category;
import com.samuelekman.tegdub.Interfaces.CategoryStore;
import com.samuelekman.tegdub.model.MainCategory;

import java.util.ArrayList;

/**
 * Created by samuel on 2017-11-23.
 */

public class FakeCategoryStorage implements CategoryStore {
        public ArrayList<Category> getCategoryList(){
        ArrayList<Category> dummyList = new ArrayList<Category>();
        dummyList.add(new Category(MainCategory.HOBBY,"Painting", R.drawable.ic_format_paint_black_24dp));
        dummyList.add(new Category(MainCategory.HOME,"Furniture", R.drawable.ic_local_hotel_black_24dp));
        dummyList.add(new Category(MainCategory.HOME,"Sleep", R.drawable.ic_format_paint_black_24dp));
        return dummyList;
    }

        @Override
        public void addToCategoryList() {
                
        }

}
