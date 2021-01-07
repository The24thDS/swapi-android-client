package dev.davidsima.swdata.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import dev.davidsima.swdata.R;
import dev.davidsima.swdata.api.StarWarsAPI;
import dev.davidsima.swdata.models.Starship;
import dev.davidsima.swdata.models.Vehicle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StarshipActivity extends AppCompatActivity {
    TextView name, model, manufacturer, costInCredits, length, maxAtmosphericSpeed, crew, passengers, cargoCapacity, consumables, hyperdriveRating, mglt, starshipClass;
    int starshipId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starship);

        name = findViewById(R.id.starship_name);
        model = findViewById(R.id.starship_model);
        manufacturer = findViewById(R.id.starship_manufacturer);
        costInCredits = findViewById(R.id.starship_cost_in_credits);
        length = findViewById(R.id.starship_length);
        maxAtmosphericSpeed = findViewById(R.id.starship_max_atmospheric_speed);
        crew = findViewById(R.id.starship_crew);
        passengers = findViewById(R.id.starship_passengers);
        cargoCapacity = findViewById(R.id.starship_cargo_capacity);
        consumables = findViewById(R.id.starship_consumables);
        hyperdriveRating = findViewById(R.id.starship_hyperdrive_rating);
        mglt = findViewById(R.id.starship_mglt);
        starshipClass = findViewById(R.id.starship_class);

        starshipId = getIntent().getIntExtra("STARSHIP_ID", 1);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.dev/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        StarWarsAPI starWarsAPI = retrofit.create(StarWarsAPI.class);

        Call<Starship> call = starWarsAPI.getStarship(starshipId);

        call.enqueue(new Callback<Starship>() {
            @Override
            public void onResponse(Call<Starship> call, Response<Starship> response) {
                if(!response.isSuccessful()) {
                    System.out.println("CODE: "+response.code());
                    return;
                }

                Starship s = response.body();

                name.setText(s.getName());
                model.append(s.getModel());
                manufacturer.append(s.getManufacturer());
                costInCredits.append(s.getCost_in_credits());
                length.append(s.getLength());
                maxAtmosphericSpeed.append(s.getMax_atmosphering_speed());
                crew.append(s.getCrew());
                passengers.append(s.getPassengers());
                cargoCapacity.append(s.getCargo_capacity());
                consumables.append(s.getConsumables());
                hyperdriveRating.append(s.getHyperdrive_rating());
                mglt.append(s.getMGLT());
                starshipClass.append(s.getStarship_class());
            }

            @Override
            public void onFailure(Call<Starship> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}