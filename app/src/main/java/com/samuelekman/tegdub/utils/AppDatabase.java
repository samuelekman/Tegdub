package com.samuelekman.tegdub.utils;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import com.samuelekman.tegdub.Interfaces.CategoryDao;
import com.samuelekman.tegdub.Interfaces.TransactionDao;
import com.samuelekman.tegdub.model.Category;
import com.samuelekman.tegdub.model.MainCategory;
import com.samuelekman.tegdub.model.Transaction;

/**
 * Created by samuel on 2017-12-14.
 */
@Database(entities = {Transaction.class, Category.class}, version = 17, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    private static final String TAG = "AppDatabase";

    public abstract TransactionDao transactionDao();
    public abstract CategoryDao categoryDao();



    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "transactiondatabase")
                    // Don't do this on a real app!
                    .allowMainThreadQueries()
                    // recreate the database if necessary
                    .fallbackToDestructiveMigration()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(SupportSQLiteDatabase db) {
                            Log.d(TAG, "onCreate: In the onCreate of callback");
                            getDatabase(context).categoryDao().addCategory(new Category(MainCategory.MISCELLANEOUS, "Miscellaneous", "ic_local_offer_black_24dp"));
                            getDatabase(context).categoryDao().addCategory(new Category(MainCategory.MISCELLANEOUS, "Healthcare", "ic_favorite_black_24dp"));
                            getDatabase(context).categoryDao().addCategory(new Category(MainCategory.MISCELLANEOUS, "Tobacco", "ic_smoking_rooms_black_24dp"));

                            //Initializing subcategories of MainCategory Entertainment
                            getDatabase(context).categoryDao().addCategory(new Category(MainCategory.ENTERTAINMENT, "Entertainment", "ic_pool_black_24dp"));
                            getDatabase(context).categoryDao().addCategory(new Category(MainCategory.ENTERTAINMENT, "Hobby", "ic_golf_course_black_24dp"));
                            getDatabase(context).categoryDao().addCategory(new Category(MainCategory.ENTERTAINMENT, "Vacation", "ic_hot_tub_black_24dp"));

                            //Initializing subcategories of MainCategory sustenance
                            getDatabase(context).categoryDao().addCategory(new Category(MainCategory.SUSTENANCE, "Sustenance", "ic_local_dining_black_24dp"));
                            getDatabase(context).categoryDao().addCategory(new Category(MainCategory.SUSTENANCE, "Alcohol", "ic_local_drink_black_24dp"));
                            getDatabase(context).categoryDao().addCategory(new Category(MainCategory.SUSTENANCE, "Groceries", "ic_local_grocery_store_black_24dp"));

                            //Initializing subcategories of MainCategory Housing
                            getDatabase(context).categoryDao().addCategory(new Category(MainCategory.HOUSING, "Housing", "ic_home_black_24dp"));
                            getDatabase(context).categoryDao().addCategory(new Category(MainCategory.HOUSING, "Rent", "ic_domain_black_24dp"));
                            getDatabase(context).categoryDao().addCategory(new Category(MainCategory.HOUSING, "Morgage", "ic_monetization_on_black_24dp"));

                            //Initializing subcategories of MainCategory Income
                            getDatabase(context).categoryDao().addCategory(new Category(MainCategory.INCOME, "Income", "ic_attach_money_black_24dp"));
                            getDatabase(context).categoryDao().addCategory(new Category(MainCategory.INCOME, "Salary", "ic_payment_black_24dp"));

                            //Initializing subcategories of MainCategory Transportation
                            getDatabase(context).categoryDao().addCategory(new Category(MainCategory.TRANSPORTATION, "Transportation", "ic_train_black_24dp"));
                            getDatabase(context).categoryDao().addCategory(new Category(MainCategory.TRANSPORTATION, "Car", "ic_directions_car_black_24dp"));
                            getDatabase(context).categoryDao().addCategory(new Category(MainCategory.TRANSPORTATION, "Public Transportation", "ic_tram_black_24dp"));


                        }
                    })

                    .build();


        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }



}
