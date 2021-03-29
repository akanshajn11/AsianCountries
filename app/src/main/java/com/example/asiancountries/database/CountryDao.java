package com.example.asiancountries.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CountryDao {

    @Query("Select * from PersistentCountry")
    List<PersistentCountry> getAllCountries();

    @Insert
    void insertCountry(PersistentCountry country);

    @Query("delete from PersistentCountry")
    void delete();

}
