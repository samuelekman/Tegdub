package com.samuelekman.tegdub.BudgetList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.samuelekman.tegdub.BudgetActivity;
import com.samuelekman.tegdub.R;

import org.w3c.dom.Text;

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

            case BudgetItem.TYPE_EXPENSE_HEADER:
                View v3 = inflater.inflate(R.layout.budget_expense_header, parent, false);
                viewHolder = new ExpenseHeaderViewHolder(v3);
                break;

            case BudgetItem.TYPE_INCOME_HEADER:
                View v4 = inflater.inflate(R.layout.budget_income_header, parent, false);
                viewHolder = new IncomeHeaderViewHolder(v4);
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
               // expenseViewHolder.imgExp.setImageResource(budgetExpenseItem.getTransaction().getCategory().getIcon());
                double sum = budgetExpenseItem.getTransaction().getSum();
                String sumString = String.valueOf(sum);
                expenseViewHolder.txtExpCost.setText(sumString);

                break;

            case BudgetItem.TYPE_INCOME:
                BudgetIncomeItem budgetIncomeItem = (BudgetIncomeItem) mBudgetItem.get(position);
                IncomeViewHolder incomeViewHolder = (IncomeViewHolder) holder;
                incomeViewHolder.txtInc.setText(budgetIncomeItem.getTransaction().getCategory().getSubCategory());
                double incsum = budgetIncomeItem.getTransaction().getSum();
                String suString = String.valueOf(incsum);
                incomeViewHolder.txtIncSum.setText(suString);

                break;

            case BudgetItem.TYPE_EXPENSE_HEADER:
                BudgetExpenseHeaderItem budgetHeaderItem = (BudgetExpenseHeaderItem) mBudgetItem.get(position);
                ExpenseHeaderViewHolder headerViewHolder = (ExpenseHeaderViewHolder) holder;
                headerViewHolder.txtHeader.setText(budgetHeaderItem.getHeader());

                break;

            case BudgetItem.TYPE_INCOME_HEADER:
                BudgetIncomeHeaderItem budgetIncomeHeaderItem = (BudgetIncomeHeaderItem) mBudgetItem.get(position);
                IncomeHeaderViewHolder incomeHeaderViewHolder = (IncomeHeaderViewHolder) holder;
                incomeHeaderViewHolder.txtIncHeader.setText(budgetIncomeHeaderItem.getHeader());

                break;

        }


    }

    private class IncomeViewHolder extends RecyclerView.ViewHolder{
        TextView txtInc;
        ImageView imgInc;
        TextView txtIncSum;
        public IncomeViewHolder(View v){
            super(v);
            txtInc = (TextView) itemView.findViewById(R.id.budgetIncomeItemTxtView);
          //  imgInc = (ImageView) itemView.findViewById(R.id.budgetIncomeItemImgView);
            txtIncSum = (TextView) itemView.findViewById(R.id.budgetSumTxtView);
        }

    }

    private class ExpenseViewHolder extends RecyclerView.ViewHolder{
        TextView txtExp;
        ImageView imgExp;
        TextView txtExpCost;
        public ExpenseViewHolder(View v){
            super(v);
            txtExp = (TextView) itemView.findViewById(R.id.budgetExpenseItemTxtView);
           // imgExp = (ImageView) itemView.findViewById(R.id.budgetExpenseItemImgView);
            txtExpCost = (TextView) itemView.findViewById(R.id.budgetExpenseCostView);
        }

    }

    private class ExpenseHeaderViewHolder extends RecyclerView.ViewHolder {
        TextView txtHeader;
        public ExpenseHeaderViewHolder(View v){
            super(v);
            txtHeader = (TextView) itemView.findViewById(R.id.budgetExpenseHeaderTxtView);
        }
    }

    private class IncomeHeaderViewHolder extends RecyclerView.ViewHolder {
        TextView txtIncHeader;
        public IncomeHeaderViewHolder(View v){
            super(v);
            txtIncHeader = (TextView) itemView.findViewById(R.id.budgetIncomeHeaderTxtView);
        }
    }


}
