package com.example.td_mvvm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.td_mvvm.databinding.ActivityCoinDetailsBinding;
import com.example.td_mvvm.models.Coin;
import com.squareup.picasso.Picasso;

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
        if (intent.getExtras() != null) {
            Bundle b = intent.getExtras();
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
        binding.button.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
 //           Bundle bundle = new Bundle();
            //bundle.putFloat("TESTAS",122); // TODO : Faire quelque chose de plus propre
 //           intent.putExtras(bundle);
            startActivity(intent);
        });
        // Image
        ImageView iv = binding.imageView;
        String uri = this.maCoin.getIconUrl();
        uri = uri.substring(0, uri.length() - 3) + "png";
        Picasso.with(this).load(uri).fit().centerInside().error(R.drawable.ic_launcher_background).into(iv);


        /*
        // Préparation de la chart
        List<String> data = this.maCoin.getSparkline(); // Obtention du tableau
        LineChart graphique = findViewById(R.id.chart);
        List<Entry> entree = new ArrayList<Entry>();
        int position = 0;
        for (String maValeur : data) {               // Attribution des valeurs dans la LineData prochaine
            String valeur = maValeur;
            float value = Float.valueOf(valeur);
            entree.add(new Entry(position, value)); // Conversion des Strings en int
            position++;
        }
        LineDataSet lds = new LineDataSet(entree, "Evolution en $ du prix");

        // Mise en forme
        lds.setColor(getColor(R.color.jsp));
        LineData ld = new LineData(lds);
        graphique.setData(ld);
        graphique.setScaleYEnabled(true);
        graphique.setScaleEnabled(true);
        graphique.setPinchZoom(true);
        graphique.invalidate();
         */
    }


}