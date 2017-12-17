package com.samuelekman.tegdub.Interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.samuelekman.tegdub.model.Category;

import java.util.List;

/**
 * Created by samuel on 2017-12-14.
 */

@Dao
public interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addCategory(Category category);

    @Query("SELECT * FROM category")
    List<Category> getCategories();

}
