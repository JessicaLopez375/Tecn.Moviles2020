package com.iua.jessicalopez.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.iua.jessicalopez.Adapters.AdapterMovie;
import com.iua.jessicalopez.Conexiones.ConexionSQLiteHelper;
import com.iua.jessicalopez.Modelo.MovieVo;
import com.iua.jessicalopez.R;

import java.util.ArrayList;

public class FragmentFav extends Fragment {

    View view;
    ConexionSQLiteHelper conexionSQLiteHelper;
    ArrayList<MovieVo> peliculasFavoritas;
    RecyclerView recyclerMoviesFav;

    public FragmentFav() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        conexionSQLiteHelper = new ConexionSQLiteHelper(getContext(),"bd movieNigth", null,1);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fav, container, false);
        peliculasFavoritas = new ArrayList<>();
        peliculasFavoritas = new MovieVo().listarPeliculasFav(conexionSQLiteHelper);
        recyclerMoviesFav = view.findViewById(R.id.recycler_MoviesFav);
        recyclerMoviesFav.setLayoutManager(new GridLayoutManager(getContext(), 2));
        AdapterMovie adapterMovie = new AdapterMovie(peliculasFavoritas, getContext());
        recyclerMoviesFav.setAdapter(adapterMovie);

        return view;
    }
}