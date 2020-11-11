package com.iua.jessicalopez.Activitys;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.iua.jessicalopez.ConexionSQLiteHelper;
import com.iua.jessicalopez.Constantes.Constantes;
import com.iua.jessicalopez.R;

public class CreateAccountActivity extends AppCompatActivity {
    EditText editName, editEmail, editPassword, editPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        editPassword2 = findViewById(R.id.editPassword2);
    }
    
    public void onClick(View view)
    {
        registrarUsuarios();
        Toast.makeText(getApplicationContext(),"Usuario registrado",Toast.LENGTH_LONG).show();
        startActivity(new Intent(CreateAccountActivity.this, HomeActivity.class));
    }

    private void registrarUsuarios() {
        ConexionSQLiteHelper conexionSQLiteHelper = new ConexionSQLiteHelper(this,"bd users", null,1);

        //Abro la base de datos para poder editarlo
        SQLiteDatabase db = conexionSQLiteHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Constantes.CAMPO_NOMBREAPELLIDO, editName.getText().toString());
        values.put(Constantes.CAMPO_EMAIL, editEmail.getText().toString());
        values.put(Constantes.CAMPO_PASSWORD, editPassword.getText().toString());

        Long idResultante = db.insert(Constantes.TABLA_USER,Constantes.CAMPO_ID, values);
        System.out.println(idResultante);


    }
}
