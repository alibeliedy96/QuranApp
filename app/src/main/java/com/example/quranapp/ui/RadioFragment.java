package com.example.quranapp.ui;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quranapp.API.APIManager;
import com.example.quranapp.API.Model.RadioResponse.RadiosItem;
import com.example.quranapp.API.Model.RadioResponse.RadiosResponse;
import com.example.quranapp.Adapters.RadioAdapter;
import com.example.quranapp.Base.BaseFragment;
import com.example.quranapp.R;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RadioFragment extends BaseFragment {


    public RadioFragment() {
        // Required empty public constructor
    }
    RecyclerView radioRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    RadioAdapter adapter;
    View view;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_radio, container, false);
        radioRecyclerView=view.findViewById(R.id.radioRecycler_view);
        setRecyclerViewWithData();
        getRadioChannels();
        return view;
    }

    private void getRadioChannels() {
        showProgressBar(R.string.loading);
        APIManager.getApi()
                .getAllRadioChannels()
                .enqueue(new Callback<RadiosResponse>() {
                    @Override
                    public void onResponse(Call<RadiosResponse> call, Response<RadiosResponse> response) {
                        hideProgressBar();
                        if(response.isSuccessful())
                        {
                            adapter.changeData(response.body().getRadios());
                        }
                        else
                        {
                            showMessage(getString(R.string.error),
                                    response.code()+"",getString(R.string.ok));
                        }
                    }

                    @Override
                    public void onFailure(Call<RadiosResponse> call, Throwable t) {
                        hideProgressBar();
                        showMessage(getString(R.string.error),t.getLocalizedMessage(),getString(R.string.ok));
                    }
                });
    }

    public void   setRecyclerViewWithData()
    {
        layoutManager=new LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false);
        radioRecyclerView.setLayoutManager(layoutManager);
        adapter=new RadioAdapter(null);
        radioRecyclerView.setAdapter(adapter);
        SnapHelper snapHelper=new PagerSnapHelper();
        snapHelper.attachToRecyclerView(radioRecyclerView);
        adapter.setOnPlayClickListener(new RadioAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, RadiosItem radio) {
                PlayRadio(radio.getURL());
            }
        });
        adapter.setOnStopClickListener(new RadioAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, RadiosItem radio) {
              StopRadio();
            }
        });

    }

    MediaPlayer mediaPlayer;
    private void StopRadio() {
        if(mediaPlayer!=null)
            mediaPlayer.stop();
    }

    private void PlayRadio(String url) {
        StopRadio();
        mediaPlayer=new MediaPlayer();
        try {
            mediaPlayer.setDataSource(url);
            //prepare function used to start media player in main thread we can using prepare if we get file from mobile not URl
//            mediaPlayer.prepare();
//            mediaPlayer.start();
            //prepare media player in background thread
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
        } catch (IOException e) {
            showMessage(R.string.error,R.string.cannot_play_radio,R.string.ok);
        }
    }
}