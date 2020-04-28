package com.fita3.adonisgonzalez.timetracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

/**
 * Classe que adapta una llista d'intervals al format elegit per la layout
 */
public class AdapterList_Interval extends ArrayAdapter<DadesInterval> {
    //Tags per debuguejar
    private final String TAG = this.getClass().getSimpleName();
    //Constructor
    public AdapterList_Interval(LlistaIntervalsActivity llistaIntervalsActivity, int layoutID, List<DadesInterval> llistaDadesIntervals) {
        super(llistaIntervalsActivity, layoutID, llistaDadesIntervals);
    }
    //Obtenció de la vista i creació d'aquesta
    public View getView(final int position, View convertView, ViewGroup parent) {
        //Inflament de la vista i inicialització dels seus elements
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fila_intervals, null, true);
        TextView inici = (TextView)view.findViewById(R.id.tempsInici);
        TextView fin = (TextView)view.findViewById(R.id.tempsFinal);
        ImageView img = (ImageView)view.findViewById(R.id.imgView);
        //Obtenció de la tasca clicada
        final DadesInterval interval = getItem(position);
        //Format de la vista (data inicial tasca + data fi)
        inici.setText(interval.getDataInicial() + "");
        fin.setText(interval.getDataFinal() + "");

        return view;
    }
}
