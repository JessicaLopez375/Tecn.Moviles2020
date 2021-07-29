package com.iua.jessicalopez.Activitys;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.iua.jessicalopez.Fragments.FragmentFav;
import com.iua.jessicalopez.Fragments.FragmentMovies;
import com.iua.jessicalopez.Fragments.FragmentProfile;
import com.iua.jessicalopez.Fragments.FragmentSetting;
import com.iua.jessicalopez.Modelo.Preferencias;
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
                    flag.putInt("conectividad",0);
                }else {
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
    
    public boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getApplication().getSystemService(this.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo()!=null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();

    }

    /*
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_BACK){
            final AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setMessage("¿Desea salir?");
            alerta.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
            });
            alerta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alerta.create().show();
        }
        return super.onKeyDown(keyCode, event);
    }*/
}
