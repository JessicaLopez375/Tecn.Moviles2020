package com.iua.jessicalopez.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.iua.jessicalopez.ClassConnection;
import com.iua.jessicalopez.ConexionSQLiteHelper;
import com.iua.jessicalopez.Fragments.FragmentMovies;
import com.iua.jessicalopez.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private Button initButton;
    private Button createAccount;
    private JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ConexionSQLiteHelper conexionSQLiteHelper = new ConexionSQLiteHelper(this,"bd users", null,1);

        initButton = findViewById(R.id.init_button);
        createAccount = findViewById(R.id.create_button);

        initButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateAccountActivity.class));
            }
        });
    }

}
