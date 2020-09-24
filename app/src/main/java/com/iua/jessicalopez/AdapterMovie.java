package com.iua.jessicalopez;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
    public void onBindViewHolder(@NonNull final ViewHolderMovie holder, final int position) {
        //Establece la comunicacion entre el adaptador y el viewHolder

       holder.imageMovie.setImageResource(listMovies.get(position).getImageMovie());

       //Esta View hace referencia a cada item
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               AppCompatActivity activity = (AppCompatActivity) view.getContext();
               FragmentDetails detailFragment = new FragmentDetails();
               detailFragment.putExtra(listMovies.get(position));
               activity.getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments,detailFragment).addToBackStack(null).commit();

               //EN CASO DE ENVIAR LOS DATOS A UNA ACTIVITY
               //Intent intent = new Intent(holder.itemView.getContext(),movieDetailActivity.class);
               //intent.putExtra("movieDetail", listMovies.get(position));
               //holder.itemView.getContext().startActivity(intent);


           }
       });

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
