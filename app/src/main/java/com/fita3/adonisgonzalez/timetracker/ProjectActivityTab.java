package com.fita3.adonisgonzalez.timetracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class ProjectActivityTab extends Fragment {

    private static final String TAG = "ProjectActivityTab";
    private Button btnTEST;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.project_activity_tab, container, false);


        btnTEST = (Button) view.findViewById(R.id.btnTEST);

        btnTEST.setOnClickListener(goToProActivity);


        return view;

    }



    private View.OnClickListener goToProActivity = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), LlistaActivitatsActivity.class);
            startActivity(intent);
        }
    };






}

