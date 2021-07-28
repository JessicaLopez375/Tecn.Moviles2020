package com.iua.jessicalopez.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iua.jessicalopez.Adapters.AdapterAdvance;
import com.iua.jessicalopez.Adapters.AdapterMovie;
import com.iua.jessicalopez.Conexiones.ClassConnection;
import com.iua.jessicalopez.Modelo.MovieVo;
import com.iua.jessicalopez.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentMovies#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMovies extends Fragment {

    RecyclerView recyclerMovies;
    ArrayList<MovieVo> movies;

    RecyclerView recyclerAdvance;
    ArrayList<MovieVo> advances;


    JSONArray jsonArray;
    String titulo, descripcion, path;
    String uribase = "https://image.tmdb.org/t/p/original";
    String uriCompleta;

    ClassConnection connection = new ClassConnection();

    String response = connection.execute("https://api.themoviedb.org/3/discover/movie?api_key=4e288b8538f5ea2c0a791cc57625bcad").get();



    public FragmentMovies() throws ExecutionException, InterruptedException {
        // Required empty public constructor
    }




    public static FragmentMovies newInstance(String param1, String param2) throws ExecutionException, InterruptedException {
        FragmentMovies fragment = new FragmentMovies();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

        try {

            JSONObject jsonObject = new JSONObject(response);
            jsonArray = jsonObject.getJSONArray("results");


        } catch (JSONException e) {
            e.printStackTrace();
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

        try {
            llenarlista();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AdapterMovie adapterMovie = new AdapterMovie(movies,getContext());
        recyclerMovies.setAdapter(adapterMovie);

        AdapterAdvance adapterAdvance = new AdapterAdvance(advances,getContext());
        recyclerAdvance.setAdapter(adapterAdvance);



        return vista;
    }

    private void llenarlista() throws JSONException {
        for (int i=0;i<=19;i++)
        {
            titulo = jsonArray.getJSONObject(i).getString("title");
            descripcion = jsonArray.getJSONObject(i).getString("overview");
            path = jsonArray.getJSONObject(i).getString("poster_path");
            uriCompleta = uribase + path;
            movies.add(new MovieVo(titulo,uriCompleta,descripcion));
        }

        for (int y=15;y<=19;y++)
        {
                titulo = jsonArray.getJSONObject(y).getString("title");
                path = jsonArray.getJSONObject(y).getString("poster_path");
                uriCompleta = uribase + path;
                advances.add(new MovieVo(titulo,uriCompleta,descripcion));

        }


    }


    public interface MoviesFragmentListener {
    }
}