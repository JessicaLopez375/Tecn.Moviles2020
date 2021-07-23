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

    String response = connection.execute("https://api.themoviedb.org/3/discover/movie?api_key=4e288b8538f5ea2c0a791cc57625bcad&page=1").get();



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




            //TextView textView = (TextView) findViewById(R.id.textViewAdvances);
            //textView.setText(jsonObject.getJSONArray("results").getJSONObject(0).getString("title"));

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
        /*movies.add(new MovieVo(R.string.el_rey_leon,R.drawable.el_rey_leon,R.string.sinopsis_fuera_control));
        movies.add(new MovieVo(R.string.dos_por_el_dinero,R.drawable.dos_por_el_dinero,R.string.sinopsis_fuera_control));
        movies.add(new MovieVo(R.string.dracula,R.drawable.dracula,R.string.sinopsis_fuera_control));
        movies.add(new MovieVo(R.string.rapido_furioso,R.drawable.rapido_y_furioso_8,R.string.sinopsis_fuera_control));
        movies.add(new MovieVo(R.string.telnet,R.drawable.tenet,R.string.sinopsis_fuera_control));
        movies.add(new MovieVo(R.string.contra_imposible,R.drawable.contra_lo_imposible,R.string.sinopsis_fuera_control));
        movies.add(new MovieVo(R.string.capitan,R.drawable.capitan_america,R.string.sinopsis_fuera_control));
        movies.add(new MovieVo(R.string.proyecto,R.drawable.proyecto_gemenis,R.string.sinopsis_fuera_control));
        movies.add(new MovieVo(R.string.everest,R.drawable.everest,R.string.sinopsis_fuera_control));
*/

        /*advances.add(new MovieVo(R.string.advance_1917,R.drawable.advance_1917,R.string.sinopsis_fuera_control));
        advances.add(new MovieVo(R.string.Unidos,R.drawable.unidos,R.string.sinopsis_fuera_control));
        advances.add(new MovieVo(R.string.Pasasite,R.drawable.pasasite,R.string.sinopsis_fuera_control));
        advances.add(new MovieVo(R.string.Parque_Magico,R.drawable.parque_magico,R.string.sinopsis_fuera_control));
        advances.add(new MovieVo(R.string.locos_addams,R.drawable.los_locos_addams,R.string.sinopsis_fuera_control));
        */

    }


    public interface MoviesFragmentListener {
    }
}