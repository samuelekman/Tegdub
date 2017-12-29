package com.samuelekman.tegdub.Interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.samuelekman.tegdub.model.Category;

import java.util.List;

import javax.security.auth.callback.Callback;

/**
 * Created by samuel on 2017-12-14.
 */

@Dao
public interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addCategory(Category category);

    @Query("SELECT * FROM category")
    List<Category> getCategories();

    @Query("SELECT * FROM category WHERE cid = :cat_id")
    Category getCategory(int cat_id);

    @Query("SELECT * FROM category where subCategory = :sub_cat")
    Category getCategory(String sub_cat);

    @Query("DELETE FROM category")
    public void nukeTable();
}
