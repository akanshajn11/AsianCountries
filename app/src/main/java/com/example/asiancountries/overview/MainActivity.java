package com.example.asiancountries.overview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.asiancountries.R;
import com.example.asiancountries.network.Country;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OverviewViewModelFactory overviewViewModelFactory = new OverviewViewModelFactory();
        OverviewViewModel viewModel = new ViewModelProvider(this, overviewViewModelFactory).get(OverviewViewModel.class);

        viewModel.getCountries();

        final Observer<List<Country>> countryObserver = new Observer<List<Country>>() {
            @Override
            public void onChanged(@Nullable final List<Country> countries) {

                OverviewAdapter adapter = new OverviewAdapter(getApplicationContext(), countries);
                RecyclerView recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
            }
        };

        viewModel.getCountries().observe(this, countryObserver);
    }
}