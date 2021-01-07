package dev.davidsima.swdata.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import dev.davidsima.swdata.R;
import dev.davidsima.swdata.api.StarWarsAPI;
import dev.davidsima.swdata.models.Film;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilmActivity extends AppCompatActivity {
    TextView title, director, producers, releaseDate, openingCrawl;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);

        title = findViewById(R.id.film_activity_title);
        director = findViewById(R.id.film_activity_director);
        producers = findViewById(R.id.film_activity_producers);
        releaseDate = findViewById(R.id.film_activity_release_date);
        openingCrawl = findViewById(R.id.film_activity_opening);
        image = findViewById(R.id.film_activity_image);

        int filmId = getIntent().getIntExtra("FILM_ID", 1);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.dev/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StarWarsAPI starWarsAPI = retrofit.create(StarWarsAPI.class);

        Call<Film> call = starWarsAPI.getFilm(filmId);

        call.enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Code: "+response.code());
                    return;
                }

                Film film = response.body();
                title.append(""+film.getEpisode_id()+" - "+film.getTitle());

                String uri = "@drawable/episode"+film.getEpisode_id();
                int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                Drawable res = getResources().getDrawable(imageResource);
                image.setImageDrawable(res);

                director.append(film.getDirector());
                producers.append(film.getProducer());
                releaseDate.append(film.getRelease_date());
                openingCrawl.setText(film.getOpening_crawl());
            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}