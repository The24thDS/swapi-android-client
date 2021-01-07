package dev.davidsima.swdata.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import dev.davidsima.swdata.R;
import dev.davidsima.swdata.api.StarWarsAPI;
import dev.davidsima.swdata.models.Species;
import dev.davidsima.swdata.models.Vehicle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VehicleActivity extends AppCompatActivity {
    TextView name, model, manufacturer, costInCredits, length, maxAtmosphericSpeed, crew, passengers, cargoCapacity, consumables, vehicleClass;
    int vehicleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);

        name = findViewById(R.id.vehicle_name);
        model = findViewById(R.id.vehicle_model);
        manufacturer = findViewById(R.id.vehicle_manufacturer);
        costInCredits = findViewById(R.id.vehicle_cost_in_credits);
        length = findViewById(R.id.vehicle_length);
        maxAtmosphericSpeed = findViewById(R.id.vehicle_max_atmospheric_speed);
        crew = findViewById(R.id.vehicle_crew);
        passengers = findViewById(R.id.vehicle_passengers);
        cargoCapacity = findViewById(R.id.vehicle_cargo_capacity);
        consumables = findViewById(R.id.vehicle_consumables);
        vehicleClass = findViewById(R.id.vehicle_class);

        vehicleId = getIntent().getIntExtra("VEHICLE_ID", 1);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.dev/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        StarWarsAPI starWarsAPI = retrofit.create(StarWarsAPI.class);

        Call<Vehicle> call = starWarsAPI.getVehicle(vehicleId);

        call.enqueue(new Callback<Vehicle>() {
            @Override
            public void onResponse(Call<Vehicle> call, Response<Vehicle> response) {
                if(!response.isSuccessful()) {
                    System.out.println("CODE: "+response.code());
                    return;
                }

                Vehicle v = response.body();

                name.setText(v.getName());
                model.append(v.getModel());
                manufacturer.append(v.getManufacturer());
                costInCredits.append(v.getCost_in_credits());
                length.append(v.getLength());
                maxAtmosphericSpeed.append(v.getMax_atmosphering_speed());
                crew.append(v.getCrew());
                passengers.append(v.getPassengers());
                cargoCapacity.append(v.getCargo_capacity());
                consumables.append(v.getConsumables());
                vehicleClass.append(v.getVehicle_class());
            }

            @Override
            public void onFailure(Call<Vehicle> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}