package com.example.juste.hangedmangame;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Konstantin on 12-01-2016.
 */
public class lostfragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View lostFragmentView = inflater.inflate(R.layout.lostfragment, container, false);
        return lostFragmentView;
    }
}
