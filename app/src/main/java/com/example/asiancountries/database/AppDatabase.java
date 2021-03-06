package com.example.asiancountries.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.asiancountries.models.PersistentCountry;

@Database(entities = {PersistentCountry.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CountryDao countryDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "Country_DB")
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
    }

}
