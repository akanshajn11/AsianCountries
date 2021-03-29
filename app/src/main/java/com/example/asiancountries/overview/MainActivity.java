package com.example.asiancountries.overview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.asiancountries.R;
import com.example.asiancountries.detail.DetailActivity;
import com.example.asiancountries.network.Country;
import com.example.asiancountries.network.Language;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OverviewAdapter.OnItemClickListener {

    public List<Country> countriesList;
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_CAPITAL = "capital";
    public static final String EXTRA_FLAG = "flag";
    public static final String EXTRA_REGION = "region";
    public static final String EXTRA_SUBREGION = "subregion";
    public static final String EXTRA_POPULATION = "population";
    public static final String EXTRA_BORDERS = "borders";
    public static final String EXTRA_LANGUAGES = "languages";

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
                countriesList = countries;
                RecyclerView recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(MainActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
            }
        };

        viewModel.getCountries().observe(this, countryObserver);
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        Country clickedItem = countriesList.get(position);
        ArrayList<Language> languages = (ArrayList<Language>) clickedItem.getLanguages();

        detailIntent.putExtra(EXTRA_NAME, clickedItem.getName());
        detailIntent.putExtra(EXTRA_CAPITAL, clickedItem.getCapital());
        detailIntent.putExtra(EXTRA_FLAG, clickedItem.getFlag());
        detailIntent.putExtra(EXTRA_REGION, clickedItem.getRegion());
        detailIntent.putExtra(EXTRA_SUBREGION, clickedItem.getSubregion());
        detailIntent.putExtra(EXTRA_POPULATION, clickedItem.getPopulation());
        detailIntent.putStringArrayListExtra(EXTRA_BORDERS, (ArrayList<String>) clickedItem.getBorders());
        detailIntent.putExtra(EXTRA_LANGUAGES, languages);
        startActivity(detailIntent);

    }
}