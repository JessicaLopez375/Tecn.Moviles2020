package com.iua.jessicalopez.Fragments;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.iua.jessicalopez.Conexiones.ConexionSQLiteHelper;
import com.iua.jessicalopez.Constantes.Constantes;
import com.iua.jessicalopez.Modelo.User;
import com.iua.jessicalopez.R;

public class FragmentProfile extends Fragment {

    View vista;
    TextView textNombreApellidoUser;
    TextView emailUser;
    Button cambiarContrasenia;
    Button cambiarEmail;
    User user = new User();
    ConexionSQLiteHelper conn;
    FragmentCambiarPassword fragmentCambiarPassword;
    FragmentCambiarEmail fragmentCambiarEmail;




    public FragmentProfile() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        conn = new ConexionSQLiteHelper(getContext(), "bd movieNigth", null, 1);

        if(getArguments() != null){
            user = (User) getArguments().getSerializable("usuario");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_profile, container, false);
        textNombreApellidoUser = vista.findViewById(R.id.textNombreApellidoUser);
        cambiarContrasenia = vista.findViewById(R.id.buttonCambiarContrasenia);
        cambiarEmail = vista.findViewById(R.id.buttonCambiarEmail);
        emailUser = vista.findViewById(R.id.emailUser);
        fragmentCambiarPassword = new FragmentCambiarPassword();
        fragmentCambiarEmail = new FragmentCambiarEmail();
        final FragmentTransaction transaction= getFragmentManager().beginTransaction();


        cambiarContrasenia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentCambiarPassword.setArguments(getArguments());
                transaction.replace(R.id.containerFragments,fragmentCambiarPassword );
                transaction.commit();

            }
        });

        cambiarEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentCambiarEmail.setArguments(getArguments());
                transaction.replace(R.id.containerFragments, fragmentCambiarEmail);
                transaction.commit();

            }
        });


        //Se consulta a la BD el usuario
        User aux = user.findUserByEmail(user.getEmail(),conn);

        //Se setean los campos de la vista
        textNombreApellidoUser.setText(aux.getNombreApellido().toUpperCase());
        emailUser.setText(aux.getEmail());






        /*listo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferencias = getContext().getSharedPreferences("Preferencias", getContext().MODE_PRIVATE);

                String nombre = name.getText().toString();
                String perf = email.getText().toString();
                int posicion_pref = spinner.getSelectedItemPosition();
                String pref = spinner.getSelectedItem().toString();

                SharedPreferences.Editor editor = preferencias.edit();
                editor.putString("Nombre", nombre);
                editor.putString("Perfil", perf);
                editor.putInt("Posicion preferencia",posicion_pref);
                editor.putString("Preferencia", pref);

                editor.commit();

                atras();


            }
        });*/


        return vista;
    }

        /*
    public void cargarPreferencias() {
        SharedPreferences preferencias = getContext().getSharedPreferences("Preferencias", getContext().MODE_PRIVATE);

        String nombre = preferencias.getString("Nombre", "-");
        String perf = preferencias.getString("Perfil", "-");
        int posicion = preferencias.getInt("Posicion preferencia",0);

        name.setText(nombre);
        email.setText(perf);
        spinner.setSelection(posicion);

    }*/

    public void atras(){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentSetting fs = new FragmentSetting();
        fs.setArguments(getArguments());
        fragmentTransaction.replace(R.id.containerFragments, fs);

        fragmentTransaction.commit();
    }




    }


