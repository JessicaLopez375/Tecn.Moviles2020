package com.iua.jessicalopez;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentProfile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    View vista;
    TextView cancel;
    TextView listo;
    EditText name;
    EditText perfil;
    Spinner spinner;

    public FragmentProfile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentProfile.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentProfile newInstance(String param1, String param2) {
        FragmentProfile fragment = new FragmentProfile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_profile, container, false);

        cancel = vista.findViewById(R.id.cancel);
        spinner = vista.findViewById(R.id.spinner);
        name = vista.findViewById(R.id.editTextName);
        perfil = vista.findViewById(R.id.editTextPerfil);
        listo = vista.findViewById(R.id.listo);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getContext(), R.array.preferencias, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        cargarPreferencias();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                atras();
            }
        });

        listo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferencias = getContext().getSharedPreferences("Preferencias", getContext().MODE_PRIVATE);

                String nombre = name.getText().toString();
                String perf = perfil.getText().toString();
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
        });


        return vista;
    }


    public void cargarPreferencias() {
        SharedPreferences preferencias = getContext().getSharedPreferences("Preferencias", getContext().MODE_PRIVATE);

        String nombre = preferencias.getString("Nombre", "-");
        String perf = preferencias.getString("Perfil", "-");
        int posicion = preferencias.getInt("Posicion preferencia",0);

        name.setText(nombre);
        perfil.setText(perf);
        spinner.setSelection(posicion);

    }

    public void atras(){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerFragments, new FragmentSetting());
        fragmentTransaction.commit();
    }

}