package com.example.td_mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.td_mvvm.databinding.ActivityMainBinding;
import com.example.td_mvvm.models.Coin;
import com.example.td_mvvm.models.CustomAdapter;
import com.example.td_mvvm.storage.PreferencesHelper;
import com.example.td_mvvm.viewModels.IViewModel;
import com.example.td_mvvm.viewModels.RetrofitViewModel;
import com.example.td_mvvm.viewModels.okHttpViewModel;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 *  MEINARD Simon   (il ne push jamais car il est timide)
 *  CLERICE Elliot  (le fan du C)
 *
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
    private static final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        APPLICATION_CONTEXT = this.getApplicationContext();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        maVue = new RetrofitViewModel();
        listeDesInfos = new ArrayList<>();
        configureRecycler();
        Intent intent = getIntent();
        if(intent.getExtras()!=null) maVue.acquisitionDonnes();
    }

    @Override
    protected void onResume(){
        super.onResume();
        binding.btnId.setOnClickListener(v->{
            PreferencesHelper.getInstance().setApiKey(getRandomString(64));
        });

        binding.btnAPI.setOnClickListener(v->{
            PreferencesHelper.getInstance().setApiKey("c943e0f237msh7c19578a3d59636p1131e8jsnc67ebeb4fa26");
        });

        binding.btnToast.setOnClickListener(v->{
            Toast.makeText(this,"C'est ta clé : " + PreferencesHelper.getInstance().getApiKey() , Toast.LENGTH_LONG).show();
        });
        binding.monBouton.setOnClickListener(v->{
            listeDesInfos.clear();
            maVue.acquisitionDonnes();
        });

        maVue.getData().observe(this, coins -> {
            listeDesInfos.addAll(coins);
            this.monAdaptateurPerso.notifyDataSetChanged();
        });
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    private void changementActivite(Coin item){
        Intent intent = new Intent(this,CoinDetails.class);
        Bundle b = new Bundle();
        b.putSerializable("PIECE",item);
        intent.putExtras(b);
        startActivity(intent);
    }

    private void configureRecycler(){
        this.monAdaptateurPerso = new CustomAdapter(listeDesInfos, new CustomAdapter.OnItemClickListener(){
            @Override public void onItemClick(Coin item) {
                PreferencesHelper.getInstance().setFavCoin(item.getUuid());
                System.out.println(PreferencesHelper.getInstance().getFavCoin());
                changementActivite(item);

            }
        });
        recyclage=binding.lerecycleur;
        recyclage.setAdapter(this.monAdaptateurPerso);
        recyclage.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }
    private static String getRandomString(final int sizeOfRandomString)
    {
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int i=0;i<sizeOfRandomString;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }

}