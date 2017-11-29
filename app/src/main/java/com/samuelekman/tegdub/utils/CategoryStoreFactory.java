package com.samuelekman.tegdub.utils;

import com.samuelekman.tegdub.Interfaces.CategoryStore;

/**
 * Created by samuel on 2017-11-29.
 */

public class CategoryStoreFactory {

    public static CategoryStore categoryStore(){
        return new FakeCategoryStorage();
    }

}
