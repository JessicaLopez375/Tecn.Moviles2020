package com.iua.jessicalopez;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class movieDetailActivity extends AppCompatActivity {


    ImageView imageMovie;
    TextView textView;
    TextView textSinopsis;

    private MovieVo movieDetails;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);

        initView();
        initValues();


    }

    public void initView(){
        imageMovie = findViewById(R.id.imageMovieDetail);
        textView = findViewById(R.id.titleMovieDetail);
        textSinopsis = findViewById(R.id.textSinopsis);

    }

    public void initValues(){
        movieDetails = (MovieVo) getIntent().getExtras().getSerializable("movieDetail");

        imageMovie.setImageResource(movieDetails.getImageMovie());

        textView.setText(movieDetails.getNameMovie());

        textSinopsis.setText(movieDetails.getSinopsis());


    }
}
