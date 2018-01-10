package com.samuelekman.tegdub.utils;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import com.samuelekman.tegdub.model.Category;

import java.util.List;

/**
 * Created by samuel on 2018-01-10.
 */

public class GetCategoriesTask extends AsyncTask<Void, Integer, List<Category>> {
    private AppDatabase database;
    private Context context;
    private Exception exception;

    public GetCategoriesTask(Context context){
        super();
        this.context = context;
    }

    @Override
    protected List<Category> doInBackground(Void... voids) {
        try {
            database = AppDatabase.getDatabase(context);
            return database.categoryDao().getCategories();
        } catch (Exception e){
            exception = e;
            return null;
        }

    }
/*
This is a method I started working on for handling exceptions. Didn't complete it.
TO-DO
    @Override
    protected void onPostExecute(List<Category> result){
        if(exception!=null){
            throw exception;
        }
    }
    */
}
