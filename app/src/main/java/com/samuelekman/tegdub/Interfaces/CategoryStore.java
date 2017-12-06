package com.samuelekman.tegdub.Interfaces;

import com.samuelekman.tegdub.model.Category;
import com.samuelekman.tegdub.model.MainCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by samuel on 2017-11-23.
 */

public interface CategoryStore {
    ArrayList<Category> getCategoryList();
    void addToCategoryList();
    Category getCategory(String s);

}
