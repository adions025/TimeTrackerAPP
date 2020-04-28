package com.fita3.adonisgonzalez.timetracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class InfoActivity extends AppCompatActivity {

    TextView nomResult;

    TextView descResult;

    TextView duradaResult;

    TextView iniciResult;

    TextView fiResult;

    DadesActivitat dades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nomResult = findViewById(R.id.nomR);
        descResult = findViewById(R.id.descR);
        duradaResult = findViewById(R.id.duradaR);
        iniciResult = findViewById(R.id.iniciR);
        fiResult = findViewById(R.id.fiR);

        dades = (DadesActivitat) getIntent().getSerializableExtra("activity");

        nomResult.setText(dades.getNom());

        descResult.setText(dades.getDescripcio());

        duradaResult.setText(String.valueOf(dades.getHores() + "h " + dades.getMinuts() + "m "
                + dades.getSegons() + "s"));

        iniciResult.setText(String.valueOf(dades.getDataInicial()));

        fiResult.setText(String.valueOf(dades.getDataFinal()));
    }
}
