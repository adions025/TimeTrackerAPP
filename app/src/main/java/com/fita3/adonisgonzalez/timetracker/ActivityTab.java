package com.fita3.adonisgonzalez.timetracker;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe encarregada de mostrar el tab d'activitats en la pantalla principal
 */
public class ActivityTab extends Fragment {
    //Declaració de totes les llistes i adaptadors necessàris
    public static final int REC_PROJECTE = 1;
    private final String TAG = this.getClass().getSimpleName();
    private ListView arrelListView;
    private ArrayAdapter<DadesActivitat> adapter;
    private List<DadesActivitat> llistaDadesActivitats;
    private int layoutID = R.layout.fila;
    private boolean activitatPareActualEsArrel;
    private Button button;
    String[] bankNames = {"BOI", "SBI", "HDFC", "PNB", "OBC"};

    //Declaració variables de broadcast necessàries
    public static final String PARA_SERVEI = "Para_servei";
    public static final String PUJA_NIVELL = "Puja_nivell";
    public static final String BAIXA_NIVELL = "Baixa_nivell";
    public static final String DESA_ARBRE = "Desa_arbre";
    public static final String DONAM_FILLS = "Donam_fills";

    //Classe interior que s'encarrega de gestionar els events rebuts (ja venia implementada)
    private class Receptor extends BroadcastReceiver {
        /**
         * Nom de la classe per fer aparèixer als missatges de logging del
         * LogCat.
         *
         * @see Log
         */
        private final String tag = this.getClass().getCanonicalName();

        /**
         * Gestiona tots els intents enviats, de moment només el de la
         * acció TE_FILLS. La gestió consisteix en actualitzar la llista
         * de dades que s'està mostrant mitjançant el seu adaptador.
         *
         * @param context
         * @param intent  objecte Intent que arriba per "broadcast" i del qual en fem
         *                servir l'atribut "action" per saber quina mena de intent és
         *                i els extres per obtenir les dades a mostrar i si el projecte
         *                actual és l'arrel de tot l'arbre o no
         */
        @Override
        public void onReceive(final Context context, final Intent intent) {
            Log.i(tag, "onReceive");
            if (intent.getAction().equals(GestorArbreActivitats.TE_FILLS)) {
                activitatPareActualEsArrel = intent.getBooleanExtra(
                        "activitat_pare_actual_es_arrel", false);
                // obtenim la nova llista de dades d'activitat que ve amb
                // l'intent
                @SuppressWarnings("unchecked")
                ArrayList<DadesActivitat> llistaDadesAct =
                        (ArrayList<DadesActivitat>) intent
                                .getSerializableExtra("llista_dades_activitats");

                if (llistaDadesAct == null) {
                    return;
                }
                adapter.clear();
                for (DadesActivitat dadesAct : llistaDadesAct) {
                    adapter.add(dadesAct);
                }
                // això farà redibuixar el ListView
                adapter.notifyDataSetChanged();
                Log.d(tag, "mostro els fills actualitzats");
            } else {
                // no pot ser
                assert false : "intent d'acció no prevista";
            }
        }
    }
    //Variables per rebre les dades (ja venia implementada)
    public Receptor receptor;

    //Recàrrega de l'activitat al tornar (cicle de vida de l'app) (ja venia implementada)
    @Override
    public final void onResume() {

        IntentFilter filter;
        filter = new IntentFilter();
        filter.addAction(GestorArbreActivitats.TE_FILLS);
        receptor = new Receptor();
        getActivity().registerReceiver(receptor, filter);

        // Crea el servei GestorArbreActivitats, si no existia ja. A més,
        // executa el mètode onStartCommand del servei, de manera que
        // *un cop creat el servei* = havent llegit ja l'arbre si es el
        // primer cop, ens enviarà un Intent amb acció TE_FILLS amb les
        // dades de les activitats de primer nivell per que les mostrem.
        // El que no funcionava era crear el servei (aquí mateix o
        // a onCreate) i després demanar la llista d'activiats a mostrar
        // per que startService s'executa asíncronament = retorna de seguida,
        // i la petició no trobava el servei creat encara.
        getActivity().startService(new Intent(getContext(), GestorArbreActivitats.class));

        super.onResume();
    }

    //Vista que es crea en iniciar l'activitat
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Primerament s'infla la vista de l'activitat
        View view = inflater.inflate(R.layout.activity_llista_activitats, container, false);
        //Configuració dels elements de la vista i inicialització
        arrelListView = (ListView) view.findViewById(R.id.listViewActivitats);
        button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(goToProActivity);
        llistaDadesActivitats = new ArrayList<DadesActivitat>();
        //Creació de l'adaptador de la layout per mostrar els projectes/tasques per pantalla i inicialització d'aquest
        adapter = new AdapterList(getContext(), layoutID, llistaDadesActivitats);
        arrelListView.setAdapter(adapter);
        //Funció per controlar els clicks normals sobre les activitats i distribuir la seva funció segons tipus(venia predefinida)
        //Accedim a l'interior del projecte o intervals
        arrelListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> arg0, final View arg1,
                                    final int pos, final long id) {

                Intent inte = new Intent(LlistaActivitatsActivity.BAIXA_NIVELL);
                inte.putExtra("posicio", pos);
                getActivity().sendBroadcast(inte);

                if (llistaDadesActivitats.get(pos).isProjecte()) {
                    getActivity().sendBroadcast(new Intent(LlistaActivitatsActivity.DONAM_FILLS));

                } else if (llistaDadesActivitats.get(pos).isTasca()) {
                    Intent intent = new Intent(getContext(), LlistaIntervalsActivity.class);
                    intent.putExtra("dades", llistaDadesActivitats.get(pos));
                    startActivity(intent);
                } else {
                    assert false : "activitat que no es projecte ni tasca";
                }
            }
        });
        //Funció per controlar els clicks llargs sobre les activitats i distribuir la seva funció segons tipus(venia predefinida)
        //Obtenim l'informació de les activitats
        arrelListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> arg0, final View arg1,
                                           final int pos, final long id) {

                Intent inte = new Intent(getContext(), InfoActivity.class);
                inte.putExtra("activity", llistaDadesActivitats.get(pos));
                startActivity(inte);
                return false;
            }
        });
        return view;
    }

    //Funció que controla la pulsació del botó d'afegir activitat.
    private View.OnClickListener goToProActivity = new View.OnClickListener() {
        public void onClick(final View v) {
            //Primer diàleg de selecció de tipus d'activitat
            CharSequence options[] = new CharSequence[]{"Project", "Task"};
            final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Select option");
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    switch (i) {
                        case 0: //Es crea projecte nou
                            Intent intent = new Intent(v.getContext(), AddActivity.class);
                            startActivity(intent);
                            break;

                        case 1:
                            //Segon diàleg de selecció del tipus de tasca
                            CharSequence tasks[] = new CharSequence[]{"Normal", "Programed", "Duration"};
                            final AlertDialog.Builder builderInt = new AlertDialog.Builder(getContext());
                            builderInt.setTitle("Select option");
                            builderInt.setItems(tasks, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    switch (i) {
                                        case 0: //Es crea tasca normal nova
                                            Intent intent0 = new Intent(v.getContext(), AddTask.class);
                                            startActivity(intent0);
                                            break;

                                        case 1: //Es crea tasca programada nova
                                            Intent intent1 = new Intent(v.getContext(), AddTaskPro.class);
                                            startActivity(intent1);
                                            break;

                                        case 2: //Es crea tasca durable nova
                                            Intent intent2 = new Intent(v.getContext(), AddTaskDur.class);
                                            startActivity(intent2);
                                            break;

                                        default:
                                            break;
                                    }
                                }
                            });
                            builderInt.show();
                            break;
                        default:
                            break;
                    }
                }
            });
            builder.show();
        }
    };
}