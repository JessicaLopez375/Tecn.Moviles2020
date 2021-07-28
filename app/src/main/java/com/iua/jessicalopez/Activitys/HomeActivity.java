package com.iua.jessicalopez.Activitys;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import com.iua.jessicalopez.Fragments.FragmentFav;
import com.iua.jessicalopez.Fragments.FragmentMovies;
import com.iua.jessicalopez.Fragments.FragmentProfile;
import com.iua.jessicalopez.Fragments.FragmentSetting;
import com.iua.jessicalopez.Modelo.User;
import com.iua.jessicalopez.R;


import java.util.concurrent.ExecutionException;

public class HomeActivity extends AppCompatActivity implements FragmentMovies.MoviesFragmentListener {

    FragmentMovies fragmentMovies;
    FragmentSetting fragmentSetting;
    FragmentFav fragmentFav;
    FragmentProfile fragmentProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        //Recibimos el usuario logueado (email)
        Bundle usuarioEnviado = getIntent().getExtras();

        try {
            Bundle flag = new Bundle();
            fragmentMovies = new FragmentMovies();
                if(!isConnected()){
                    Toast.makeText(this, "SIN INTERNET", Toast.LENGTH_SHORT).show();
                    flag.putInt("conectividad",0);
                }else {
                    Toast.makeText(this, "con INTERNET", Toast.LENGTH_SHORT).show();
                    flag.putInt("conectividad",1);
                }
            fragmentMovies.setArguments(flag);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fragmentSetting = new FragmentSetting();
        fragmentFav = new FragmentFav();
        fragmentProfile = new FragmentProfile();
        fragmentProfile.setArguments(usuarioEnviado);
        //Por defecto el primer fragment es el movie
        getSupportFragmentManager().beginTransaction().add(R.id.containerFragments, fragmentMovies).commit();

        if(!isConnected()){
            Toast.makeText(this, "SIN INTERNET", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "con INTERNET", Toast.LENGTH_SHORT).show();
        }


    }


    public void onClick(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (view.getId()) {
            case (R.id.setting_button):
                transaction.replace(R.id.containerFragments, fragmentSetting);
                break;
            case (R.id.home_button):
                transaction.replace(R.id.containerFragments, fragmentMovies);
                break;
            case (R.id.fav_button):
                transaction.replace(R.id.containerFragments, fragmentFav);
                break;
            case (R.id.cuenta_button):
                transaction.replace(R.id.containerFragments, fragmentProfile);
                break;
        }

        transaction.commit();


    }

    public void setDayNigth(int mode) {
        if (mode == 0) {
            Toast.makeText(this, "ESTOY EN 0", Toast.LENGTH_SHORT).show();
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }

    public boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getApplication().getSystemService(this.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo()!=null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();

    }


}
