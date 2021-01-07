package dev.davidsima.swdata.models;

public class Film {
    private String title;
    private int episode_id;
    private String opening_crawl;
    private String director;
    private String producer;
    private String release_date;
    private String[] characters;
    private String[] planets;
    private String[] starships;
    private String[] vehicles;
    private String[] species;

    public String getTitle() {
        return title;
    }

    public int getEpisode_id() {
        return episode_id;
    }

    public String getOpening_crawl() {
        return opening_crawl;
    }

    public String getDirector() {
        return director;
    }

    public String getProducer() {
        return producer;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String[] getCharacters() {
        return characters;
    }

    public String[] getPlanets() {
        return planets;
    }

    public String[] getStarships() {
        return starships;
    }

    public String[] getVehicles() {
        return vehicles;
    }

    public String[] getSpecies() {
        return species;
    }
}
