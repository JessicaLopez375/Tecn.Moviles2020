package com.iua.jessicalopez.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.iua.jessicalopez.Fragments.FragmentCambiarEmail;
import com.iua.jessicalopez.Fragments.FragmentEnviarEmail;
import com.iua.jessicalopez.Fragments.FragmentMovies;
import com.iua.jessicalopez.R;

public class EnviarEmailActivity extends AppCompatActivity  {
    FragmentEnviarEmail fragmentEnviarEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_enviar_email);

        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragmentEnviarEmail,new FragmentEnviarEmail()).commit();
    }
}