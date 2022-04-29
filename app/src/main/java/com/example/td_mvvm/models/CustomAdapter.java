package com.example.td_mvvm.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.td_mvvm.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Classe dédiée à l'adapteur pour gérer l'affichage des coins dans un recycleur
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private final OnItemClickListener listener;
    private ArrayList<CoinTable> localDataSet; // Dataset fourni est une liste de coin

    public CustomAdapter(ArrayList<CoinTable> a, OnItemClickListener listener) {
        localDataSet = a;
        this.listener = listener;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layoutdurecycler, viewGroup, false);
        return new ViewHolder(view);
    }

    /**
     * Attribution des valeurs au view holder selon la position dans le DataSet
     *
     * @param viewHolder
     * @param position
     */
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.bind(localDataSet.get(position), listener);
    }

    public int getItemCount() {
        return localDataSet.size();
    }

    public interface OnItemClickListener {
        void onItemClick(CoinTable item);

        void onItemLongClick(CoinTable item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView rang;
        private final TextView prix;
        private final TextView nom;
        private final LinearLayout layout;
        private final ImageView monImage;
        private final View maVue;

        /**
         * Constructeur qui attribue aux var locales les TV
         *
         * @param view
         */
        public ViewHolder(View view) {
            super(view);
            rang = view.findViewById(R.id.Rang);
            prix = view.findViewById(R.id.Prix);
            nom = view.findViewById(R.id.Nom);
            layout = view.findViewById(R.id.layoutdefou);
            monImage = view.findViewById(R.id.monIV);
            maVue = view;
        }

        public void bind(final CoinTable item, final OnItemClickListener listener) {
            String nom = item.getName();
            String price = item.getPrice();
            int rang = item.getRank();
            this.rang.setText(String.valueOf(rang)); // Conversion en String obligatoire, int à l'origine
            this.nom.setText(nom);
            this.prix.setText(price.substring(0, 7) + "$");
            String uri = item.getIconUrl();
            uri = uri.substring(0, uri.length() - 3) + "png";
            Picasso.with(maVue.getContext()).load(uri).fit().centerInside().error(R.drawable.ic_launcher_background).into(monImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onItemLongClick(item);
                    return true;
                }
            });
        }

        public LinearLayout getLayout() {
            return this.layout;
        }
    }

}
