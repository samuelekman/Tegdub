package com.samuelekman.tegdub;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.samuelekman.tegdub.model.Category;
import com.samuelekman.tegdub.model.MainCategory;
import com.samuelekman.tegdub.utils.AppDatabase;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private AppDatabase database;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       database = AppDatabase.getDatabase(getApplicationContext());


       ArrayList<Category> cList = (ArrayList<Category>) database.categoryDao().getCategories();
      /*
      This isn't pretty, but I spent too much time trying to implement a room.callback to populate the Database.
       */
        if (cList.size() <= 0){
            initCategoryDB();
        }



        final Button button = (Button) findViewById(R.id.create_entry);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                createEntry(v);
            }
        });
        final Button budgetButton = (Button) findViewById(R.id.budgetButton);
        budgetButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                showBudget(v);
            }
        });
    }
    public void createEntry(View view ){
        Intent intent = new Intent(this, CreateEntry.class);
        intent.putExtra("ActivityID", "MainActivity");
        startActivity(intent);
    }

    public void showBudget(View view){
        Intent intent = new Intent(this, BudgetActivity.class);
        startActivity(intent);
    }

    //TextInputEditText (for error and hints according to MaterialDesign!!!

    //This method initializes the DB with the default CategoryObjects. There should be more categories but I want to fix the rest of the app first,
    // This is after all not the main purpose of this project..

    private void initCategoryDB(){
        Log.d(TAG, "initCategoryDB: Initializing DB");
        ArrayList<Category> catList = new ArrayList<>();

        catList.add(new Category(MainCategory.MISCELLANEOUS, "Miscellaneous", "ic_local_offer_black_24dp"));
        catList.add(new Category(MainCategory.MISCELLANEOUS, "Healthcare", "ic_favorite_black_24dp"));
        catList.add(new Category(MainCategory.MISCELLANEOUS, "Tobacco", "ic_smoking_rooms_black_24dp"));

        //Initializing subcategories of MainCategory Entertainment
        catList.add(new Category(MainCategory.ENTERTAINMENT, "Entertainment", "ic_pool_black_24dp"));
        catList.add(new Category(MainCategory.ENTERTAINMENT, "Hobby", "ic_golf_course_black_24dp"));
        catList.add(new Category(MainCategory.ENTERTAINMENT, "Vacation", "ic_hot_tub_black_24dp"));

        //Initializing subcategories of MainCategory sustenance
        catList.add(new Category(MainCategory.SUSTENANCE, "Sustenance", "ic_local_dining_black_24dp"));
        catList.add(new Category(MainCategory.SUSTENANCE, "Alcohol", "ic_local_drink_black_24dp"));
        catList.add(new Category(MainCategory.SUSTENANCE, "Groceries", "ic_local_grocery_store_black_24dp"));

        //Initializing subcategories of MainCategory Housing
        catList.add(new Category(MainCategory.HOUSING, "Housing", "ic_home_black_24dp"));
        catList.add(new Category(MainCategory.HOUSING, "Rent", "ic_domain_black_24dp"));
        catList.add(new Category(MainCategory.HOUSING, "Morgage", "ic_monetization_on_black_24dp"));

        //Initializing subcategories of MainCategory Income
        catList.add(new Category(MainCategory.INCOME, "Income", "ic_attach_money_black_24dp"));
        catList.add(new Category(MainCategory.INCOME, "Salary", "ic_payment_black_24dp"));

        //Initializing subcategories of MainCategory Transportation
        catList.add(new Category(MainCategory.TRANSPORTATION, "Transportation", "ic_train_black_24dp"));
        catList.add(new Category(MainCategory.TRANSPORTATION, "Car", "ic_directions_car_black_24dp"));
        catList.add(new Category(MainCategory.TRANSPORTATION, "Public Transportation", "ic_tram_black_24dp"));

        new AddCategoryTask().execute(catList);


         }


    /*
        An inner class for adding Categories to database.
        Should handle exceptions, it doesn't...
         */
    public class AddCategoryTask extends AsyncTask<ArrayList<Category>, Void, Void> {


        @Override
        protected Void doInBackground(ArrayList<Category>...categories) {
            ArrayList<Category> passedList = categories[0];
            for(Category category : passedList){
                database.categoryDao().addCategory(category);
            }
            return null;
        }
    }

}
