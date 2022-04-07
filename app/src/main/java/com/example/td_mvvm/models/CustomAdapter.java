package com.example.td_mvvm.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.td_mvvm.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    private ArrayList<Coin> localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView rang;
        private final TextView prix;
        private final TextView nom;
        public ViewHolder(View view) {
            super(view);
            rang = view.findViewById(R.id.Rang);
            prix = view.findViewById(R.id.Prix);
            nom = view.findViewById(R.id.Nom);
        }
        public ArrayList<TextView> getTextsView(){
            ArrayList<TextView> a = new ArrayList<>();
            a.add(rang);
            a.add(prix);
            a.add(nom);
            return a;
        }
    }

    public CustomAdapter(ArrayList<Coin> a){
        localDataSet = a;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layoutdurecycler, viewGroup, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder viewHolder, final int position){
        String nom = localDataSet.get(position).getName();
        String prix = localDataSet.get(position).getPrice();
        int rang = localDataSet.get(position).getRank();
        viewHolder.getTextsView().get(0).setText(String.valueOf(rang));
        viewHolder.getTextsView().get(2).setText(nom);
        viewHolder.getTextsView().get(1).setText(prix);
    }

    public int getItemCount(){
        return localDataSet.size();
    }


}
