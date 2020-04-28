package com.fita3.adonisgonzalez.timetracker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import nucli.Activitat;
import static android.view.View.GONE;

/**
 * Classe que adapta una llista d'activitats al format elegit per la layout
 */
public class AdapterList extends ArrayAdapter<DadesActivitat> {
    //Inicialització de les variables i de les crides que seràn necessàries
    private final String TAG = this.getClass().getSimpleName();
    private boolean activitatPareActualEsArrel;
    public static final String PARA_SERVEI = "Para_servei";
    public static final String PUJA_NIVELL = "Puja_nivell";
    public static final String BAIXA_NIVELL = "Baixa_nivell";
    public static final String DESA_ARBRE = "Desa_arbre";
    public static final String DONAM_FILLS = "Donam_fills";
    //Constructor
    public AdapterList(@NonNull Context context, int resource, @NonNull List<DadesActivitat> objects) {
        super(context, resource, objects);
    }
    //Obtenció de la vista i creació d'aquesta
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //Inflament de la vista i inicialització dels seus elements
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fila, null, true);
        TextView nom = (TextView)view.findViewById(R.id.nomView);
        TextView temps = (TextView)view.findViewById(R.id.tempsView);
        ImageView img = (ImageView)view.findViewById(R.id.imgView);
        final ImageView del = (ImageView)view.findViewById(R.id.delete);
        final ImageView play = (ImageView)view.findViewById(R.id.play);
        //Obtenció de l'activitat clicada
        final DadesActivitat activitat = getItem(position);
        //Format de la vista (nom + hores i temps de l'activitat)
        nom.setText(activitat.getNom());
        temps.setText(activitat.getHores() + "h " + activitat.getMinuts() + "m " + activitat.getSegons() + "s");
        //Mostrar l'imatge corresponent al tipus de l'activitat
        if(activitat.isProjecte()) {
            img.setImageResource(R.drawable.carpeta);
            play.setVisibility(GONE);
        } else {
            img.setImageResource(R.drawable.folioblanco);
        }
        //Mostrar el cronòmetre (botó play/pause)
        if(!activitat.isCronometreEngegat()){
            play.setImageResource(R.drawable.play);
        }
        else{
            play.setImageResource(R.drawable.pause);
        }

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activitat.isTasca()) {
                    Intent inte;
                    if (!activitat.isCronometreEngegat()) {
                        inte = new Intent(LlistaActivitatsActivity.ENGEGA_CRONOMETRE);

                    } else {
                        inte = new Intent(LlistaActivitatsActivity.PARA_CRONOMETRE);

                    }
                    inte.putExtra("posicio", position);
                    getContext().sendBroadcast(inte);
                }
            }


        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogEmptyEditext(activitat.getActivitat());
            }


        });
        return view;
    }

    //Implementanció de l'eliminació d'una activitat
    public void dialogEmptyEditext(final Activitat activitat) {
        //Creació diàleg pop-up
        android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage("Confirm delete");
        //Mitjançant broadcast, eliminar tasca (pare = null)
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Intent intent = new Intent(GestorArbreActivitats.ELIMINAR_ACTIVITAT);
                intent.putExtra("activitat", activitat);
                getContext().sendBroadcast(intent);
                //Recarreguem la vista amb els nous elements
                getContext().sendBroadcast(new Intent(LlistaActivitatsActivity.DONAM_FILLS));
            }
        });
        //Mostrem i creeem el diàleg
        android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
