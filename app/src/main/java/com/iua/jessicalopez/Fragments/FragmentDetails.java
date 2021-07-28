package com.iua.jessicalopez.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iua.jessicalopez.Modelo.MovieVo;
import com.iua.jessicalopez.R;

public class FragmentDetails extends Fragment {



    ImageView imageMovie;
    TextView textView;
    TextView textSinopsis;
    MovieVo movieDetails;
    Context context;


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


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_detail, container, false);

        imageMovie = vista.findViewById(R.id.imageMovieDetail);
        textView = vista.findViewById(R.id.titleMovieDetail);
        textSinopsis = vista.findViewById(R.id.textSinopsis);

        initValues();
        return vista;
    }


    public void initValues() {

        Glide.with(context)
                .load(movieDetails.getFoto())
                .into(imageMovie);

        //imageMovie.setImageResource(Integer.parseInt(movieDetails.getFoto()));

        textView.setText(movieDetails.getName());

        textSinopsis.setText(movieDetails.getDescription());


    }

    public void putExtra(MovieVo movieVo) {
        this.movieDetails = movieVo;
    }
}
