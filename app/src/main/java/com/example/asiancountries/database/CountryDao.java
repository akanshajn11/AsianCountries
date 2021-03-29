package com.example.asiancountries.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.asiancountries.models.PersistentCountry;

import java.util.List;

@Dao
public interface CountryDao {

    @Query("Select * from PersistentCountry")
    List<PersistentCountry> getAllCountries();

    @Insert
    void saveCountries(List<PersistentCountry> countries);

    @Query("delete from PersistentCountry")
    void delete();

}
