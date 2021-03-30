package com.example.asiancountries.overview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asiancountries.R;
import com.example.asiancountries.detail.DetailActivity;
import com.example.asiancountries.models.AsiaCountry;
import com.example.asiancountries.utils.AppConstants;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OverviewAdapter.OnItemClickListener {

    public List<AsiaCountry> countriesList;
    public OverviewViewModel viewModel;
    public RecyclerView recyclerView;
    public Button refreshButton;
    public TextView internetError;
    public MenuItem menuItem;
    public SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new OverviewViewModel(getApplication());
        recyclerView = findViewById(R.id.recyclerView);
        refreshButton = findViewById(R.id.refresh_button);
        internetError = findViewById(R.id.internet_error);
        swipeRefreshLayout = findViewById(R.id.swipeContainer);
        swipeRefreshLayout.setRefreshing(true);
        fetchData();

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchData();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (countriesList.size() > 0) {
                    fetchData();
                    Toast.makeText(getApplicationContext(), "Countries refreshed", Toast.LENGTH_SHORT).show();
                } else
                    swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void fetchData() {
        viewModel.getCountries();
        swipeRefreshLayout.setRefreshing(false);

        swipeRefreshLayout.setVisibility(View.VISIBLE);
        refreshButton.setVisibility(View.INVISIBLE);

        final Observer<List<AsiaCountry>> countryObserver = new Observer<List<AsiaCountry>>() {
            @Override
            public void onChanged(@Nullable final List<AsiaCountry> countries) {
                countriesList = countries;
                if (countriesList.size() == 0) {
                    swipeRefreshLayout.setVisibility(View.INVISIBLE);
                    internetError.setVisibility(View.VISIBLE);
                    refreshButton.setVisibility(View.VISIBLE);
                    if (menuItem != null)
                        menuItem.setVisible(false);
                } else {
                    swipeRefreshLayout.setVisibility(View.VISIBLE);
                    internetError.setVisibility(View.INVISIBLE);
                    if (menuItem != null)
                        menuItem.setVisible(true);
                }

                OverviewAdapter adapter = new OverviewAdapter(getApplicationContext(), countriesList);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(MainActivity.this);
            }
        };

        viewModel.getCountries().observe(this, countryObserver);
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        AsiaCountry clickedItem = countriesList.get(position);
        detailIntent.putExtra(AppConstants.EXTRA_SELECTED_COUNTRY, clickedItem);
        startActivity(detailIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        menuItem = menu.findItem(R.id.delete);
        if (internetError.getVisibility() == View.VISIBLE)
            menuItem.setVisible(false);
        else
            menuItem.setVisible(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.delete:
                viewModel.deleteAllCountriesFromDB();
                Toast.makeText(getApplicationContext(), "Cache cleared", Toast.LENGTH_SHORT).show();

                swipeRefreshLayout.setVisibility(View.INVISIBLE);
                internetError.setVisibility(View.INVISIBLE);
                refreshButton.setVisibility(View.VISIBLE);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}