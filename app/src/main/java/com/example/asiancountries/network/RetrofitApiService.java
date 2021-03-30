package com.example.asiancountries.network;

import com.example.asiancountries.models.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApiService {

    @GET("rest/v2/region/asia")
    Call<List<Country>> getCountries(

    );

}

