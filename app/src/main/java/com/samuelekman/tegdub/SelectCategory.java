package com.samuelekman.tegdub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.samuelekman.tegdub.Interfaces.HeaderItem;
import com.samuelekman.tegdub.Interfaces.ListItem;
import com.samuelekman.tegdub.controller.SelectCategoryController;
import com.samuelekman.tegdub.model.Category;
import com.samuelekman.tegdub.model.MainCategory;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import java.util.List;

public class SelectCategory extends AppCompatActivity {

    RecyclerView recView;
    GroupedListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);

        SelectCategoryController selectCategoryController = new SelectCategoryController();
        TreeMap<String, List<Category>> treeMap = selectCategoryController.prepareList();

        for (Map.Entry<String, List<Category>> entry : treeMap.entrySet()) {
            String key = entry.getKey();
            System.out.println(key);
            for (Category c : entry.getValue()) {
                System.out.println(c.getSubCategory());
            }
        }
        List<ListItem> mListItems = makeListItems(treeMap);
        adapter = new GroupedListAdapter(this, mListItems);
        recView = (RecyclerView) findViewById(R.id.listWithCategory);
        recView.setAdapter(adapter);
        recView.setLayoutManager(new LinearLayoutManager(this));
    }

    public List<ListItem> makeListItems(TreeMap<String, List<Category>> tMap){
        List<ListItem> mListItems = new ArrayList<>();

        for(String header : tMap.keySet()) {
            HeaderItem headerItem = new HeaderItem(header);
            mListItems.add(headerItem);

            for(Category c : tMap.get(header)) {
                CategoryItem categoryItem = new CategoryItem(c);
                mListItems.add(categoryItem);
            }
        }
        return mListItems;
    }
}