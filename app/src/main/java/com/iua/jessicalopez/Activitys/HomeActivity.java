package com.iua.jessicalopez.Activitys;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.iua.jessicalopez.Fragments.FragmentFav;
import com.iua.jessicalopez.Fragments.FragmentMovies;
import com.iua.jessicalopez.Fragments.FragmentSetting;
import com.iua.jessicalopez.R;

public class HomeActivity extends AppCompatActivity implements FragmentMovies.MoviesFragmentListener,
        FragmentSetting.SettingFragmentListener {

    FragmentMovies fragmentMovies;
    FragmentSetting fragmentSetting;
    FragmentFav fragmentFav;



    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);


        fragmentMovies = new FragmentMovies();
        fragmentSetting = new FragmentSetting();
        fragmentFav = new FragmentFav();
        //Por defecto el primer fragment es el movie
        getSupportFragmentManager().beginTransaction().add(R.id.containerFragments,fragmentMovies).commit();


    }


    public void onClick(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (view.getId())
        {
            case (R.id.setting_button):
                transaction.replace(R.id.containerFragments,fragmentSetting);
                break;
            case(R.id.home_button):
                transaction.replace(R.id.containerFragments,fragmentMovies);
                break;
            case(R.id.fav_button):
                transaction.replace(R.id.containerFragments,fragmentFav);
                break;
        }

        transaction.commit();


    }


}
