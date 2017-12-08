package com.samuelekman.tegdub.BudgetList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.samuelekman.tegdub.BudgetActivity;
import com.samuelekman.tegdub.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samuel on 2017-12-08.
 */

public class BudgetListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<BudgetItem> mBudgetItem = new ArrayList<>();

    public BudgetListAdapter(BudgetActivity budgetActivity, List<BudgetItem> mBudgetItem){
        this.mBudgetItem = mBudgetItem;
    }

    @Override
    public int getItemViewType(int position) {
        return mBudgetItem.get(position).getType();
    }

    @Override
    public int getItemCount(){
        return mBudgetItem != null ? mBudgetItem.size() : 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {

            case BudgetItem.TYPE_EXPENSE:
                View v1 = inflater.inflate(R.layout.budget_expense_item, parent, false);
                viewHolder = new ExpenseViewHolder(v1);
                break;

            case BudgetItem.TYPE_INCOME:
                View v2 = inflater.inflate(R.layout.budget_income_item, parent, false);
                viewHolder = new IncomeViewHolder(v2);
                break;

            case BudgetItem.TYPE_HEADER:
                View v3 = inflater.inflate(R.layout.budget_header, parent, false);
                viewHolder = new HeaderViewHolder(v3);
                break;

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch(holder.getItemViewType()){

            case BudgetItem.TYPE_EXPENSE:
                BudgetExpenseItem budgetExpenseItem = (BudgetExpenseItem) mBudgetItem.get(position);
                ExpenseViewHolder expenseViewHolder = (ExpenseViewHolder) holder;
                expenseViewHolder.txtExp.setText(budgetExpenseItem.getTransaction().getCategory().getSubCategory());
                expenseViewHolder.imgExp.setImageResource(budgetExpenseItem.getTransaction().getCategory().getIcon());

                break;

            case BudgetItem.TYPE_INCOME:
                BudgetIncomeItem budgetIncomeItem = (BudgetIncomeItem) mBudgetItem.get(position);
                IncomeViewHolder incomeViewHolder = (IncomeViewHolder) holder;
                incomeViewHolder.txtInc.setText(budgetIncomeItem.getTransaction().getCategory().getSubCategory());
        }
    }

    private class IncomeViewHolder extends RecyclerView.ViewHolder{
        TextView txtInc;
        ImageView imgInc;
        public IncomeViewHolder(View v){
            super(v);
            txtInc = (TextView) itemView.findViewById(R.id.budgetIncomeItemTxtView;
            imgInc = (ImageView) itemView.findViewById(R.id.budgetIncomeItemImgView);
        }

    }

    private class ExpenseViewHolder extends RecyclerView.ViewHolder{
        TextView txtExp;
        ImageView imgExp;
        public ExpenseViewHolder(View v){
            super(v);
            txtExp = (TextView) itemView.findViewById(R.id.budgetExpenseItemTxtView);
            imgExp = (ImageView) itemView.findViewById(R.id.budgetExpenseItemImgView);
        }

    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView txtHeader;
        public HeaderViewHolder(View v){
            super(v);
            txtHeader = (TextView) itemView.findViewById(R.id.budgetHeaderTxtView);
        }
    }

}
