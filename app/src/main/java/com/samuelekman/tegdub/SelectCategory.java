package com.samuelekman.tegdub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.samuelekman.tegdub.CategoryList.CategoryItem;
import com.samuelekman.tegdub.CategoryList.GroupedListAdapter;
import com.samuelekman.tegdub.CategoryList.HeaderItem;
import com.samuelekman.tegdub.CategoryList.ListItem;
import com.samuelekman.tegdub.model.Category;
import com.samuelekman.tegdub.utils.AppDatabase;
import com.samuelekman.tegdub.utils.GetCategoriesTask;

import java.util.ArrayList;
import java.util.TreeMap;

import java.util.List;

public class SelectCategory extends AppCompatActivity {

    private RecyclerView recView;
    private GroupedListAdapter adapter;
    private GridLayoutManager manager;
    private Category cTemp;
    private AppDatabase database;
    private static final String TAG = "SelectCategory";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);
        database = AppDatabase.getDatabase(getApplicationContext());
        TreeMap<String, List<Category>> treeMap = prepareList();
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
    /*This method takes a TreeMap as argument, loops through the list and makes listHeaderItems or CategoryItems and returns a list of listitems
    * The list of listitems is used within
    * */
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
    /*
     * Checks if the cTemp variable is null (back button pressed with no category selected from the list).
     * If it's not null pass the subcategory back to previous activity.
     * I don't pass objects as I've read that it's not good.
     */
    @Override
    public void onBackPressed(){
        if(cTemp!=null){
        getIntent().putExtra("Dunno", cTemp.getSubCategory());
        this.setResult(RESULT_OK, getIntent());
        super.onBackPressed();
        }

        this.setResult(RESULT_CANCELED, getIntent());
        super.onBackPressed();
    }

    /*
    This method gets the categories from the DB, then fills a TreeMap with each mainCategory as the String key.
    this try-catch solution is horrible, I know. The thought was to solve it within the GetCategoriesTask class.
     */
    public TreeMap<String, List<Category>> prepareList(){
        List<Category> catList;
        try {
           catList = new GetCategoriesTask(this).execute().get();
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
        } catch(Exception ie){
            return null;
        }
        //List<Category> catList = database.categoryDao().getCategories();

    }
}