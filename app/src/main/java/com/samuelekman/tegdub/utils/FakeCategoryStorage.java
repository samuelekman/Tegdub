package com.samuelekman.tegdub.utils;

import com.samuelekman.tegdub.model.Category;
import com.samuelekman.tegdub.model.CategoryStore;
import com.samuelekman.tegdub.model.MainCategory;

import java.util.ArrayList;

/**
 * Created by samuel on 2017-11-23.
 */

public class FakeCategoryStorage implements CategoryStore {
    ArrayList<Category> getCategoryList(){
        ArrayList<Category> dummyList = new ArrayList<Category>();
        dummyList.add(new Category(MainCategory.HOBBY,"Painting"));
        dummyList.add(new Category(MainCategory.HOBBY,"Painting"));

    }
}
