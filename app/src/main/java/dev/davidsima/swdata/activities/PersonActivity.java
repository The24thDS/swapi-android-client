package dev.davidsima.swdata.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import dev.davidsima.swdata.R;
import dev.davidsima.swdata.api.StarWarsAPI;
import dev.davidsima.swdata.models.Person;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PersonActivity extends AppCompatActivity {
    TextView name, height, mass, hairColor, skinColor, eyeColor, birthYear, gender;
    int personId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        name = findViewById(R.id.person_name);
        height = findViewById(R.id.person_height);
        mass = findViewById(R.id.person_mass);
        hairColor = findViewById(R.id.person_hair_color);
        skinColor = findViewById(R.id.person_skin_color);
        eyeColor = findViewById(R.id.person_eye_color);
        birthYear = findViewById(R.id.person_birth_year);
        gender = findViewById(R.id.person_gender);

        personId = getIntent().getIntExtra("PERSON_ID", 1);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.dev/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        StarWarsAPI starWarsAPI = retrofit.create(StarWarsAPI.class);

        Call<Person> call = starWarsAPI.getPerson(personId);

        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                if(!response.isSuccessful()) {
                    System.out.println("CODE: "+response.code());
                    return;
                }

                Person p = response.body();

                name.setText(p.getName());
                height.append(p.getHeight()+" cm");
                mass.append(p.getMass()+" kg");
                hairColor.append(p.getHair_color());
                skinColor.append(p.getSkin_color());
                eyeColor.append(p.getEye_color());
                birthYear.append(p.getBirth_year());
                gender.append(p.getGender());
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}