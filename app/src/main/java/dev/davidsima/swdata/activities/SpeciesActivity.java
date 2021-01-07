package dev.davidsima.swdata.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import dev.davidsima.swdata.R;
import dev.davidsima.swdata.api.StarWarsAPI;
import dev.davidsima.swdata.models.Planet;
import dev.davidsima.swdata.models.Species;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpeciesActivity extends AppCompatActivity {
    TextView name, classification, designation, averageHeight, skinColors, hairColors, eyeColors, averageLifespan, language;
    int speciesId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_species);

        name = findViewById(R.id.species_name);
        classification = findViewById(R.id.species_classification);
        designation = findViewById(R.id.species_designation);
        averageHeight = findViewById(R.id.species_average_height);
        skinColors = findViewById(R.id.species_skin_colors);
        hairColors = findViewById(R.id.species_hair_colors);
        eyeColors = findViewById(R.id.species_eye_colors);
        averageLifespan = findViewById(R.id.species_average_lifespan);
        language = findViewById(R.id.species_language);

        speciesId = getIntent().getIntExtra("SPECIES_ID", 1);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.dev/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        StarWarsAPI starWarsAPI = retrofit.create(StarWarsAPI.class);

        Call<Species> call = starWarsAPI.getSpecies(speciesId);

        call.enqueue(new Callback<Species>() {
            @Override
            public void onResponse(Call<Species> call, Response<Species> response) {
                if(!response.isSuccessful()) {
                    System.out.println("CODE: "+response.code());
                    return;
                }

                Species s = response.body();

                name.setText(s.getName());
                classification.append(s.getClassification());
                designation.append(s.getDesignation());
                averageHeight.append(s.getAverage_height());
                skinColors.append(s.getSkin_colors());
                hairColors.append(s.getHair_colors());
                eyeColors.append(s.getEye_colors());
                averageLifespan.append(s.getAverage_lifespan());
                language.append(s.getLanguage());
            }

            @Override
            public void onFailure(Call<Species> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}