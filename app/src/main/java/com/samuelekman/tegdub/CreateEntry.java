package com.samuelekman.tegdub;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.app.DatePickerDialog;
import android.widget.EditText;
import android.widget.Toast;

import com.samuelekman.tegdub.model.Transaction;
import com.samuelekman.tegdub.utils.Storage.AppDatabase;
import java.util.Calendar;


public class CreateEntry extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    static final int REQUEST_CODE = 1;
    private EditText categoryTextField;
    private EditText dateTextField;
    private EditText sumTextField;
    private EditText noteTextField;
    private Calendar selectedDate;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private static final String TAG = "CreateEntry";
    Intent mIntent;
    AppDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_entry);
        database =  AppDatabase.getDatabase(this);
        mIntent = this.getIntent();

        categoryTextField = (EditText) findViewById(R.id.categoryTextField);
        categoryTextField.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                selectCategory(v);
            }
        });
        Calendar c = Calendar.getInstance();

        dateTextField = (EditText) findViewById(R.id.dateTextField);
        changeDate(c);
        dateTextField.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                showDatePicker();
            }
        });

        sumTextField = (EditText) findViewById(R.id.ammountTextField);

        noteTextField = (EditText) findViewById(R.id.noteTextField);

        toolbar = (Toolbar) findViewById(R.id.toolbarEntry);
        toolbar.setTitle("Create Transaction");
        toolbar.setTitleTextColor(Color.WHITE);

        fab = (FloatingActionButton) findViewById(R.id.fabEntry);
        fab.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (isInputOk()==false){
                    /*
                    Should do something more useful here. TO-DO
                     */
                    Toast toast = Toast.makeText(getApplicationContext(), "Please enter all the fields", Toast.LENGTH_SHORT);
                    toast.show();

                } else {
                    Transaction t = buildTransactionObject();
                    new AddTransactionTask().execute(t);
                    goBack(mIntent);
                }
            }
        });

    }
/*
Method that checks where the CreateEntry Activity where started from.
Takes you back to the right place, I hope..
 */
    public void goBack(Intent intent){
        if(intent != null) {
            String fromActivity = intent.getExtras().getString("ActivityID");

            switch(fromActivity){

                case "MainActivity":
                    Intent intent1 = new Intent(CreateEntry.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent1);
                    break;

                case "BudgetFragment":
                    onBackPressed();
                    break;

            }
        }
    }

    public void changeDate(Calendar date){
        this.selectedDate = date;
        dateTextField.setText(String.format("%d-%02d-%02d", date.get(Calendar.YEAR), date.get(Calendar.MONTH)+1, date. get(Calendar.DAY_OF_MONTH)));
    }

    public void selectCategory(View v) {
        Intent intent = new Intent(this, SelectCategory.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

/*
Method that checks if the result was OK, in my case I don't have to do anything if it wasn't.
If OK, sets the text in the categoryTextField with the subcategory taken from the SelectCategory Activity.
 */
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_CODE){
            if (resultCode == Activity.RESULT_OK){
                String objectData = data.getStringExtra("Dunno");
                categoryTextField.setText(objectData);
            }

        }
    }

    //This method reads the UserInput and creates a transaction object
    public Transaction buildTransactionObject(){

        return  new Transaction(
                Double.parseDouble(sumTextField.getText().toString()),
                selectedDate,
                noteTextField.getText().toString(),
                database.categoryDao().getCategory(categoryTextField.getText().toString()).getCid()

        );

    }

    /*Method that checks if the UserInput is OK (don't want to build TransactionObjects with null values)
        This method should also check if the input is wrong (Letters in sum), not implemented yet.
     */
    public boolean isInputOk() {
        if(isEmpty(sumTextField) || isEmpty(dateTextField)){
            return false;
        }
        return true;
    }

    /*
    Method for checking if EditTexts are empty.
     */
    public boolean isEmpty(EditText text){
        if(text.getText().toString().trim().length() >0){
            return false;
        }
        return true;
    }

    public void showDatePicker(){
        DatePickerFragment newFragment = DatePickerFragment.newInstance(selectedDate);
            newFragment.setParent(this);
            newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

    //Inner class for showing the datePicker.
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
    An inner class for adding Transaction to database.
    Should handle exceptions, it doesn't...
     */
    public class AddTransactionTask extends AsyncTask<Transaction, Void, Void>{


        @Override
        protected Void doInBackground(Transaction... transactions) {
            database.transactionDao().addTransactions(transactions);
            return null;
        }
    }

 }

