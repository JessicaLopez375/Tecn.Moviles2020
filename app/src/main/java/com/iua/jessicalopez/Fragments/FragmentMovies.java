package com.iua.jessicalopez.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.UnicodeSetSpanner;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.iua.jessicalopez.Activitys.HomeActivity;
import com.iua.jessicalopez.Adapters.AdapterAdvance;
import com.iua.jessicalopez.Adapters.AdapterMovie;
import com.iua.jessicalopez.Conexiones.ClassConnection;
import com.iua.jessicalopez.Conexiones.ConexionSQLiteHelper;
import com.iua.jessicalopez.Dialogs.DialogSinConexionFragment;
import com.iua.jessicalopez.Modelo.MovieVo;
import com.iua.jessicalopez.Modelo.Preferencias;
import com.iua.jessicalopez.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class FragmentMovies extends Fragment {

    RecyclerView recyclerMovies;
    ArrayList<MovieVo> movies;

    RecyclerView recyclerAdvance;
    ArrayList<MovieVo> advances;

    SwipeRefreshLayout swipeRefreshLayout;

    JSONArray pelis;
    JSONArray populares;
    JSONArray generos;

    String titulo, descripcion, path;
    String uribase = "https://image.tmdb.org/t/p/original";
    String uriCompleta;
    String URLGeneral = "https://api.themoviedb.org/3/discover/movie?api_key=4e288b8538f5ea2c0a791cc57625bcad";
    String URLPopulares = "https://api.themoviedb.org/3/discover/movie?api_key=4e288b8538f5ea2c0a791cc57625bcad&sort_by=popularity.desc";
    String URLPaginada = "https://api.themoviedb.org/3/discover/movie?api_key=4e288b8538f5ea2c0a791cc57625bcad&page=";
    String URLGeneros = "https://api.themoviedb.org/3/genre/movie/list?api_key=4e288b8538f5ea2c0a791cc57625bcad&language=en-US";

    int conectividad;
    String idGenero;
    String response;

    ConexionSQLiteHelper conexionSQLiteHelper;

    //String response = connection.execute("https://api.themoviedb.org/3/discover/movie?api_key=4e288b8538f5ea2c0a791cc57625bcad&page=3").get();
    //String masPopulares = connection2.execute("https://api.themoviedb.org/3/discover/movie?api_key=4e288b8538f5ea2c0a791cc57625bcad&sort_by=popularity.desc").get();

    public FragmentMovies() throws ExecutionException, InterruptedException {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            conectividad = getArguments().getInt("conectividad");

        }

        //Se obtiene la preferencia de genero
        PreferenceManager.setDefaultValues(getContext(), R.xml.preferencias,false);
        SharedPreferences genero = PreferenceManager.getDefaultSharedPreferences(getContext());
        Preferencias p = new Preferencias();
        String filtroGenero = p.obtenerPreferencias(genero,getContext());

        //BD
        conexionSQLiteHelper = new ConexionSQLiteHelper(getContext(), "bd movieNigth", null, 1);


        //Validamos si hay conectividad
        if (conectividad == 1) {
            populares = cargarPeliculas(URLPopulares, new ClassConnection());
            generos = cargarPeliculas(URLGeneros, new ClassConnection(), "genres");
            try {
                idGenero = encontrarIDGenero(filtroGenero, generos);
                pelis = cargarPeliculas(URLPaginada.concat("3&with_genres=" + idGenero), new ClassConnection());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View vista = inflater.inflate(R.layout.fragment_movies, container, false);

        swipeRefreshLayout = vista.findViewById(R.id.swipe);


        movies = new ArrayList<>();
        recyclerMovies = vista.findViewById(R.id.recycler_Movies);
        recyclerMovies.setLayoutManager(new GridLayoutManager(getContext(), 2));

        advances = new ArrayList<>();
        recyclerAdvance = vista.findViewById(R.id.recycler_Advances);
        recyclerAdvance.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        if (conectividad == 1) {
            try {
                llenarlista(pelis, populares);
                swipeRefreshLayout.setRefreshing(false);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            DialogSinConexionFragment dialogSinConexionFragment = new DialogSinConexionFragment();
            dialogSinConexionFragment.show(getFragmentManager(), "Dialogo sin conexión");
            swipeRefreshLayout.setRefreshing(false);
        }


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                boolean c = isConnected();
                if (c) {
                    try {
                        pelis = cargarPeliculas(URLPaginada.concat("6&with_genres=" + idGenero), new ClassConnection());
                        populares = cargarPeliculas(URLPaginada + "5", new ClassConnection());
                        llenarlista(pelis, populares);

                        Collections.shuffle(movies);
                        Collections.shuffle(advances);
                        swipeRefreshLayout.setRefreshing(false);
                        AdapterMovie adapterMovie2 = new AdapterMovie(movies, getContext());
                        recyclerMovies.setAdapter(adapterMovie2);

                        AdapterAdvance adapterAdvance2 = new AdapterAdvance(advances, getContext());
                        recyclerAdvance.setAdapter(adapterAdvance2);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {

                    DialogSinConexionFragment dialogSinConexionFragment = new DialogSinConexionFragment();
                    dialogSinConexionFragment.show(getFragmentManager(), "Dialogo sin conexión");
                    swipeRefreshLayout.setRefreshing(false);
                }

            }
        });

        AdapterMovie adapterMovie = new AdapterMovie(movies, getContext());
        recyclerMovies.setAdapter(adapterMovie);

        AdapterAdvance adapterAdvance = new AdapterAdvance(advances, getContext());
        recyclerAdvance.setAdapter(adapterAdvance);


        return vista;
    }

    private void llenarlista(JSONArray pelis, JSONArray populares) throws JSONException {

        for (int i = 0; i < pelis.length(); i++) {
            titulo = pelis.getJSONObject(i).getString("title");
            descripcion = pelis.getJSONObject(i).getString("overview");
            path = pelis.getJSONObject(i).getString("poster_path");
            uriCompleta = uribase + path;
            movies.add(new MovieVo(titulo, uriCompleta, descripcion));
        }

        for (int y = 1; y < populares.length(); y++) {
            titulo = populares.getJSONObject(y).getString("title");
            path = populares.getJSONObject(y).getString("poster_path");
            uriCompleta = uribase + path;
            advances.add(new MovieVo(titulo, uriCompleta, descripcion));

        }


    }


    public JSONArray cargarPeliculas(String url, ClassConnection connection) {

        JSONArray resultado = null;
        try {
            response = connection.execute(url).get();
            JSONObject result = new JSONObject(response);
            resultado = result.getJSONArray("results");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();


        }
        return resultado;
    }

    public JSONArray cargarPeliculas(String url, ClassConnection connection, String name) {

        JSONArray resultado = null;
        try {
            response = connection.execute(url).get();
            JSONObject result = new JSONObject(response);
            resultado = result.getJSONArray(name);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();


        }
        return resultado;
    }

    public interface MoviesFragmentListener {

    }

    public boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(getContext().CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();

    }

    public String encontrarIDGenero(String genero, JSONArray generos) throws JSONException {
        String id = "";
        String name;
        for (int i = 0; i < generos.length(); i++) {
            name = generos.getJSONObject(i).getString("name");
            if (name.equals(genero)) {
                id = generos.getJSONObject(i).getString("id");
            }

        }
        return id;
    }


}