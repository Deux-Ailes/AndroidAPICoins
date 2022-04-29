package com.example.td_mvvm.activities;

import android.app.ActivityManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.td_mvvm.databinding.ActivityCoinDetailsBinding;
import com.example.td_mvvm.databinding.ActivityMainBinding;
import com.example.td_mvvm.models.CoinTable;
import com.example.td_mvvm.models.CustomAdapter;
import com.example.td_mvvm.models.ForegroundService;
import com.example.td_mvvm.storage.PreferencesHelper;
import com.example.td_mvvm.viewModels.retrofitViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * MENARD Simon   (il ne push jamais car il est timide)
 * CLERICE Elliot  (le fan du C)
 */
public class MainActivity extends AppCompatActivity {

    public static String Channel1 = "Chameau";
    private static Context APPLICATION_CONTEXT;
    private ActivityMainBinding binding;
    private retrofitViewModel maVue;
    private CustomAdapter monAdaptateurPerso;
    private ArrayList<CoinTable> listeDesInfos;
    private RecyclerView recyclage;

    public static Context getContext() {
        return APPLICATION_CONTEXT;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        APPLICATION_CONTEXT = this.getApplicationContext();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Paramétrage du recycler
        maVue = new retrofitViewModel(this.getApplication());
        listeDesInfos = new ArrayList<>();
        configureRecycler();


       // configureRefresher();

        // Paramétrage de la notification
        createNotificationChannels();
        Intent intent = new Intent(this, ForegroundService.class);
        Boolean test = checkIfServiceActivated(ForegroundService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && savedInstanceState == null) {
            getContext().startForegroundService(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Parce que la vie est forcément plus belle avec un refresher
        binding.swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                maVue.acquisitionDonnees();
            }
        });

        maVue.getData().observe(this, coinTables -> {
            listeDesInfos.clear();
            listeDesInfos.addAll(coinTables);
            Collections.sort(listeDesInfos, new Comparator<CoinTable>() { // faut bien les trier dans l'ordre, changement de l'ordre dans
                @Override
                public int compare(CoinTable o1, CoinTable o2) {
                    return Double.compare(o1.getRank(), o2.getRank());
                }
            });
            if (PreferencesHelper.getInstance().getFavCoinData() != null) {
                PreferencesHelper.getInstance()
                        .setFavCoinData(
                                listeDesInfos.stream()
                                        .filter(coin -> PreferencesHelper.getInstance().getFavCoin().equals(coin.getUuid()))
                                        .findAny()
                                        .orElse(null));
            }
            this.monAdaptateurPerso.notifyDataSetChanged();
            binding.swipeRefreshLayout.setRefreshing(false);
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
    private void changeActivityCoined(CoinTable item) {
        Intent intent = new Intent(this, CoinDetails.class);
        Bundle b = new Bundle();
        b.putString("PIECE", item.getUuid());
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
            public void onItemClick(CoinTable item) {
                changeActivityCoined(item);
            }

            @Override
            public void onItemLongClick(CoinTable item) {
                PreferencesHelper.getInstance().setFavCoin(item.getUuid());
                PreferencesHelper.getInstance().setFavCoinData(item);
            }
        });

        recyclage = binding.lerecycleur;
        recyclage.setAdapter(this.monAdaptateurPerso);
        recyclage.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void configureRefresher(){
        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            binding.swipeRefreshLayout.setRefreshing(true);
            listeDesInfos.clear();
            maVue.acquisitionDonnees();
        });
    }

    /**
     * Créer le notification channel
     */
    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mainChannel = new NotificationChannel(
                    Channel1,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            mainChannel.setDescription("C'est mon canal de com, pas touche >:(");
            NotificationManager manager = this.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(mainChannel);
        }
    }

    private boolean checkIfServiceActivated(Class<?> nomDeLaClasse){
        ActivityManager a = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for(ActivityManager.RunningServiceInfo service : a.getRunningServices(Integer.MAX_VALUE)){
            if(service.getClass().equals(nomDeLaClasse)) return true;
        }
        return false;
    }
}