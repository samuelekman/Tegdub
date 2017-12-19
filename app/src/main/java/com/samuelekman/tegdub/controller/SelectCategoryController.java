package com.samuelekman.tegdub.controller;

import android.app.Activity;

import com.samuelekman.tegdub.Interfaces.CategoryStore;
import com.samuelekman.tegdub.model.Category;
import com.samuelekman.tegdub.model.MainCategory;
import com.samuelekman.tegdub.utils.AppDatabase;
import com.samuelekman.tegdub.utils.CategoryStoreFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by samuel on 2017-11-29.
 */

public class SelectCategoryController {

    CategoryStore categoryStore = CategoryStoreFactory.categoryStore();
    TreeMap<String, List<Category>> mainCategoryListTreeMap;



    public TreeMap<String, List<Category>> prepareList(){
            List<Category> catList = categoryStore.getCategoryList();

            TreeMap<String, List<Category>> categoryTreeMap = new TreeMap<>();
            for (int i = 0; i<catList.size(); i++){
                String treeMapKey = catList.get(i).getMainCategory().toString();

                if(categoryTreeMap.containsKey(treeMapKey)) {
                    categoryTreeMap.get(treeMapKey).add(catList.get(i));

                } else {
                    List<Category> list = new ArrayList<>();
                    list.add(catList.get(i));
                    categoryTreeMap.put(treeMapKey, list);
                }
            }
    return categoryTreeMap;
    }
}
