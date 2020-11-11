package com.iua.jessicalopez.Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iua.jessicalopez.Fragments.FragmentDetails;
import com.iua.jessicalopez.Modelo.MovieVo;
import com.iua.jessicalopez.R;

import java.util.ArrayList;

public class AdapterAdvance extends RecyclerView.Adapter<AdapterAdvance.ViewHolderAdvance> {

    ArrayList<MovieVo> listAdvances;
    private Context context;

    public AdapterAdvance(ArrayList<MovieVo> listAdvances, Context context) {
        this.listAdvances = listAdvances;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderAdvance onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list,null,false);

        return new ViewHolderAdvance(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAdvance holder, final int position) {

        Glide.with(context)
                .load(listAdvances.get(position).getFoto())
                .into(holder.imageAdvance);
        //holder.imageAdvance.setImageURI(Uri.parse(listAdvances.get(position).getFoto()));
        //holder.imageAdvance.setImageResource(listAdvances.get(position).getFoto());
        holder.nameAdvance.setText(listAdvances.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                FragmentDetails detailFragment = new FragmentDetails(context);
                detailFragment.putExtra(listAdvances.get(position));
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments,detailFragment).addToBackStack(null).commit();



            }
        });

    }

    @Override
    public int getItemCount() {
        return listAdvances.size();
    }

    public class ViewHolderAdvance extends RecyclerView.ViewHolder {
        ImageView imageAdvance;
        TextView nameAdvance;
        public ViewHolderAdvance(@NonNull View itemView) {
            super(itemView);
            imageAdvance = itemView.findViewById(R.id.imageMovieAdvances);
            nameAdvance = itemView.findViewById(R.id.nameMovieAdvances);
        }
    }
}
