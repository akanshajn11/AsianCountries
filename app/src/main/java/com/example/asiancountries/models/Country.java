package com.example.asiancountries.models;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Country implements AsiaCountry {

    class Language implements Serializable {
        String name;

        public String getName() {
            return name;
        }
    }

    String name;
    String capital;
    String flag;
    String region;
    String subregion;
    String population;
    List<String> borders;
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

    public String getBorders() {
        return TextUtils.join(",", borders);
    }

    public String getLanguages() {
        ArrayList<String> languageNames = new ArrayList<>();

        for (int i = 0; i < languages.size(); i++) {
            languageNames.add(languages.get(i).getName());
        }

        return TextUtils.join(",", languageNames);
    }
}






