package com.samuelekman.tegdub;

import com.samuelekman.tegdub.Interfaces.ListItem;
import com.samuelekman.tegdub.model.Category;

/**
 * Created by samuel on 2017-11-28.
 */

public class CategoryItem extends ListItem{
    private Category category;

    public CategoryItem(Category category){
        this.category = category;

    }

    public Category getCategory(){
        return category;

    }
    @Override
    public int getType() {
        return TYPE_CATEGORY;
    }
}
