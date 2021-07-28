package com.iua.jessicalopez.Fragments;

import android.os.Bundle;

import androidx.preference.MultiSelectListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreference;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.iua.jessicalopez.Activitys.HomeActivity;
import com.iua.jessicalopez.Activitys.MainActivity;
import com.iua.jessicalopez.Conexiones.ConexionSQLiteHelper;
import com.iua.jessicalopez.Modelo.User;
import com.iua.jessicalopez.R;

import java.util.Objects;


public class FragmentSetting extends PreferenceFragmentCompat {
    public FragmentSetting() {

    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferencias, rootKey);


    }












    /*public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        conn = new ConexionSQLiteHelper(getContext(), "bd movieNigth", null, 1);

        if (getArguments() != null) {
            user = (User) getArguments().getSerializable("usuario");
        } else {

        }


    }


*/

    /*
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
        conn = new ConexionSQLiteHelper(getContext(), "bd movieNigth", null, 1);

        if(getArguments() != null){
            user = (User) getArguments().getSerializable("usuario");
            fragmentProfile.setArguments(getArguments());
            Toast.makeText(getContext(), "usuario encontrado", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getContext(), "usuario encontrado", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       vista = inflater.inflate(R.layout.fragment_setting, container, false);
       buttonProfile = vista.findViewById(R.id.my_profile);
       name = vista.findViewById(R.id.nameSetting);
       User aux = user.findUserByEmail(user.getEmail(), conn);
       name.setText(aux.getNombreApellido());

       buttonProfile.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(getContext(), "el usuario es"+user.getEmail(), Toast.LENGTH_SHORT).show();
               //FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
               FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
               //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
               fragmentTransaction.replace(R.id.containerFragments, fragmentProfile);
               fragmentTransaction.commit();

           }
       });

        return vista;
    }

    public interface SettingFragmentListener {
    }*/
}