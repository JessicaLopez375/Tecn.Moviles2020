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

import com.iua.jessicalopez.R;


public class FragmentEnviarEmail extends Fragment {

    Button button;
    EditText mensaje;
    View vista;


    public FragmentEnviarEmail() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vista = inflater.inflate(R.layout.fragment_enviar_email, container, false);
        mensaje = vista.findViewById(R.id.mensaje);
        button = vista.findViewById(R.id.enviarEmailDesarrollador);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String destinatario = "jessilopez895@gmail.com";
                String asunto = "Reportar un problema";
                String msj = mensaje.getText().toString();

                if(msj.equals("")){
                    AlertDialog.Builder msjVacio = new AlertDialog.Builder(getContext());
                    msjVacio.setTitle("Mensaje vacio");
                    msjVacio.setMessage("No se puede enviar un mensaje vacio");
                    msjVacio.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    msjVacio.create().show();
                }
                else {
                    // Defino mi Intent y hago uso del objeto ACTION_SEND
                    Intent intent = new Intent(Intent.ACTION_SEND);

                    // Defino los Strings Email, Asunto y Mensaje con la función putExtra
                    intent.putExtra(Intent.EXTRA_EMAIL,
                            new String[] {destinatario});
                    intent.putExtra(Intent.EXTRA_SUBJECT, asunto);
                    intent.putExtra(Intent.EXTRA_TEXT, msj);

                    // Establezco el tipo de Intent
                    intent.setType("message/rfc822");

                    mensaje.setText("");

                    // Lanzo el selector de cliente de Correo
                    startActivity(Intent.createChooser(intent, "Elige un cliente de Correo:"));



                }

            }
        });


        return vista;
    }




}