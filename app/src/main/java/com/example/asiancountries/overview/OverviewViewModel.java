package com.example.asiancountries.overview;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.asiancountries.database.AppDatabase;
import com.example.asiancountries.models.PersistentCountry;
import com.example.asiancountries.models.AsiaCountry;
import com.example.asiancountries.models.Country;
import com.example.asiancountries.network.RetrofitApiService;
import com.example.asiancountries.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OverviewViewModel extends AndroidViewModel {

    private Context context;
    private AppDatabase appDB;

    public OverviewViewModel(Application application) {
        super(application);
        this.context = application.getApplicationContext();
        this.appDB = AppDatabase.getDbInstance(application.getApplicationContext());
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private MutableLiveData<List<AsiaCountry>> countries;

    public LiveData<List<AsiaCountry>> getCountries() {

        if (countries == null) {
            countries = new MutableLiveData<>();

            if (this.isNetworkConnected()) {
                this.loadCountries();
            } else {
                this.getAllCountriesFromDB();
            }
        }
        return countries;
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private void loadCountries() {
        RetrofitApiService apiService = retrofit.create(RetrofitApiService.class);
        Call<List<Country>> call = apiService.getCountries();
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {

                if (response.isSuccessful()) {
                    deleteSavedCountries();
                    List<Country> newCountries = response.body();

                    ArrayList<AsiaCountry> asianCountries = new ArrayList<>();

                    for (AsiaCountry country : newCountries) {
                        asianCountries.add(country);
                    }

                    countries.setValue(asianCountries);

                    saveCountries(newCountries);
                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) { }
        });
    }

    private void deleteSavedCountries() {
        this.appDB.countryDao().delete();
    }

    private void saveCountries(List<Country> countries) {
        ArrayList<PersistentCountry> persistentCountries = new ArrayList<>();

        for(Country country : countries) {
            PersistentCountry persistentCountry = new PersistentCountry();
            persistentCountry.name = country.getName();
            persistentCountry.capital = country.getCapital();
            persistentCountry.flag = country.getFlag();
            persistentCountry.region = country.getRegion();
            persistentCountry.subregion = country.getSubregion();
            persistentCountry.population = country.getPopulation();
            persistentCountry.borders = country.getBorders();
            persistentCountry.languages = country.getLanguages();

            persistentCountries.add(persistentCountry);
        }

        this.appDB.countryDao().saveCountries(persistentCountries);
    }

    private void getAllCountriesFromDB() {
        ArrayList<AsiaCountry> asianCountries = new ArrayList<>();

        for (AsiaCountry country : appDB.countryDao().getAllCountries()) {
            asianCountries.add(country);
        }

        countries.setValue(asianCountries);
    }
}
