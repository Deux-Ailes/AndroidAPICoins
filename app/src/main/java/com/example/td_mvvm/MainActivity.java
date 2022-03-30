package com.example.td_mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.td_mvvm.databinding.ActivityMainBinding;
import com.example.td_mvvm.models.Cmaclasse;
import com.example.td_mvvm.viewModels.IViewModel;
import com.example.td_mvvm.viewModels.maVueModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private IViewModel maVue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        maVue = new maVueModel();
    }

    @Override
    protected void onResume(){
        super.onResume();
        binding.monBouton.setOnClickListener(v->{
            maVue.generateNextValue();
        });
        maVue.getData().observe(this, cmaclasse -> {
            binding.tvTropbien.setText(cmaclasse.getString());
        });
    }

    @Override
    protected void onPause(){
        super.onPause();
    }
}