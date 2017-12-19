package com.samuelekman.tegdub;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.samuelekman.tegdub.CategoryList.CategoryItem;
import com.samuelekman.tegdub.CategoryList.GroupedListAdapter;
import com.samuelekman.tegdub.CategoryList.HeaderItem;
import com.samuelekman.tegdub.CategoryList.ListItem;
import com.samuelekman.tegdub.controller.SelectCategoryController;
import com.samuelekman.tegdub.model.Category;
import com.samuelekman.tegdub.utils.AppDatabase;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import java.util.List;

public class SelectCategory extends AppCompatActivity {

    RecyclerView recView;
    GroupedListAdapter adapter;
    GridLayoutManager manager;
    Category cTemp;
    AppDatabase database;
    private static final String TAG = "SelectCategory";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);
        database = AppDatabase.getDatabase(getApplicationContext());
        //SelectCategoryController selectCategoryController = new SelectCategoryController();
        //TreeMap<String, List<Category>> treeMap = selectCategoryController.prepareList();
        TreeMap<String, List<Category>> treeMap = prepareList();

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
    //This method takes a TreeMap as argument, loops through the list and makes listHeaderItems or CategoryItems and returns a list of listitems
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

    public TreeMap<String, List<Category>> prepareList(){
        //List<Category> catList = categoryStore.getCategoryList();
        List<Category> catList = database.categoryDao().getCategories();

        for (Category c : catList){
            Log.d(TAG, "prepareList: catList" + c.toString());
        }
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