package dev.davidsima.swdata.models;

import java.util.List;
public class Planet {
    private String name;

    private String rotation_period;

    private String orbital_period;

    private String diameter;

    private String climate;

    private String gravity;

    private String terrain;

    private String surface_water;

    private String population;

    private List<String> residents;

    private List<String> films;

    private String created;

    private String edited;

    private String url;

    public int getId() {
        String[] splitUrl = url.split("/");
        int id = Integer.parseInt(splitUrl[splitUrl.length - 1]);
        return id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setRotation_period(String rotation_period){
        this.rotation_period = rotation_period;
    }
    public String getRotation_period(){
        return this.rotation_period;
    }
    public void setOrbital_period(String orbital_period){
        this.orbital_period = orbital_period;
    }
    public String getOrbital_period(){
        return this.orbital_period;
    }
    public void setDiameter(String diameter){
        this.diameter = diameter;
    }
    public String getDiameter(){
        return this.diameter;
    }
    public void setClimate(String climate){
        this.climate = climate;
    }
    public String getClimate(){
        return this.climate;
    }
    public void setGravity(String gravity){
        this.gravity = gravity;
    }
    public String getGravity(){
        return this.gravity;
    }
    public void setTerrain(String terrain){
        this.terrain = terrain;
    }
    public String getTerrain(){
        return this.terrain;
    }
    public void setSurface_water(String surface_water){
        this.surface_water = surface_water;
    }
    public String getSurface_water(){
        return this.surface_water;
    }
    public void setPopulation(String population){
        this.population = population;
    }
    public String getPopulation(){
        return this.population;
    }
    public void setResidents(List<String> residents){
        this.residents = residents;
    }
    public List<String> getResidents(){
        return this.residents;
    }
    public void setFilms(List<String> films){
        this.films = films;
    }
    public List<String> getFilms(){
        return this.films;
    }
    public void setCreated(String created){
        this.created = created;
    }
    public String getCreated(){
        return this.created;
    }
    public void setEdited(String edited){
        this.edited = edited;
    }
    public String getEdited(){
        return this.edited;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
}
