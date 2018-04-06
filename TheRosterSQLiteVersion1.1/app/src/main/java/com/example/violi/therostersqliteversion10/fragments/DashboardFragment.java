package com.example.violi.therostersqliteversion10.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.violi.therostersqliteversion10.AddActivity;
import com.example.violi.therostersqliteversion10.R;
import com.example.violi.therostersqliteversion10.RosterActivity;


public class DashboardFragment extends Fragment {


    private Button btnAddData;
    private ImageButton btnViewData;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        // Inflate the layout for this fragment


        final ActionBar abCustom  = ((AppCompatActivity) getActivity()).getSupportActionBar();
        //abCustom .setHomeAsUpIndicator(R.drawable.ic_menu);
        abCustom .setDisplayHomeAsUpEnabled(true);
        abCustom .setTitle(R.string.Dashboard);

        final Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.activity_scale);

        btnAddData = (Button) rootView.findViewById(R.id.btnAdd);
        btnViewData = (ImageButton) rootView.findViewById(R.id.btnView);

        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To check all input fields are not empty
                btnAddData.startAnimation(animation);
                Intent intent = new Intent(getActivity(), AddActivity.class);
                startActivity(intent);
            }
        });
        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnViewData.startAnimation(animation);
                Intent intent = new Intent(getActivity(), RosterActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

}
