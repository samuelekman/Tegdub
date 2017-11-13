package com.samuelekman.tegdub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_entry);

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
}
