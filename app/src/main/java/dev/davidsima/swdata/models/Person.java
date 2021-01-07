package dev.davidsima.swdata.models;

import java.util.List;

public class Person {
    private String name;

    private String height;

    private String mass;

    private String hair_color;

    private String skin_color;

    private String eye_color;

    private String birth_year;

    private String gender;

    private String homeworld;

    private List<String> films;

    private List<String> species;

    private List<String> vehicles;

    private List<String> starships;

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
    public void setHeight(String height){
        this.height = height;
    }
    public String getHeight(){
        return this.height;
    }
    public void setMass(String mass){
        this.mass = mass;
    }
    public String getMass(){
        return this.mass;
    }
    public void setHair_color(String hair_color){
        this.hair_color = hair_color;
    }
    public String getHair_color(){
        return this.hair_color;
    }
    public void setSkin_color(String skin_color){
        this.skin_color = skin_color;
    }
    public String getSkin_color(){
        return this.skin_color;
    }
    public void setEye_color(String eye_color){
        this.eye_color = eye_color;
    }
    public String getEye_color(){
        return this.eye_color;
    }
    public void setBirth_year(String birth_year){
        this.birth_year = birth_year;
    }
    public String getBirth_year(){
        return this.birth_year;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public String getGender(){
        return this.gender;
    }
    public void setHomeworld(String homeworld){
        this.homeworld = homeworld;
    }
    public String getHomeworld(){
        return this.homeworld;
    }
    public void setFilms(List<String> films){
        this.films = films;
    }
    public List<String> getFilms(){
        return this.films;
    }
    public void setSpecies(List<String> species){
        this.species = species;
    }
    public List<String> getSpecies(){
        return this.species;
    }
    public void setVehicles(List<String> vehicles){
        this.vehicles = vehicles;
    }
    public List<String> getVehicles(){
        return this.vehicles;
    }
    public void setStarships(List<String> starships){
        this.starships = starships;
    }
    public List<String> getStarships(){
        return this.starships;
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
