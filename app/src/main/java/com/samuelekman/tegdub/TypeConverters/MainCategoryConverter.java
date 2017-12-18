package com.samuelekman.tegdub.TypeConverters;

import android.arch.persistence.room.TypeConverter;

import com.samuelekman.tegdub.model.Category;
import com.samuelekman.tegdub.model.MainCategory;

/**
 * Created by samuel on 2017-12-17.
 */

public class MainCategoryConverter {
    @TypeConverter
    public static String fromMainCategory(MainCategory mainCategory){
        if(mainCategory == null){
            return null;
        }

        return mainCategory.toString();
    }
    //Enums: ENTERTAINMENT(0), HOME(1), INCOME(2), MISCELLANEOUS(3), TRANSPORTATION(4), HOUSING(5), SUSTENANCE(6);

    @TypeConverter
    public static MainCategory toMainCategory(String mainCategory){
        if(mainCategory == null){
            return null;
        }
         if (mainCategory.equals("ENTERTAINMENT")){
            return MainCategory.ENTERTAINMENT;

        } else if (mainCategory.equals("HOME")){
            return MainCategory.HOME;

        } else if (mainCategory.equals("INCOME")){
            return MainCategory.INCOME;

        } else if (mainCategory.equals("MISCELLANEOUS")){
            return MainCategory.MISCELLANEOUS;

        } else if (mainCategory.equals("TRANSPORTATION")){
            return MainCategory.TRANSPORTATION;

        } else if (mainCategory.equals("HOUSING")){
            return MainCategory.HOUSING;

        } else if (mainCategory.equals("SUSTENANCE")){
            return MainCategory.SUSTENANCE;
        } else
            return null;
    }

}


