package com.example.quranapp.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.TextView;

import com.example.quranapp.Adapters.VersesAdapter;
import com.example.quranapp.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SurasActivity extends AppCompatActivity {
    ArrayList<String> suraContent;
    VersesAdapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suras);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String sura_name=getIntent().getStringExtra("sura_name");
        String file_name=getIntent().getStringExtra("file_name");


        recyclerView = findViewById(R.id.recycler_view);
        TextView SuraName = findViewById(R.id.sura_name);

        SuraName.setText(sura_name);
        readFile(file_name);

        adapter = new VersesAdapter(suraContent);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

    }

    public ArrayList<String> readFile(String fileName){
        suraContent= new ArrayList<>();
        BufferedReader reader= null;
        try{
            reader=new BufferedReader(
                    new InputStreamReader(getAssets().open(fileName),"UTF-8"));
//            final InputStream file = getAssets().open(fileName);
//            reader = new BufferedReader(new InputStreamReader(file));
            String line = reader.readLine();
            while(line != null){
                //Log.d("StackOverflow", line);
                suraContent.add(line);
                line = reader.readLine();
            }
        } catch(IOException ioe){
            ioe.printStackTrace();
        }
        return suraContent;
    }
    }
