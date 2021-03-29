package com.example.asiancountries.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asiancountries.R;
import com.example.asiancountries.network.Language;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

import java.util.ArrayList;
import java.util.List;

import static com.example.asiancountries.overview.MainActivity.EXTRA_BORDERS;
import static com.example.asiancountries.overview.MainActivity.EXTRA_CAPITAL;
import static com.example.asiancountries.overview.MainActivity.EXTRA_FLAG;
import static com.example.asiancountries.overview.MainActivity.EXTRA_LANGUAGES;
import static com.example.asiancountries.overview.MainActivity.EXTRA_NAME;
import static com.example.asiancountries.overview.MainActivity.EXTRA_POPULATION;
import static com.example.asiancountries.overview.MainActivity.EXTRA_REGION;
import static com.example.asiancountries.overview.MainActivity.EXTRA_SUBREGION;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String name = intent.getStringExtra(EXTRA_NAME);
        String capital = intent.getStringExtra(EXTRA_CAPITAL);
        String flag = intent.getStringExtra(EXTRA_FLAG);
        String region = intent.getStringExtra(EXTRA_REGION);
        String subregion = intent.getStringExtra(EXTRA_SUBREGION);
        String population = intent.getStringExtra(EXTRA_POPULATION);
        ArrayList<String> borders = getIntent().getStringArrayListExtra(EXTRA_BORDERS);
        List<Language> languages = (ArrayList<Language>) intent.getSerializableExtra(EXTRA_LANGUAGES);

        TextView name_view = findViewById(R.id.name_detail);
        name_view.setText(name);

        TextView capital_view = findViewById(R.id.capital);
        capital_view.setText(capital);

        ImageView flag_view = findViewById(R.id.flag_detail);
        GlideToVectorYou.init().with(getApplicationContext()).load(Uri.parse(flag), flag_view);

        TextView region_view = findViewById(R.id.region);
        region_view.setText(region);

        TextView subregion_view = findViewById(R.id.subregion);
        subregion_view.setText(subregion);

        TextView population_view = findViewById(R.id.population);
        population_view.setText(population);

        TextView borders_view = findViewById(R.id.borders);
        borders_view.setText(borders.toString().substring(1, borders.toString().length() - 1));

        TextView languages_view = findViewById(R.id.languages);
        ArrayList<String> languageNames = new ArrayList<String>();
        for (int i = 0; i < languages.size(); i++) {
            languageNames.add(languages.get(i).getName());
        }
        languages_view.setText(languageNames.toString().substring(1, languageNames.toString().length() - 1));

    }
}