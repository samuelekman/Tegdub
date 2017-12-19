package com.samuelekman.tegdub.utils;

import android.content.Context;

/**
 * Created by samuel on 2017-12-18.
 */

public class IconHelper {
    public static int getImageId(Context c, String iconName) {
        return c.getResources().getIdentifier(iconName, "drawable", c.getPackageName());
    }

}
