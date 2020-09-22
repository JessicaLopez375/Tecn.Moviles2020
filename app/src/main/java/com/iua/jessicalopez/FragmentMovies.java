package com.iua.jessicalopez;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentMovies#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMovies extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerMovies;
    ArrayList<MovieVo> movies;

    RecyclerView recyclerAdvance;
    ArrayList<MovieVo> advances;



    public FragmentMovies() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentMovies.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMovies newInstance(String param1, String param2) {
        FragmentMovies fragment = new FragmentMovies();
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
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_movies, container, false);

        movies = new ArrayList<>();
        recyclerMovies = vista.findViewById(R.id.recycler_Movies);
        recyclerMovies.setLayoutManager(new GridLayoutManager(getContext(),2));

        advances = new ArrayList<>();
        recyclerAdvance = vista.findViewById(R.id.recycler_Advances);
        recyclerAdvance.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));


        llenarlista();
        AdapterMovie adapterMovie = new AdapterMovie(movies);
        recyclerMovies.setAdapter(adapterMovie);

        AdapterAdvance adapterAdvance = new AdapterAdvance(advances);
        recyclerAdvance.setAdapter(adapterAdvance);

        return vista;
    }

    private void llenarlista() {
        movies.add(new MovieVo(R.drawable.fuera_de_control));
        movies.add(new MovieVo(R.drawable.el_rey_leon));
        movies.add(new MovieVo(R.drawable.dos_por_el_dinero));
        movies.add(new MovieVo(R.drawable.dracula));
        movies.add(new MovieVo(R.drawable.rapido_y_furioso_8));
        movies.add(new MovieVo(R.drawable.tenet));
        movies.add(new MovieVo(R.drawable.contra_lo_imposible));
        movies.add(new MovieVo(R.drawable.capitan_america));
        movies.add(new MovieVo(R.drawable.proyecto_gemenis));
        movies.add(new MovieVo(R.drawable.everest));


        advances.add(new MovieVo(R.string.advance_1917,R.drawable.advance_1917));
        advances.add(new MovieVo(R.string.Unidos,R.drawable.unidos));
        advances.add(new MovieVo(R.string.Pasasite,R.drawable.pasasite));
        advances.add(new MovieVo(R.string.Parque_Magico,R.drawable.parque_magico));
        advances.add(new MovieVo(R.string.locos_addams,R.drawable.los_locos_addams));


    }

    public interface MoviesFragmentListener {
    }
}