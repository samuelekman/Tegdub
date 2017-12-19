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
    ArrayList<Category> dummyList;

        public ArrayList<Category> getCategoryList(){
            if (dummyList == null) {

                dummyList = new ArrayList<Category>();
                dummyList.add(new Category(MainCategory.ENTERTAINMENT, "Painting", "ic_format_paint_black_24dp"));
                dummyList.add(new Category(MainCategory.HOME, "Furniture", "ic_local_hotel_black_24dp"));
                dummyList.add(new Category(MainCategory.HOME, "Sleep", "ic_format_paint_black_24dp"));
                dummyList.add(new Category(MainCategory.INCOME, "Salary", "ic_attach_money_black_24dp"));
                return dummyList;
            } else {
                return dummyList;
            }
    }

        @Override
        public void addToCategoryList() {
                
        }


    public Category getCategory(String s) {
        ArrayList<Category> cList = getCategoryList();
        for (Category c : cList){
            if (c.getSubCategory() != null && c.getSubCategory().contains(s)){
                return c;
            }
        }
        return null;
    }
}
