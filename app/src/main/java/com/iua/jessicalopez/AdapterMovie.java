package com.iua.jessicalopez;

import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//Alimenta el item_grid_movies
public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.ViewHolderMovie> {

    ArrayList<MovieVo> listMovies;

    public AdapterMovie(ArrayList<MovieVo> listMovies) {
        this.listMovies = listMovies;
    }

    @NonNull
    @Override
    public ViewHolderMovie onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_grid_movies,null,false);
        return new ViewHolderMovie(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMovie holder, int position) {
        //Establece la comunicacion entre el adaptador y el viewHolder

       holder.imageMovie.setImageResource(listMovies.get(position).getImageMovie());
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    public class ViewHolderMovie extends RecyclerView.ViewHolder {

        ImageView imageMovie;


        public ViewHolderMovie(@NonNull View itemView) {
            super(itemView);

            imageMovie = itemView.findViewById(R.id.imageMovie);
        }


    }
}
