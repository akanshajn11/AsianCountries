package com.example.asiancountries.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PersistentCountry {

    @PrimaryKey(autoGenerate = true)
    public int id;

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


}
