package com.samuelekman.tegdub;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.app.DatePickerDialog;
import android.widget.EditText;

import com.samuelekman.tegdub.model.Category;
import com.samuelekman.tegdub.model.Transaction;

import java.util.Calendar;
import java.util.Date;


import static com.samuelekman.tegdub.R.id.ammountTextField;
import static com.samuelekman.tegdub.R.id.categoryTextField;
import static com.samuelekman.tegdub.R.id.dateTextField;

public class CreateEntry extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    static final int REQUEST_CODE = 1;
    private EditText categoryTextField;
    private EditText dateTextField;
    private EditText sumTextField;
    private Calendar selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_entry);

        categoryTextField = (EditText) findViewById(R.id.categoryTextField);
        categoryTextField.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                selectCategory(v);
            }
        });
        Calendar c = Calendar.getInstance();
        changeDate(c);
        dateTextField = (EditText) findViewById(R.id.dateTextField);
        //dateTextField.setText(c.toString()); // Let's see how this works.
        dateTextField.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                showDatePicker();
            }
        });

        sumTextField = (EditText) findViewById(R.id.ammountTextField);



    }
    public void changeDate(Calendar date){
        this.selectedDate = date;
        dateTextField.setText(date.toString());
    }

    public void selectCategory(View v) {
        Intent intent = new Intent(this, SelectCategory.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_CODE){
            if (resultCode == Activity.RESULT_OK){
                String objectData = data.getStringExtra("Dunno");
                System.out.println("FÅr tillbaka detta"+ objectData);
                categoryTextField.setText(objectData);
            }
            // HÄr kommer resultatet parsas
        }
    }



    public void showDatePicker(){
        DatePickerFragment newFragment = DatePickerFragment.newInstance(selectedDate);
            newFragment.setParent(this);
            newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private CreateEntry parent;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
            return  dialog;
        }
        public static DatePickerFragment newInstance(Calendar c){
            DatePickerFragment datePickerFragment = new DatePickerFragment();
            return datePickerFragment;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Calendar date = Calendar.getInstance();
            date.set(Calendar.YEAR, year);
            date.set(Calendar.MONTH, month);
            date.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            parent.changeDate(date);

        }
        public void setParent(CreateEntry fragment){
            this.parent = fragment;
        }

    }

/*
    public Transaction buildTransactionObject(){
        Transaction transaction = new Transaction(
                //Double.parseDouble(sumTextField.getText().toString());
                //dateTextField

        );
    } **/
 }

