package com.fita3.adonisgonzalez.timetracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.sql.Time;

public class AddTaskPro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task_pro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button button = (Button) findViewById(R.id.buttonPROT);
        button.setOnClickListener(goToProActivity);
    }

    private View.OnClickListener goToProActivity = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), AddTask.class);
            startActivity(intent);
        }
    };
}
