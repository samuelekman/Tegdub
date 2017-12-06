package com.samuelekman.tegdub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.samuelekman.tegdub.CategoryList.CategoryItem;
import com.samuelekman.tegdub.CategoryList.GroupedListAdapter;
import com.samuelekman.tegdub.CategoryList.HeaderItem;
import com.samuelekman.tegdub.CategoryList.ListItem;
import com.samuelekman.tegdub.controller.SelectCategoryController;
import com.samuelekman.tegdub.model.Category;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import java.util.List;

public class SelectCategory extends AppCompatActivity {

    RecyclerView recView;
    GroupedListAdapter adapter;
    GridLayoutManager manager;
    Category cTemp;


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
        adapter = new GroupedListAdapter(this, mListItems, new GroupedListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Category category) {

                cTemp = category;
                onBackPressed();
            }
        });
        recView = (RecyclerView) findViewById(R.id.listWithCategory);
        recView.setAdapter(adapter);
        manager = new GridLayoutManager(this, 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
            @Override
            public int getSpanSize(int position) {
                switch (adapter.getItemViewType(position)){
                    case ListItem.TYPE_HEADER:
                        return manager.getSpanCount();

                    case ListItem.TYPE_CATEGORY:
                        return 1;

                    default:
                        return -1;
                }
            }
                                  });
        recView.setLayoutManager(manager);

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

    public void onBackPressed(){
        getIntent().putExtra("Dunno", cTemp.getSubCategory());
        this.setResult(RESULT_OK, getIntent());
        super.onBackPressed();
    }
}