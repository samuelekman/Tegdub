package com.samuelekman.tegdub.utils;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.samuelekman.tegdub.Interfaces.CategoryDao;
import com.samuelekman.tegdub.Interfaces.TransactionDao;
import com.samuelekman.tegdub.model.Category;
import com.samuelekman.tegdub.model.Transaction;

/**
 * Created by samuel on 2017-12-14.
 */
@Database(entities = {Transaction.class, Category.class}, version = 16, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract TransactionDao transactionDao();
    public abstract CategoryDao categoryDao();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "transactiondatabase")
                    // Don't do this on a real app!
                    .allowMainThreadQueries()
                    // recreate the database if necessary
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }
}
