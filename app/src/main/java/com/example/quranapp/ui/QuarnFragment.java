package com.example.quranapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quranapp.Adapters.QuranSurasAdapter;
import com.example.quranapp.R;


public class QuarnFragment extends Fragment {


    QuranSurasAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    public QuarnFragment() {
        // Required empty public constructor
    }


   View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_quarn, container, false);
        recyclerView=view.findViewById(R.id.recycler_view_suras);

        adapter=new QuranSurasAdapter(DataHolder.ArSuras);
        layoutManager=new GridLayoutManager(getActivity(),3,GridLayoutManager.VERTICAL,false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        //using snapHelper from page after page
//        SnapHelper snapHelper=new PagerSnapHelper();
//        snapHelper.attachToRecyclerView(recyclerView);
        adapter.setOnItemClickListener(new QuranSurasAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, String name) {
                Intent intent=new Intent(getActivity(),SurasActivity.class);
                intent.putExtra("sura_name",name);
                intent.putExtra("file_name",(pos+1)+".txt");
                startActivity(intent);
            }
        });

return view;
    }
}