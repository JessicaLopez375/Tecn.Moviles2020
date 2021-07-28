package com.iua.jessicalopez.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iua.jessicalopez.Activitys.LoginActivity;
import com.iua.jessicalopez.Conexiones.ConexionSQLiteHelper;
import com.iua.jessicalopez.Modelo.User;
import com.iua.jessicalopez.R;

public class FragmentCambiarEmail extends Fragment {


    View vista;
    Button buttonAtrasCE, buttonGuardarCE;
    User user = new User();
    ConexionSQLiteHelper conexionSQLiteHelper;
    EditText emailActual, nuevoEmail;

    public FragmentCambiarEmail() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        conexionSQLiteHelper = new ConexionSQLiteHelper(getContext(), "bd movieNigth", null, 1);
        if (getArguments() != null) {
            user = (User) getArguments().getSerializable("usuario");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_cambiar_email, container, false);
        buttonAtrasCE = vista.findViewById(R.id.buttonAtrasCE);
        buttonGuardarCE = vista.findViewById(R.id.buttonGuardarCE);
        emailActual = vista.findViewById(R.id.emailActual);
        nuevoEmail = vista.findViewById(R.id.nuevoEmail);

        buttonAtrasCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atras();
            }
        });

        buttonGuardarCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String error = "";
                String emailact = emailActual.getText().toString();
                String emailnew = nuevoEmail.getText().toString();


                if (camposVacios(emailact, emailnew)) {
                    AlertDialog.Builder alertaCamposVacios = new AlertDialog.Builder(getContext());
                    alertaCamposVacios.setTitle("Error");
                    alertaCamposVacios.setMessage("Se deben completar todos los campos");
                    alertaCamposVacios.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            atras();
                        }
                    });
                    alertaCamposVacios.create().show();
                } else {
                    if (!user.validarEmail(emailact) || !user.getEmail().equals(emailact)) {
                        error = error + "\n" + "El email actual no es valido";
                    } else {
                        if (!user.validarEmail(emailnew)) {
                            error = error + "\n" + "El nuevo email no es valido";
                        } else {
                            if (emailnew.equals(emailact)) {
                                error = error + "\n" + "El nuevo email coincide con el actual";
                            }
                        }
                    }


                    if (!error.equals("")) {
                        AlertDialog.Builder alertaErrores = new AlertDialog.Builder(getContext());
                        alertaErrores.setTitle("Error");
                        alertaErrores.setMessage("Se han cometido los siguientes errores: " + error);
                        alertaErrores.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                emailActual.setText("");
                                nuevoEmail.setText("");
                            }
                        });
                        alertaErrores.create().show();

                    } else {
                        user.actualizarEmail(user.getEmail(), emailnew, conexionSQLiteHelper);
                        AlertDialog.Builder passwordCambiada = new AlertDialog.Builder(getContext());
                        passwordCambiada.setTitle("Exito");
                        passwordCambiada.setMessage("Se ha cambiado exitosamente el email. Debera loguearse nuevamente");
                        passwordCambiada.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getContext(), LoginActivity.class);
                                startActivity(intent);
                            }
                        });
                        passwordCambiada.create().show();

                    }

                }
            }
        });


        return vista;
    }

    public void atras() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentProfile fp = new FragmentProfile();
        fp.setArguments(getArguments());
        fragmentTransaction.replace(R.id.containerFragments, fp);

        fragmentTransaction.commit();
    }

    public boolean camposVacios(String emailAct, String emailNew) {
        if (emailAct.equals("") || emailNew.equals("")) {
            return true;
        } else {
            return false;
        }
    }

}