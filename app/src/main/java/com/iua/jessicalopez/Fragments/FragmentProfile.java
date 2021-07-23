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
    TextView cancel;
    TextView listo;
    EditText name;
    EditText email;
    EditText password;
    User user = new User();
    ConexionSQLiteHelper conn;

    Spinner spinner;

    public FragmentProfile() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        conn = new ConexionSQLiteHelper(getContext(), "bd users", null, 1);
        if(getArguments() != null){
            user = (User) getArguments().getSerializable("usuario");
            Toast.makeText(getContext(), "el usuario" + user.getEmail(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_profile, container, false);

        cancel = vista.findViewById(R.id.cancel);
        spinner = vista.findViewById(R.id.spinner);
        name = vista.findViewById(R.id.editTextName);
        email = vista.findViewById(R.id.editTextEmail);
        password = vista.findViewById(R.id.editPasswordPerfil);
        listo = vista.findViewById(R.id.listo);
        consultar();

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getContext(), R.array.preferencias, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                atras();
            }
        });

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
        fragmentTransaction.replace(R.id.containerFragments, new FragmentSetting());
        fragmentTransaction.commit();
    }

    private void consultar() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] emailparam = {user.getEmail()};
        String[] campos = {Constantes.CAMPO_NOMBREAPELLIDO, Constantes.CAMPO_PASSWORD };
        try {
            Cursor cursor = db.query(Constantes.TABLA_USER, campos, Constantes.CAMPO_EMAIL +"=?",emailparam,null, null, null);
            cursor.moveToFirst();
            name.setText(cursor.getString(0));
            password.setText(cursor.getString(1));
            email.setText(user.getEmail());
            cursor.close();

        }catch (Exception e){
            Toast.makeText(getContext(),"Error", Toast.LENGTH_SHORT).show();


        }


    }


}