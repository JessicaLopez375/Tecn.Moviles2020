package com.iua.jessicalopez;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterAdvance extends RecyclerView.Adapter<AdapterAdvance.ViewHolderAdvance> {

    ArrayList<MovieVo> listAdvances;

    public AdapterAdvance(ArrayList<MovieVo> listAdvances) {
        this.listAdvances = listAdvances;
    }

    @NonNull
    @Override
    public ViewHolderAdvance onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list,null,false);

        return new ViewHolderAdvance(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAdvance holder, int position) {
        holder.imageAdvance.setImageResource(listAdvances.get(position).getImageMovie());
        holder.nameAdvance.setText(listAdvances.get(position).getNameMovie());

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
