package com.iua.jessicalopez.Activitys;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.iua.jessicalopez.ClassConnection;
import com.iua.jessicalopez.ConexionSQLiteHelper;
import com.iua.jessicalopez.Constantes.Constantes;
import com.iua.jessicalopez.Fragments.FragmentMovies;
import com.iua.jessicalopez.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    EditText editEmail, editPass;
    ConexionSQLiteHelper conexionSQLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        conexionSQLiteHelper = new ConexionSQLiteHelper(getApplicationContext(),"bd users",null,1);

        editEmail = findViewById(R.id.editEmail);
        editPass = findViewById(R.id.editPassword);

        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase db = conexionSQLiteHelper.getReadableDatabase();

                String[] parametros={editEmail.getText().toString()};
                String[] campos = {Constantes.CAMPO_EMAIL,Constantes.CAMPO_PASSWORD};

                try {
                    Cursor cursor = db.query(Constantes.TABLA_USER,campos,Constantes.CAMPO_EMAIL+"=?",parametros,null,null,null);
                    cursor.moveToFirst();
                    if ((cursor.getString(0).equals(editEmail.getText().toString()))&&(cursor.getString(1).equals(editPass.getText().toString())))
                    {
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Usuario no registrado",Toast.LENGTH_LONG).show();
                    }
                    cursor.close();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Debe completar todos los datos",Toast.LENGTH_LONG).show();
                }




            }
        });


    }

}
