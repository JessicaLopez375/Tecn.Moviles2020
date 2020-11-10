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
import com.iua.jessicalopez.ClassConnection;
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

    String titulo;
    String descripcion;
    String path;
    String uribase = "https://image.tmdb.org/t/p/original";

    ClassConnection connection = new ClassConnection();


    public FragmentMovies() {
        // Required empty public constructor
    }


    public static FragmentMovies newInstance(String param1, String param2) {
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
            String response = connection.execute("https://api.themoviedb.org/3/discover/movie?api_key=4e288b8538f5ea2c0a791cc57625bcad").get();
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            titulo = jsonArray.getJSONObject(1).getString("title");
            descripcion = jsonArray.getJSONObject(1).getString("overview");
            path = jsonArray.getJSONObject(1).getString("poster_path");
            uribase = uribase + path;
            //TextView textView = (TextView) findViewById(R.id.textViewAdvances);
            //textView.setText(jsonObject.getJSONArray("results").getJSONObject(0).getString("title"));

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
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

        System.out.println(titulo);
        System.out.println(descripcion);
        llenarlista();
        AdapterMovie adapterMovie = new AdapterMovie(movies);
        recyclerMovies.setAdapter(adapterMovie);

        AdapterAdvance adapterAdvance = new AdapterAdvance(advances);
        recyclerAdvance.setAdapter(adapterAdvance);



        return vista;
    }

    private void llenarlista() {



        movies.add(new MovieVo(titulo,R.drawable.fuera_de_control,descripcion));
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

        advances.add(new MovieVo(R.string.advance_1917,R.drawable.advance_1917,R.string.sinopsis_fuera_control));
        advances.add(new MovieVo(R.string.Unidos,R.drawable.unidos,R.string.sinopsis_fuera_control));
        advances.add(new MovieVo(R.string.Pasasite,R.drawable.pasasite,R.string.sinopsis_fuera_control));
        advances.add(new MovieVo(R.string.Parque_Magico,R.drawable.parque_magico,R.string.sinopsis_fuera_control));
        advances.add(new MovieVo(R.string.locos_addams,R.drawable.los_locos_addams,R.string.sinopsis_fuera_control));


    }


    public interface MoviesFragmentListener {
    }
}