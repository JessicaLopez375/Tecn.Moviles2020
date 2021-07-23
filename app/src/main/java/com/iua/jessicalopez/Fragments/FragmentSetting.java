package com.iua.jessicalopez.Fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iua.jessicalopez.Conexiones.ConexionSQLiteHelper;
import com.iua.jessicalopez.Constantes.Constantes;
import com.iua.jessicalopez.Modelo.User;
import com.iua.jessicalopez.R;


public class FragmentSetting extends Fragment {



    FragmentProfile fragmentProfile = new FragmentProfile();
    Button buttonProfile;
    View vista;
    User user = new User();
    TextView name;
    ConexionSQLiteHelper conn;
    public FragmentSetting() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        conn = new ConexionSQLiteHelper(getContext(), "bd users", null, 1);

        if(getArguments() != null){
            user = (User) getArguments().getSerializable("usuario");
            fragmentProfile.setArguments(getArguments());
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       vista = inflater.inflate(R.layout.fragment_setting, container, false);
       buttonProfile = vista.findViewById(R.id.my_profile);
       name = vista.findViewById(R.id.nameSetting);
       consultar();

       buttonProfile.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
               FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
               fragmentTransaction.replace(R.id.contenedorSetting, fragmentProfile);
               fragmentTransaction.commit();

           }
       });

        return vista;
    }

    private void consultar() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] email = {user.getEmail()};
        String[] campos = {Constantes.CAMPO_NOMBREAPELLIDO, Constantes.CAMPO_PASSWORD };
        try {
            Cursor cursor = db.query(Constantes.TABLA_USER, campos, Constantes.CAMPO_EMAIL +"=?",email,null, null, null);
            cursor.moveToFirst();
            name.setText(cursor.getString(0));
            cursor.close();

        }catch (Exception e){


        }


    }

    public interface SettingFragmentListener {
    }
}