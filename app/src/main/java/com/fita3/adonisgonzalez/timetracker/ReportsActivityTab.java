package com.fita3.adonisgonzalez.timetracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Classe encarregada de mostrar el tab d'informes en la pantalla principal
 */
public class ReportsActivityTab extends Fragment {
    //Declaració de totes les variables necessàries
    Button button;
    private static final String TAG = "ReportsActivityTab";
    //Vista que es crea en iniciar l'activitat (infla la vista i seteja els botons i el seus destins)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reports_activity_tab,container,false);

        button = (Button) view.findViewById(R.id.createReport);
        button.setOnClickListener(goToProActivity);
        return view;
    }
    //Control del click del botó
    private View.OnClickListener goToProActivity = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), CreateReport.class);
            startActivity(intent);
        }
    };
}

