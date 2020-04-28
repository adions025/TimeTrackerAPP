package com.fita3.adonisgonzalez.timetracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.lang.reflect.Array;

/**
 * Classe per crear informes (no implementada)
 */
public class CreateReport extends AppCompatActivity {

    Spinner spinner;
    Button createRep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        createRep = (Button) findViewById(R.id.createRep);
        createRep.setOnClickListener(goToProActivity);

        String informe[] = {"HTML short", "HTML detailed", "Text short", "Text detailed"};
        spinner = (Spinner) findViewById(R.id.spinnerReport);

        ArrayAdapter<String> spinnerArray = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, informe);
        spinnerArray.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(spinnerArray);

    }

    private View.OnClickListener goToProActivity = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), MainActivity.class);
            startActivity(intent);
        }
    };


}
