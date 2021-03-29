package com.example.asiancountries.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asiancountries.R;
import com.example.asiancountries.network.Country;
import com.example.asiancountries.network.Language;
import com.example.asiancountries.utils.AppConstants;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        Country selectedCountry = (Country) intent
                .getSerializableExtra(AppConstants.EXTRA_SELECTED_COUNTRY);

        TextView nameView = findViewById(R.id.name_detail);
        nameView.setText(selectedCountry.getName());

        TextView capitalView = findViewById(R.id.capital);
        capitalView.setText(selectedCountry.getCapital());

        ImageView flagView = findViewById(R.id.flag_detail);
        GlideToVectorYou.init().with(getApplicationContext())
                .load(Uri.parse(selectedCountry.getFlag()), flagView);

        TextView regionView = findViewById(R.id.region);
        regionView.setText(selectedCountry.getRegion());

        TextView subregionView = findViewById(R.id.subregion);
        subregionView.setText(selectedCountry.getSubregion());

        TextView populationView = findViewById(R.id.population);
        populationView.setText(selectedCountry.getPopulation());

        TextView bordersView = findViewById(R.id.borders);
        List<String> borders = selectedCountry.getBorders();
        bordersView.setText(borders.toString()
                .substring(1, borders.toString().length() - 1));

        TextView languagesView = findViewById(R.id.languages);

        List<Language> languages = selectedCountry.getLanguages();
        ArrayList<String> languageNames = new ArrayList<>();

        for (int i = 0; i < languages.size(); i++) {
            languageNames.add(languages.get(i).getName());
        }

        languagesView.setText(TextUtils.join(",", languageNames));

    }
}