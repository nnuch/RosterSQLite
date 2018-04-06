package com.example.violi.therostersqliteversion10.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.violi.therostersqliteversion10.R;


public class HomeFragment extends Fragment {

    private ImageView hockeyImage;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment

        //ActionBar and setTitle
        final ActionBar abCustom = ((AppCompatActivity) getActivity()).getSupportActionBar();
        abCustom.setHomeAsUpIndicator(R.drawable.ic_menu);
        abCustom.setDisplayHomeAsUpEnabled(true);
        abCustom.setTitle(R.string.Home);

        hockeyImage = (ImageView) rootView.findViewById(R.id.hockeyImage);




        return rootView;
    }


}
