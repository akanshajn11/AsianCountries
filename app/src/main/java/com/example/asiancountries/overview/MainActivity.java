package com.example.asiancountries.overview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.asiancountries.R;
import com.example.asiancountries.detail.DetailActivity;
import com.example.asiancountries.models.AsiaCountry;
import com.example.asiancountries.utils.AppConstants;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OverviewAdapter.OnItemClickListener {

    public List<AsiaCountry> countriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OverviewViewModel viewModel = new OverviewViewModel(getApplication());

        viewModel.getCountries();

        final Observer<List<AsiaCountry>> countryObserver = new Observer<List<AsiaCountry>>() {
            @Override
            public void onChanged(@Nullable final List<AsiaCountry> countries) {

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
        AsiaCountry clickedItem = countriesList.get(position);

        detailIntent.putExtra(AppConstants.EXTRA_SELECTED_COUNTRY,clickedItem);
        startActivity(detailIntent);
    }
}