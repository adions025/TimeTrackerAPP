package com.fita3.adonisgonzalez.timetracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;

/**
 * Classe encarregada d'afegir Projectes nous
 */
public class AddActivity extends AppCompatActivity {
    //Definicions inicials de les variables internes
    EditText editNameAct;
    EditText editDescriptionAct;
    Button mButton;
    String name;
    String description;
    ArrayList<? extends Parcelable> llistaDadesActivitats;
    //Creació de la vista
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Metadata (layout de l'activitat + el nom a mostrar a la toolbar)
        setContentView(R.layout.activity_add);
        setTitle("Afegir Projecte");
        //Selecció dels elements de l'activitat i la seva inicialització
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mButton = (Button)findViewById(R.id.buttonAdd);
        editNameAct = (EditText)findViewById(R.id.nameAct);
        editDescriptionAct = (EditText)findViewById(R.id.descAct);
        //En clicar el botó d'afegir, mirar sinó hi ha un camp buit
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmpty(editNameAct   ) || isEmpty(editDescriptionAct)){
                    dialogEmptyEditext();
                } else{ //Crida al gestor d'activitats per afegir un projecte (codi explicat en la seva classe)
                    Intent intent = new Intent(GestorArbreActivitats.AFEGIR_PROJECTE);
                    intent.putExtra("nom", editNameAct.getText().toString());
                    intent.putExtra("desc", editDescriptionAct.getText().toString());
                    //Enviament de l'intent
                    sendBroadcast(intent);
                    //Fi de l'activitat
                    finish();
                }
            }
        });
    }

    //Mostrar el diàleg de camps buits
    public void dialogEmptyEditext() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("You must write a name and description project " + editNameAct.getText());
        alertDialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    //Control de que no hi hagi camps buits
    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
}