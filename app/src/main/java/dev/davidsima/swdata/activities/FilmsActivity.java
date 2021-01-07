package dev.davidsima.swdata.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import dev.davidsima.swdata.R;
import dev.davidsima.swdata.api.StarWarsAPI;
import dev.davidsima.swdata.api.StarWarsApiResponse;
import dev.davidsima.swdata.adapters.FilmAdapter;
import dev.davidsima.swdata.models.Film;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilmsActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films);

        recyclerView = findViewById(R.id.films_recycler_view);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.dev/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StarWarsAPI starWarsAPI = retrofit.create(StarWarsAPI.class);

        Call<StarWarsApiResponse<Film>> call = starWarsAPI.getFilms();

        call.enqueue(new Callback<StarWarsApiResponse<Film>>() {
            @Override
            public void onResponse(Call<StarWarsApiResponse<Film>> call, Response<StarWarsApiResponse<Film>> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Code: "+response.code());
                    return;
                }

                StarWarsApiResponse<Film> data = response.body();
                List<Film> films = data.getResults();

                FilmAdapter filmAdapter = new FilmAdapter(getApplicationContext(), films, FilmsActivity.this::onListItemClick);
                recyclerView.setAdapter(filmAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }

            @Override
            public void onFailure(Call<StarWarsApiResponse<Film>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void onListItemClick(int clickedItemIndex) {
        int filmId = clickedItemIndex+1;
        Intent intent = new Intent(getApplicationContext(), FilmActivity.class);
        intent.putExtra("FILM_ID", filmId);
        startActivity(intent);
    }
}