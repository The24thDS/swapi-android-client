package dev.davidsima.swdata.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import dev.davidsima.swdata.R;
import dev.davidsima.swdata.api.StarWarsAPI;
import dev.davidsima.swdata.models.Person;
import dev.davidsima.swdata.models.Planet;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlanetActivity extends AppCompatActivity {
    TextView name, rotationPeriod, orbitalPeriod, diameter, climate, gravity, terrain, surface_water, population;
    int planetId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet);

        name = findViewById(R.id.planet_name);
        rotationPeriod = findViewById(R.id.planet_rotation_period);
        orbitalPeriod = findViewById(R.id.planet_orbital_period);
        diameter = findViewById(R.id.planet_diameter);
        climate = findViewById(R.id.planet_climate);
        gravity = findViewById(R.id.planet_gravity);
        terrain = findViewById(R.id.planet_terrain);
        surface_water = findViewById(R.id.planet_surface_water);
        population = findViewById(R.id.planet_population);

        planetId = getIntent().getIntExtra("PLANET_ID", 1);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.dev/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        StarWarsAPI starWarsAPI = retrofit.create(StarWarsAPI.class);

        Call<Planet> call = starWarsAPI.getPlanet(planetId);

        call.enqueue(new Callback<Planet>() {
            @Override
            public void onResponse(Call<Planet> call, Response<Planet> response) {
                if(!response.isSuccessful()) {
                    System.out.println("CODE: "+response.code());
                    return;
                }

                Planet p = response.body();

                name.setText(p.getName());
                rotationPeriod.append(p.getRotation_period());
                orbitalPeriod.append(p.getOrbital_period());
                diameter.append(p.getDiameter());
                climate.append(p.getClimate());
                gravity.append(p.getGravity());
                terrain.append(p.getTerrain());
                surface_water.append(p.getSurface_water());
                population.append(p.getPopulation());
            }

            @Override
            public void onFailure(Call<Planet> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}