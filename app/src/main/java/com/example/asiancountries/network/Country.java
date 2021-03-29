package com.example.asiancountries.network;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Country implements Serializable {

    @SerializedName("name")
    String name;

    @SerializedName("capital")
    String capital;

    @SerializedName("flag")
    String flag;

    @SerializedName("region")
    String region;

    @SerializedName("subregion")
    String subregion;

    @SerializedName("population")
    String population;

    @SerializedName("borders")
    List<String> borders;

    @SerializedName("languages")
    List<Language> languages;

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getFlag() {
        return flag;
    }

    public String getRegion() {
        return region;
    }

    public String getSubregion() {
        return subregion;
    }

    public String getPopulation() {
        return population;
    }

    public List<String> getBorders() {
        return borders;
    }

    public List<Language> getLanguages() {
        return languages;
    }
}






