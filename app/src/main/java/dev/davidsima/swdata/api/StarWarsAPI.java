package dev.davidsima.swdata.api;

import dev.davidsima.swdata.models.Film;
import dev.davidsima.swdata.models.Person;
import dev.davidsima.swdata.models.Planet;
import dev.davidsima.swdata.models.Species;
import dev.davidsima.swdata.models.Starship;
import dev.davidsima.swdata.models.Vehicle;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StarWarsAPI {
    @GET("films")
    Call<StarWarsApiResponse<Film>> getFilms();

    @GET("films/{id}")
    Call<Film> getFilm(@Path("id") int filmId);

    @GET("people")
    Call<StarWarsApiResponse<Person>> getPeople(@Query("page") int page);

    @GET("people/{id}")
    Call<Person> getPerson(@Path("id") int personId);

    @GET("planets")
    Call<StarWarsApiResponse<Planet>> getPlanets(@Query("page") int page);

    @GET("planets/{id}")
    Call<Planet> getPlanet(@Path("id") int planetId);

    @GET("species")
    Call<StarWarsApiResponse<Species>> getSpeciesList(@Query("page") int page);

    @GET("species/{id}")
    Call<Species> getSpecies(@Path("id") int speciesId);

    @GET("vehicles")
    Call<StarWarsApiResponse<Vehicle>> getVehicles(@Query("page") int page);

    @GET("vehicles/{id}")
    Call<Vehicle> getVehicle(@Path("id") int vehicleId);

    @GET("starships")
    Call<StarWarsApiResponse<Starship>> getStarships(@Query("page") int page);

    @GET("starships/{id}")
    Call<Starship> getStarship(@Path("id") int starshipId);
}
