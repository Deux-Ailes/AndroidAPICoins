package com.example.td_mvvm.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.td_mvvm.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    private ArrayList<ArrayList<String>> localDataSet;

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
            a.add(0,rang);
            a.add(1,prix);
            a.add(2,nom);
            return a;
        }
    }

    public CustomAdapter(ArrayList<ArrayList<String>> a){
        localDataSet = a;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layoutdurecycler, viewGroup, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder viewHolder, final int position){
        for(int i=0;i<3;i++){
            viewHolder.getTextsView().get(i).setText(localDataSet.get(position).get(i));
        }
    }

    public int getItemCount(){
        return localDataSet.size();
    }


}
