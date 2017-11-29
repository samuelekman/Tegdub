package com.samuelekman.tegdub.Interfaces;

import com.samuelekman.tegdub.model.MainCategory;

/**
 * Created by samuel on 2017-11-28.
 */

public class HeaderItem extends ListItem{
    private String mainCategory;

    @Override
    public int getType() {
        return TYPE_HEADER;
    }

    public HeaderItem(String mainCategory){
        this.mainCategory = mainCategory;
    }

    public String getHeader(){
        return mainCategory;
    }
}
