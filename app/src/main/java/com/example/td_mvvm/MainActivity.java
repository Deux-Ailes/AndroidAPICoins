package com.example.td_mvvm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.td_mvvm.databinding.ActivityMainBinding;
import com.example.td_mvvm.models.Coin;
import com.example.td_mvvm.models.CustomAdapter;
import com.example.td_mvvm.models.ForegroundService;
import com.example.td_mvvm.storage.PreferencesHelper;
import com.example.td_mvvm.viewModels.IViewModel;
import com.example.td_mvvm.viewModels.retrofitViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * MENARD Simon   (il ne push jamais car il est timide)
 * CLERICE Elliot  (le fan du C)
 */
public class MainActivity extends AppCompatActivity {

    private static Context APPLICATION_CONTEXT;

    public static Context getContext() {
        return APPLICATION_CONTEXT;
    }

    private ActivityMainBinding binding;
    private IViewModel maVue;
    private CustomAdapter monAdaptateurPerso;
    private ArrayList<Coin> listeDesInfos;
    private RecyclerView recyclage;
    private static final String ALLOWED_CHARACTERS = "0123456789qwertyuiopasdfghjklzxcvbnm";
    public static String Channel1 = "Chameau";
    private boolean coinPresent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        APPLICATION_CONTEXT = this.getApplicationContext();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        maVue = new retrofitViewModel(this.getApplication());
        listeDesInfos = new ArrayList<>();
        configureRecycler();
        createNotificationChannels();
        Intent intent = new Intent(this, ForegroundService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            getContext().startForegroundService(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.btnAPI.setOnClickListener(v -> {
            PreferencesHelper.getInstance().setApiKey("c943e0f237msh7c19578a3d59636p1131e8jsnc67ebeb4fa26");
        });
        binding.btnToast.setOnClickListener(v -> {
            // Faire des tests ici
            Intent serviceIntent = new Intent(this, ForegroundService.class);
            serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android");
            ContextCompat.startForegroundService(this, serviceIntent);
        });
        binding.monBouton.setOnClickListener(v -> {
            listeDesInfos.clear();
            maVue.acquisitionDonnes();
        });
        maVue.getData().observe(this, coins -> {
            listeDesInfos.clear();
            listeDesInfos.addAll(coins);
            Collections.sort(listeDesInfos, new Comparator<Coin>() { // faut bien les trier dans l'ordre, changement de l'ordre dans
                @Override
                public int compare(Coin o1, Coin o2) {
                    return Double.compare(o1.getRank(), o2.getRank());
                }
            });
            if (PreferencesHelper.getInstance().getFavCoinData()!=null) {
                PreferencesHelper.getInstance()
                        .setFavCoinData(
                                listeDesInfos.stream()
                                        .filter(coin->PreferencesHelper.getInstance().getFavCoin().equals(coin.getUuid()))
                                        .findAny()
                                        .orElse(null));
            }
            this.monAdaptateurPerso.notifyDataSetChanged(); // Problème, lors d'un retour, update
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    /**
     * Ouvre une activité qui présente en détail le coin
     *
     * @param item Coin étudié
     */
    private void changeActivityCoined(Coin item) {
        Intent intent = new Intent(this, CoinDetails.class);
        Bundle b = new Bundle();
        b.putSerializable("PIECE", item);
        intent.putExtras(b);
        startActivity(intent);
    }

    /**
     * Prépare le recycler
     * Attribue l'adapteur à la vue et met le onItemClick
     * Bind le recycleur et met le layout dessus
     */
    private void configureRecycler() {
        this.monAdaptateurPerso = new CustomAdapter(listeDesInfos, new CustomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Coin item) {
                changeActivityCoined(item);
            }

            @Override
            public void onItemLongClick(Coin item) {
                PreferencesHelper.getInstance().setFavCoin(item.getUuid());
                PreferencesHelper.getInstance().setFavCoinData(item);
            }
        });
        recyclage = binding.lerecycleur;
        recyclage.setAdapter(this.monAdaptateurPerso);
        recyclage.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    /**
     * Génère une string de taille fixée par l'utilisateur
     *
     * @param sizeOfRandomString Taille de la string voulue
     * @return String générée
     */
    private static String getRandomString(final int sizeOfRandomString) {
        final Random random = new Random();
        final StringBuilder sb = new StringBuilder(sizeOfRandomString);
        for (int i = 0; i < sizeOfRandomString; ++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }

    /**
     * Créer des notifications channels
     */
    private void createNotificationChannels()  {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    Channel1,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("C'est mon canal de com, pas touche >:(");
            NotificationManager manager = this.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
        }
    }

}