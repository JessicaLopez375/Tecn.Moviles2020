package com.iua.jessicalopez.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iua.jessicalopez.Conexiones.ConexionSQLiteHelper;
import com.iua.jessicalopez.Modelo.MovieVo;
import com.iua.jessicalopez.R;

import java.util.ArrayList;

public class FragmentDetails extends Fragment {



    ImageView imageMovie;
    TextView textView;
    TextView textSinopsis;
    String titulo;
    String descripcion;
    String uriCompleta;

    MovieVo movieDetails;
    MovieVo movieFavorita;
    ArrayList<MovieVo> moviesFav;
    Context context;
    Button favbutton;

    RecyclerView MoviesFav;
    ConexionSQLiteHelper conexionSQLiteHelper;
    Boolean flag = false;




    public FragmentDetails(Context context) {
        this.context = context;
        // Required empty public constructor
    }
    public FragmentDetails() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        conexionSQLiteHelper = new ConexionSQLiteHelper(getContext(),"bd movieNigth", null,1);


    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_detail, container, false);

        imageMovie = vista.findViewById(R.id.imageMovieDetail);
        textView = vista.findViewById(R.id.titleMovieDetail);
        textSinopsis = vista.findViewById(R.id.textSinopsis);
        favbutton = vista.findViewById(R.id.favbutton);
        moviesFav = new ArrayList<>();
        movieFavorita = new MovieVo();

        initValues();

        if(movieFavorita.buscarPelicula(titulo,conexionSQLiteHelper)!=0){
            favbutton.setBackgroundResource(R.color.amarillo);
            flag = true;

        }else {
            favbutton.setBackgroundResource(R.color.colorPrimary);

        }

        favbutton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (flag == false){
                    favbutton.setBackgroundResource(R.color.amarillo);
                    moviesFav.add(new MovieVo(titulo, uriCompleta, descripcion));
                    movieFavorita = new MovieVo(titulo, uriCompleta, descripcion);
                    movieDetails.registrarMovieFavorita(titulo,descripcion,uriCompleta,conexionSQLiteHelper);

                } else {
                    favbutton.setBackgroundResource(R.color.colorPrimary);
                    movieFavorita.eliminarPeliFav(titulo, conexionSQLiteHelper);
                }

            }
        });
        return vista;
    }


    public void initValues() {

        Glide.with(context)
                .load(movieDetails.getFoto())
                .into(imageMovie);

        //imageMovie.setImageResource(Integer.parseInt(movieDetails.getFoto()));
        uriCompleta = movieDetails.getFoto();
        titulo = movieDetails.getName();
        textView.setText(movieDetails.getName());

        descripcion = movieDetails.getDescription();
        textSinopsis.setText(movieDetails.getDescription());


    }

    public void putExtra(MovieVo movieVo) {
        this.movieDetails = movieVo;
    }
}
