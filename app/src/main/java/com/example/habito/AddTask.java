package com.example.habito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
    }

    public void saveTask(View view) {
        Intent data= new Intent();
        EditText taskName = (EditText)findViewById(R.id.task_name_input);
        data.setData(Uri.parse(taskName.getText().toString()));
        setResult(RESULT_OK, data);
        finish();
    }
}