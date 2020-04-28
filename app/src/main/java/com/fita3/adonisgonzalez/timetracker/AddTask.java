package com.fita3.adonisgonzalez.timetracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import static android.text.TextUtils.isEmpty;

public class AddTask extends AppCompatActivity {

    EditText editNameAct;
    EditText editDescriptionAct;
    Button mButton;

    String name;
    String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mButton = (Button)findViewById(R.id.buttonNT);
        editNameAct = (EditText)findViewById(R.id.nameNT);
        editDescriptionAct = (EditText)findViewById(R.id.descNT);
/*
        name = editNameAct.getText().toString();
        description = editDescriptionAct.getText().toString();
        */

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmpty(editNameAct) || isEmpty(editDescriptionAct)){
                    dialogEmptyEditext();
                } else{
                    Intent intent = new Intent(GestorArbreActivitats.AFEGIR_TASCA);
                    intent.putExtra("nom", editNameAct.getText().toString());
                    intent.putExtra("desc", editDescriptionAct.getText().toString());

                    sendBroadcast(intent);

                    finish();
                }
            }
        });
    }

    public void dialogEmptyEditext() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("You must write a name and description task");
        alertDialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {


            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }



    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

}
