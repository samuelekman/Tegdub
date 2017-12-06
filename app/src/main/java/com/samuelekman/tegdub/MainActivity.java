package com.samuelekman.tegdub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.create_entry);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                createEntry(v);
            }
        });
        final Button budgetButton = (Button) findViewById(R.id.budgetButton);
        budgetButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                showBudget(v);
            }
        });
    }
    public void createEntry(View view ){
        Intent intent = new Intent(this, CreateEntry.class);
        startActivity(intent);
    }

    public void showBudget(View view){
        Intent intent = new Intent(this, BudgetActivity.class);
        startActivity(intent);
    }


}
