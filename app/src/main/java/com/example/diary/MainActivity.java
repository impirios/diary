package com.example.diary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView ;
    private RecyclerView.Adapter mAdapter;
    CalendarView calendarView;
    EditText editText;
    FloatingActionButton Add;
    ArrayList<Task> tasks ;
    MyDatabaseHelper databaseHelper;
    date today;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.NewTask);
        calendarView = findViewById(R.id.calendarview);
        today = new date(Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH)+1,Calendar.getInstance().get(Calendar.DATE));

        databaseHelper = new MyDatabaseHelper(this);
        tasks = databaseHelper.getTasks(today);

        recyclerView = findViewById(R.id.rcview);
        mAdapter = new RcAdapter(tasks);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Add = findViewById(R.id.AddTask);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().trim().length()>0) {
                    Task newTask = new Task(tasks.size(),editText.getText().toString(), 0,today);
                    databaseHelper.AddTask(newTask);
                    tasks.add(newTask);
                    mAdapter.notifyItemInserted(tasks.size()-1);
                    editText.setText("");
                }

            }
        });


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                    today = new date(year,month+1,dayOfMonth);
                    tasks.clear();
                    tasks = databaseHelper.getTasks(today);
                    Toast.makeText(getApplicationContext(),"Tasks on "+today.getDay()+"/"+today.getMonth()+"/"+today.getYear(),Toast.LENGTH_SHORT).show();
                    mAdapter = new RcAdapter(tasks);
                    recyclerView.setAdapter(mAdapter);


            }
        });

    }


}