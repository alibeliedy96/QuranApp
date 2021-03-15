package com.example.quranapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.quranapp.R;


public class TasbehFragment extends Fragment  {
private TextView show;
private Button counter,clear;
int result;
    public TasbehFragment() {
        // Required empty public constructor
    }


   View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_tasbeh, container, false);
        show=view.findViewById(R.id.count);
        counter=view.findViewById(R.id.counter);
        counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result++;
                show.setText(Integer.toString(result));
            }
        });
        clear=view.findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result=0;
                show.setText(Integer.toString(result));
            }
        });
        return view;
    }


}