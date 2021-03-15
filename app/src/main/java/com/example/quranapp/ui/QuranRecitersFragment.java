package com.example.quranapp.ui;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.quranapp.API.APIManager;
import com.example.quranapp.API.Model.RecitersResponse.RecitersItem;
import com.example.quranapp.API.Model.RecitersResponse.RecitersResponse;
import com.example.quranapp.Adapters.RecitersAdapter;
import com.example.quranapp.Adapters.SpinnerAdapter;
import com.example.quranapp.Base.BaseFragment;
import com.example.quranapp.R;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuranRecitersFragment extends BaseFragment {

    public QuranRecitersFragment() {
        // Required empty public constructor
    }
    RecyclerView recitersRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecitersAdapter adapter;
    Spinner spinner;
    View view;
    MediaPlayer mediaPlayer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_quran_reciters, container, false);
        recitersRecyclerView=view.findViewById(R.id.reciters_recycler_view);
        spinner =view.findViewById(R.id.spinner);
        setRecyclerViewWithData();
        getAllReciters();
        return view;
    }

    private void getAllReciters() {
        showProgressBar(R.string.loading);
        APIManager.getApi()
                .getAllReciters()
                .enqueue(new Callback<RecitersResponse>() {
                    @Override
                    public void onResponse(Call<RecitersResponse> call, Response<RecitersResponse> response) {
                      hideProgressBar();
                        if(response.isSuccessful())
                        {
                            adapter.changeData(response.body().getReciters());
                        }

                    }

                    @Override
                    public void onFailure(Call<RecitersResponse> call, Throwable t) {
                    hideProgressBar();
                        showMessage(getString(R.string.error),
                                t.getLocalizedMessage(),getString(R.string.ok));

                    }
                });
    }

    public void   setRecyclerViewWithData()
    {
        layoutManager=new LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false);
        recitersRecyclerView.setLayoutManager(layoutManager);
        adapter=new RecitersAdapter(null);
        recitersRecyclerView.setAdapter(adapter);
        SnapHelper snapHelper=new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recitersRecyclerView);
        // when RecyclerView listening on scroll   get index for item visible
        recitersRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //LayoutManager resposible about getting item visible in RecyclerView
                int firstVisiblePosition = ((LinearLayoutManager)layoutManager)
                        .findFirstVisibleItemPosition();
                RecitersItem item = adapter.getItemAtPosition(firstVisiblePosition);
                setSpinnerData(item);

            }
        });
    }
    ArrayList<Integer> surasIndecies;
    public void setSpinnerData(RecitersItem item){
        String allSurasIndeces = item.getSuras();
        String[] arr = allSurasIndeces.split(",");

        surasIndecies = new ArrayList<>();
//        for (int i =0;i<arr.length;i++){
//            String s=arr[i];
//            surasIndecies.add(Integer.valueOf(s)-1);
//        }
        for (String s : arr){
            surasIndecies.add(Integer.valueOf(s)-1);
        }
        ArrayList<String> names =new ArrayList<>();
        for(int i = 0;i<surasIndecies.size();i++){
            int index = surasIndecies.get(i);
            String name = DataHolder.ArSuras[index];
            names.add(name);
        }
        SpinnerAdapter sAdapter= new SpinnerAdapter(names);
        spinner.setAdapter(sAdapter);
        //need to get position of each item and select this item to play
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //get position of each item
                int suraIndex = surasIndecies.get(position);
                //i add one to suraIndex because if do not add one the first suraIndex become 000.mp3 and do not play
                String suraIndexString = (suraIndex+1)+"";
                //this condition because the digits in url as 3 digits 000.mp3
                while(suraIndexString.length()<3)
                    suraIndexString="0"+suraIndexString;

                int firstVisiblePosition = ((LinearLayoutManager)layoutManager)
                        .findFirstVisibleItemPosition();
                RecitersItem recitersItem = adapter.getItemAtPosition(firstVisiblePosition);
                String URL = recitersItem.getServer()+"/"+suraIndexString+".mp3";
                PlayRadio(URL);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }


    private void StopRadio() {
        if(mediaPlayer!=null)
            mediaPlayer.stop();
    }

    private void PlayRadio(String url) {
        StopRadio();
        mediaPlayer=new MediaPlayer();
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });
        } catch (IOException e) {
            showMessage(R.string.error,R.string.cannot_play_radio,R.string.ok);
        }
    }
}