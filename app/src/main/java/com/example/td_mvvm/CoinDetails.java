package com.example.td_mvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.example.td_mvvm.databinding.ActivityCoinDetailsBinding;

import com.example.td_mvvm.models.Coin;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CoinDetails extends AppCompatActivity {
    private Coin maCoin;
    private ActivityCoinDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_coin_details);
        binding = ActivityCoinDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        Coin c;
        if(intent.getExtras()!=null){
            Bundle b =intent.getExtras();
            this.maCoin = (Coin) b.get("PIECE");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Attribution des variables aux textes
        binding.tvSymbol.setText(this.maCoin.getSymbol());
        binding.tvName.setText(this.maCoin.getName());
        binding.tvPrix.setText(this.maCoin.getPrice() + " $");
        binding.tvRank.setText("Rang : " + String.valueOf(this.maCoin.getRank()));
        binding.button.setOnClickListener(v->{
            Intent intent = new Intent(this, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putFloat("TESTAS",122); // TODO : Faire quelque chose de plus propre
            intent.putExtras(bundle);
            startActivity(intent);
        });
        // Image
        ImageView iv = binding.imageView;
        String uri = this.maCoin.getIcon();
        uri =uri.substring(0,uri.length()-3) + "png";
        Picasso.with(this).load(uri).fit().centerInside().error(R.drawable.ic_launcher_background).into(iv);

        // Préparation de la chart
        List<String> data = this.maCoin.getSparkline();
        LineChart graphique = findViewById(R.id.chart);
        List<Entry> entree = new ArrayList<Entry>();
        int position = 0;
        for(String maValeur : data){               // Attribution des valeurs dans la LineData prochaine
            String valeur = maValeur;
            float value = Float.valueOf(valeur);
            entree.add(new Entry(position,value)); // Conversion des Strings en int
            position++;
        }
        LineDataSet lds = new LineDataSet(entree,"Evolution en $ du prix");

        lds.setColor(getColor(R.color.jsp));
        LineData ld = new LineData(lds);
        graphique.setData(ld);
        graphique.setScaleYEnabled(true);
        graphique.setScaleEnabled(true);
        graphique.setPinchZoom(true);
        graphique.invalidate();
    }


}