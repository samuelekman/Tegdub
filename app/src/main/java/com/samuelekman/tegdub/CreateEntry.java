package com.samuelekman.tegdub;

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

import java.util.Calendar;


import static com.samuelekman.tegdub.R.id.ammountTextField;

public class CreateEntry extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_entry);

        final EditText dateTextField = (EditText) findViewById(R.id.dateTextField);
        dateTextField.setHint("Today");
        dateTextField.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                showDatePicker();
            }
        });

        final Button categoryButton = (Button) findViewById(R.id.categoryButton);
        categoryButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                selectCategory(v);
            }
            });

    }

    public void selectCategory(View v) {
        Intent intent = new Intent(this, SelectCategory.class);
        startActivity(intent);
    }

    public void showDatePicker(){
        DialogFragment newFragment = new DatePickerFragment();
            newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
            return  dialog;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        }
    }
 }

