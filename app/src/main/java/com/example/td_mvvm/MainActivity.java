package com.example.td_mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.td_mvvm.databinding.ActivityMainBinding;
import com.example.td_mvvm.models.CustomAdapter;
import com.example.td_mvvm.viewModels.IViewModel;
import com.example.td_mvvm.viewModels.okHttpViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private IViewModel maVue;
    private CustomAdapter monAdaptateurPerso;
    private ArrayList<ArrayList<String>> listeDesInfos;
    private RecyclerView recyclage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        maVue = new okHttpViewModel();
        listeDesInfos = new ArrayList<>();
        configureRecycler();
    }

    @Override
    protected void onResume(){
        super.onResume();
        binding.monBouton.setOnClickListener(v->{
            listeDesInfos.clear();
            maVue.acquisitionDonnes();
        });
        maVue.getData().observe(this, cmaclasse -> {
            //binding.tvTropbien.setText(cmaclasse.getString());
            listeDesInfos.add(cmaclasse.getCoinString());
            this.monAdaptateurPerso.notifyDataSetChanged();
        });
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    private void configureRecycler(){
        this.monAdaptateurPerso = new CustomAdapter(listeDesInfos);
        recyclage=binding.lerecycleur;
        recyclage.setAdapter(this.monAdaptateurPerso);
        recyclage.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }


}