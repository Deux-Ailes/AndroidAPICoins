package com.example.td_mvvm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.td_mvvm.R;
import com.example.td_mvvm.databinding.ActivityCoinDetailsBinding;
import com.example.td_mvvm.models.CoinFromRequest;
import com.example.td_mvvm.viewModels.DataCoinInformation;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CoinDetails extends AppCompatActivity {
    private CoinFromRequest maCoin;
    private ActivityCoinDetailsBinding binding;
    private DataCoinInformation maVue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCoinDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        binding.layoutData.setVisibility(View.INVISIBLE);
        maVue = new DataCoinInformation(this.getApplication());
        maVue.acquisitionDonnees((String) b.get("PIECE"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Acquisition de la coin
        maVue.getCoin().observe(this, coin -> {
            this.maCoin = coin;
            updateFields();
        });
    }

    /**
     * Mets à jour tous les champs en fonction de this.maCoin
     * Update la chart
     */
    private void updateFields() {
        if (binding.layoutData.getVisibility() == View.INVISIBLE) {
            binding.layoutData.setVisibility(View.VISIBLE);
            binding.layoutLoading.setVisibility(View.GONE);
        }
        binding.tvSymbol.setText(this.maCoin.getSymbol());
        binding.tvName.setText(this.maCoin.getName());
        binding.tvPrice.setText(String.format("%s $", this.maCoin.getPrice()));
        binding.tvRank.setText(String.format("Rang : %s", this.maCoin.getRank()));
        binding.button.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        // Image
        ImageView iv = binding.imageView;
        String uri = this.maCoin.getIconUrl();
        uri = uri.substring(0, uri.length() - 3) + "png";
        Picasso.with(this).load(uri).fit().centerInside().error(R.drawable.ic_launcher_background).into(iv);

        // Préparation de la chart
        List<String> data = this.maCoin.getSparkline(); // Obtention du tableau
        LineChart graphique = findViewById(R.id.chart);
        List<Entry> entree = new ArrayList<>();
        int position = 0;
        for (String sparkLineValue : data) {               // Attribution des valeurs dans la LineData prochaine
            float valueFloat = Float.parseFloat(sparkLineValue);
            entree.add(new Entry(position, valueFloat)); // Ajout dans la liste de la nouvelle entrée comportant la position du point et sa valeur float
            position++;
        }
        LineDataSet lds = new LineDataSet(entree, "Evolution en $ du prix");

        // Mise en forme de la chart
        lds.setColor(getColor(R.color.jsp));
        LineData ld = new LineData(lds);
        graphique.setData(ld);
        graphique.setScaleYEnabled(true);
        graphique.setScaleEnabled(true);
        graphique.setPinchZoom(true);
        graphique.invalidate();
    }


}