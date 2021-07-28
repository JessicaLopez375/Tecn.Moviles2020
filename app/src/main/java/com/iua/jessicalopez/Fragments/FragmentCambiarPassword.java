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

import com.google.android.material.textfield.TextInputEditText;
import com.iua.jessicalopez.Activitys.HomeActivity;
import com.iua.jessicalopez.Activitys.LoginActivity;
import com.iua.jessicalopez.Conexiones.ConexionSQLiteHelper;
import com.iua.jessicalopez.Modelo.User;
import com.iua.jessicalopez.R;

public class FragmentCambiarPassword extends Fragment {

    User user = new User();
    View vista;
    Button atras;
    Button guardar;
    EditText passwordActual, passwordNueva, repetirNuevaPassword;
    ConexionSQLiteHelper conexionSQLiteHelper;

    public FragmentCambiarPassword() {
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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_cambiar_password, container, false);
        atras = vista.findViewById(R.id.buttonAtras);
        guardar = vista.findViewById(R.id.buttonGuardar);
        passwordActual = vista.findViewById(R.id.contraseñaActual);
        passwordNueva = vista.findViewById(R.id.passwordNueva);
        repetirNuevaPassword = vista.findViewById(R.id.repetirPasswordNueva);


        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atras();
            }
        });


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String error = "";
                String passActual = passwordActual.getText().toString();
                String nuevaPass = passwordNueva.getText().toString();
                String repetirNuevaPass = repetirNuevaPassword.getText().toString();

                if (camposVacios(passActual, nuevaPass, repetirNuevaPass)) {
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
                    if (!validarPasswordVieja(passActual)) {
                        error = error + "\n" + "La contraseña actual es incorrecta\n";
                    }

                    if (!validarPassword(nuevaPass) || !validarPassword(repetirNuevaPass)) {
                        error = error + "\n" + "La nueva contraseña es invalida\n";
                    }else {
                        if (!coincidenciaPasswords(nuevaPass, repetirNuevaPass)) {
                            error = error + "\n" + "Las contraseñas no coinciden\n";
                        } else {
                            if (coincidenciaPasswords(nuevaPass, passActual)){
                                error = error + "\n" + "La nueva contraseña es identica a la contraseña actual\n";
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
                                passwordActual.setText("");
                                passwordNueva.setText("");
                                repetirNuevaPassword.setText("");
                            }
                        });
                        alertaErrores.create().show();

                    } else {
                        user.actualizarPassword(user.getEmail(), nuevaPass, conexionSQLiteHelper);
                        AlertDialog.Builder passwordCambiada = new AlertDialog.Builder(getContext());
                        passwordCambiada.setTitle("Exito");
                        passwordCambiada.setMessage("Se ha cambiado exitosamente la contraseña. Debera loguearse nuevamente");
                        passwordCambiada.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent =  new Intent(getContext(), LoginActivity.class);
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

    public boolean validarPasswordVieja(String passActual) {
        User aux = user.findUserByEmail(user.getEmail(), conexionSQLiteHelper);
        if (passActual.equals(aux.getPassword())) {
            return true;
        } else {
            return false;
        }

    }

    public boolean camposVacios(String passActual, String nuevaPass, String repetirNuevaPass) {
        if (passActual.equals("") || nuevaPass.equals("") || repetirNuevaPass.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean coincidenciaPasswords(String nuevaPass, String repetirNuevaPass) {
        if (nuevaPass.equals(repetirNuevaPass)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validarPassword(String password) {
        if (password.equals(null) || password.trim().length() == 0 || password.length() < 8) {
            return false;
        } else {
            return true;
        }
    }

    public void cerrarApp(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }




}