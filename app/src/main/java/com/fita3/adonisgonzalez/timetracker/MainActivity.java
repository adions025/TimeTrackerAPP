package com.fita3.adonisgonzalez.timetracker;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Classe main que controla el funcionament de l'aplicació, és la primera activitat carregada
 */
public class MainActivity extends AppCompatActivity{
    //Inicialització de les variables internes necessàries
    private static final String TAG = "MainActivity";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager viewPage;
    String[]  bankNames = {"BOI","SBI","HDFC","PNB","OBC"};
    //Funció onCreate de la vista, amb el control de l'adaptador de fragments a la pàgina i els tabs d'aquesta
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Debug
        Log.d(TAG, "onCreate: String");
        //Adaptadors dels tabs
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        viewPage = (ViewPager) findViewById(R.id.container);
        setupViewerPager(viewPage);
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPage);
    }

    //Funció per adaptar els tabs a la vista principal
    private void setupViewerPager(ViewPager viewerPager) {
        //Inicialització del nom del tab
        String tabName = getResources().getString(R.string.tab_text_2);
        //Adaptació dels fragments a la vista principal
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new ActivityTab(), "Activity");
        adapter.addFragment(new ReportsActivityTab(), tabName);
        viewerPager.setAdapter(adapter);

    }
    //En clicar la tecla virtual d'anar endarrere, anar a la carpeta superior i mostrar les activitats d'aquesta
    @Override
    public void onBackPressed() {
        try {
            sendBroadcast(new Intent(LlistaActivitatsActivity.PUJA_NIVELL));
            sendBroadcast(new Intent(LlistaActivitatsActivity.DONAM_FILLS));
        } catch (Exception RuntimeException){
            finish();
        }
    }

    //Creació del menú superior (cerca, opcions i informació) (no implementat, si dissenyat)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                break;

            case R.id.acercaDe:
                break;

            case R.id.menu_buscar:
                break;
        }
        return false;
    }
}
