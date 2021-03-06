package com.samuelekman.tegdub.CategoryList;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.samuelekman.tegdub.R;
import com.samuelekman.tegdub.SelectCategory;
import com.samuelekman.tegdub.model.Category;
import com.samuelekman.tegdub.utils.IconHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samuel on 2017-11-29.
 */

public class GroupedListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private IconHelper iconHelper;
    private SelectCategory selectCategory;
    public interface OnItemClickListener {
        void onItemClick(Category category);
    }

    private List<ListItem> mListItem = new ArrayList<>();
    private OnItemClickListener listener;

    public GroupedListAdapter(SelectCategory selectCategory, List<ListItem> mListItem, OnItemClickListener listener){
        this.mListItem = mListItem;
        this.listener = listener;
        this.selectCategory = selectCategory;
    }

    @Override
    public int getItemViewType(int position) {
        return mListItem.get(position).getType();
    }

    @Override
    public int getItemCount(){
        return mListItem != null ? mListItem.size() : 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType){

            case ListItem.TYPE_CATEGORY:
                View v1 = inflater.inflate(R.layout.list_item, parent, false);
                viewHolder = new CategoryViewHolder(v1);
                break;

            case ListItem.TYPE_HEADER:
                View v2 = inflater.inflate(R.layout.list_header, parent, false);
                viewHolder = new HeaderViewHolder(v2);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position){

        switch (viewHolder.getItemViewType()){

            case ListItem.TYPE_CATEGORY:
                CategoryItem categoryItem = (CategoryItem) mListItem.get(position);
                CategoryViewHolder categoryViewHolder = (CategoryViewHolder) viewHolder;
                categoryViewHolder.txtCat.setText(categoryItem.getCategory().getSubCategory());
                String iconName = categoryItem.getCategory().getIcon();
                int iconId =iconHelper.getImageId(selectCategory, iconName);
                categoryViewHolder.imgCat.setImageResource(iconId);
                int color = Color.parseColor(categoryItem.getCategory().getMainCategory().getColorCode());
                categoryViewHolder.imgCat.setColorFilter(color);
                categoryViewHolder.bind(categoryItem.getCategory(), listener);

                break;

            case ListItem.TYPE_HEADER:
                HeaderItem headerItem = (HeaderItem) mListItem.get(position);
                HeaderViewHolder headerViewHolder = (HeaderViewHolder) viewHolder;
                headerViewHolder.txtHead.setText(headerItem.getHeader());

                break;
        }
    }



    private class CategoryViewHolder extends RecyclerView.ViewHolder  {
        TextView txtCat;
        ImageView imgCat;
        public CategoryViewHolder(View v) {
            super(v);
            txtCat = (TextView) itemView.findViewById(R.id.listItemTxtView);
            imgCat = (ImageView) itemView.findViewById(R.id.listItemImgView);
        }
        public void bind(final Category category, final OnItemClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override public void onClick(View v){
                    listener.onItemClick(category);
                }
            });
        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView txtHead;
        public HeaderViewHolder(View v) {
            super(v);
            txtHead = (TextView) itemView.findViewById(R.id.headerTxtView);
        }
    }
}
