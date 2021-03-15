package com.example.quranapp.ui;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.quranapp.Base.BaseActivity;
import com.example.quranapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

public class HomeActivity extends BaseActivity {
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment=null;
            switch (item.getItemId()) {
                case R.id.navigation_quran:
                    fragment = new QuarnFragment();
                    break;

                case R.id.navigation_tasbeh:
                    fragment = new TasbehFragment();
                    break;
                case R.id.navigation_radio:
                    fragment = new RadioFragment();
                    break;
                case R.id.navigation_quran_listening:
                    fragment = new QuranRecitersFragment();
                    break;
            }
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    .commit();
            return true;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navigation =  findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_quran);

    }

}