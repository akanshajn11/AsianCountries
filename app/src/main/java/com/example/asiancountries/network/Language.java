package com.example.asiancountries.network;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Language implements Serializable {

    @SerializedName("name")
    String name;

    public String getName() {
        return name;
    }


}
