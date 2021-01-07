package dev.davidsima.swdata.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.davidsima.swdata.R;
import dev.davidsima.swdata.adapters.StarshipsAdapter;
import dev.davidsima.swdata.adapters.VehiclesAdapter;
import dev.davidsima.swdata.api.StarWarsAPI;
import dev.davidsima.swdata.api.StarWarsApiResponse;
import dev.davidsima.swdata.models.Starship;
import dev.davidsima.swdata.models.Vehicle;
import dev.davidsima.swdata.utils.PaginationScrollListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StarshipsActivity extends AppCompatActivity {
    StarWarsAPI starWarsAPI;

    StarshipsAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 3;
    private int currentPage = PAGE_START;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starships);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.dev/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        starWarsAPI = retrofit.create(StarWarsAPI.class);

        recyclerView = findViewById(R.id.starships_recycler);
        progressBar = findViewById(R.id.progress_bar);

        adapter = new StarshipsAdapter(getApplicationContext(), StarshipsActivity.this::onListItemClick);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                loadNextPage();
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        loadFirstPage();
    }

    private void loadFirstPage() {
        Call<StarWarsApiResponse<Starship>> call = starWarsAPI.getStarships(1);

        call.enqueue(new Callback<StarWarsApiResponse<Starship>>() {
            @Override
            public void onResponse(Call<StarWarsApiResponse<Starship>> call, Response<StarWarsApiResponse<Starship>> response) {
                progressBar.setVisibility(View.GONE);
                if (!response.isSuccessful()) {
                    System.out.println("Code: "+response.code());
                    return;
                }

                StarWarsApiResponse<Starship> data = response.body();
                List<Starship> starships = data.getResults();
                int count = data.getCount();
                TOTAL_PAGES = count / starships.size();

                adapter.addAll(starships);

                if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<StarWarsApiResponse<Starship>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                System.out.println(t.getMessage());
            }
        });
    }

    private void loadNextPage() {
        Call<StarWarsApiResponse<Starship>> call = starWarsAPI.getStarships(currentPage);

        call.enqueue(new Callback<StarWarsApiResponse<Starship>>() {
            @Override
            public void onResponse(Call<StarWarsApiResponse<Starship>> call, Response<StarWarsApiResponse<Starship>> response) {
                adapter.removeLoadingFooter();
                isLoading = false;
                progressBar.setVisibility(View.GONE);
                if (!response.isSuccessful()) {
                    System.out.println("Code: "+response.code());
                    return;
                }

                StarWarsApiResponse<Starship> data = response.body();
                List<Starship> starships = data.getResults();

                adapter.addAll(starships);

                if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<StarWarsApiResponse<Starship>> call, Throwable t) {
                adapter.removeLoadingFooter();
                isLoading = false;
                progressBar.setVisibility(View.GONE);
                System.out.println(t.getMessage());
            }
        });
    }

    public void onListItemClick(int vehicleId) {
        Intent intent = new Intent(getApplicationContext(), StarshipActivity.class);
        intent.putExtra("STARSHIP_ID", vehicleId);
        startActivity(intent);
    }
}