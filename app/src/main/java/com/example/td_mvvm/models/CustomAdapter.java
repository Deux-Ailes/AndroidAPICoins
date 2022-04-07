package com.example.td_mvvm.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.td_mvvm.R;
import com.example.td_mvvm.storage.PreferencesHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    private ArrayList<Coin> localDataSet; // Dataset fourni est une liste de coin

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView rang;
        private final TextView prix;
        private final TextView nom;
        private final LinearLayout layout;

        /**
         * Constructeur qui attribue aux var locales les TV
         * @param view
         */
        public ViewHolder(View view) {
            super(view);
            rang = view.findViewById(R.id.Rang);
            prix = view.findViewById(R.id.Prix);
            nom = view.findViewById(R.id.Nom);
            layout = view.findViewById(R.id.layoutdefou);

        }

        /**
         * Fonction permettant d'obtenir les TV du VH
         * @return Liste des text views du ViewHolder
         */
        public ArrayList<TextView> getTextsView(){
            ArrayList<TextView> a = new ArrayList<>();
            a.add(rang);
            a.add(prix);
            a.add(nom);
            return a;
        }

        public LinearLayout getLayout(){
            return this.layout;
        }
    }

    public CustomAdapter(ArrayList<Coin> a){
        localDataSet = a;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layoutdurecycler, viewGroup, false);
        return new ViewHolder(view);
    }

    /**
     * Attribution des valeurs au view holder selon la position dans le DataSet
     * @param viewHolder
     * @param position
     */
    public void onBindViewHolder(ViewHolder viewHolder, final int position){
        String nom = localDataSet.get(position).getName();
        String price = localDataSet.get(position).getPrice();
        int rang = localDataSet.get(position).getRank();
        viewHolder.getTextsView().get(0).setText(String.valueOf(rang)); // Conversion en String obligatoire, int à l'origine
        viewHolder.getTextsView().get(2).setText(nom);
        viewHolder.getTextsView().get(1).setText(price);
        viewHolder.getLayout().setOnClickListener(v->{PreferencesHelper.getInstance().setFavCoin(localDataSet.get(position).getUuid());});
    }

    public int getItemCount(){
        return localDataSet.size();
    }
    
}
