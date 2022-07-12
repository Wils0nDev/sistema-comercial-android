package com.idesolution.distribuidoravdc.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.idesolution.distribuidoravdc.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BottonNavigationBarFragment extends Fragment {


    public BottonNavigationBarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_botton_navigation_bar, container, false);
    }

}
