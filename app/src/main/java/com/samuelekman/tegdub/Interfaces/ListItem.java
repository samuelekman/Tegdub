package com.samuelekman.tegdub.Interfaces;

/**
 * Created by samuel on 2017-11-28.
 */

public abstract class ListItem {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_CATEGORY = 1;

    abstract public int getType();
}
