package com.example.habito;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddHabit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);
    }

    public void saveTask(View view) {
        Intent data= new Intent();
        EditText taskName = (EditText)findViewById(R.id.habit_name_input);
        data.setData(Uri.parse(taskName.getText().toString()));
        setResult(RESULT_OK, data);
        finish();
    }

    public void closeActivity(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}