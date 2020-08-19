package com.example.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class AddTaskActivity extends AppCompatActivity {
    EditText GetTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        GetTask = findViewById(R.id.NewTask);


    }
}