package com.example.asiancountries.overview;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.asiancountries.network.Country;
import com.example.asiancountries.network.RetrofitApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OverviewViewModel extends ViewModel {

    public static final String BASE_URL = "https://restcountries.eu/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private MutableLiveData<List<Country>> countries;

    public LiveData<List<Country>> getCountries() {

        if (countries == null) {
            countries = new MutableLiveData<List<Country>>();
            loadCountries();
        }
        return countries;
    }

    private void loadCountries() {

        RetrofitApiService apiService = retrofit.create(RetrofitApiService.class);

        Call<List<Country>> call = apiService.getCountries();
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {

                if (response.isSuccessful()) {
                    countries.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {

            }
        });


    }

}
