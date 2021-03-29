package com.example.asiancountries.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PersistentCountry implements AsiaCountry {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name="name")
    public String name;

    @ColumnInfo(name="capital")
    public String capital;

    @ColumnInfo(name="flag")
    public String flag;

    @ColumnInfo(name="region")
    public String region;

    @ColumnInfo(name="subregion")
    public String subregion;

    @ColumnInfo(name="population")
    public String population;

    @ColumnInfo(name="borders")
    public String borders;

    @ColumnInfo(name="languages")
    public String languages;

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
        return borders;
    }

    public String getLanguages() {
        return languages;
    }
}
