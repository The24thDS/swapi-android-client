package dev.davidsima.swdata.models;

import java.util.List;

public class Species {
    private String name;

    private String classification;

    private String designation;

    private String average_height;

    private String skin_colors;

    private String hair_colors;

    private String eye_colors;

    private String average_lifespan;

    private String homeworld;

    private String language;

    private List<String> people;

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
    public void setClassification(String classification){
        this.classification = classification;
    }
    public String getClassification(){
        return this.classification;
    }
    public void setDesignation(String designation){
        this.designation = designation;
    }
    public String getDesignation(){
        return this.designation;
    }
    public void setAverage_height(String average_height){
        this.average_height = average_height;
    }
    public String getAverage_height(){
        return this.average_height;
    }
    public void setSkin_colors(String skin_colors){
        this.skin_colors = skin_colors;
    }
    public String getSkin_colors(){
        return this.skin_colors;
    }
    public void setHair_colors(String hair_colors){
        this.hair_colors = hair_colors;
    }
    public String getHair_colors(){
        return this.hair_colors;
    }
    public void setEye_colors(String eye_colors){
        this.eye_colors = eye_colors;
    }
    public String getEye_colors(){
        return this.eye_colors;
    }
    public void setAverage_lifespan(String average_lifespan){
        this.average_lifespan = average_lifespan;
    }
    public String getAverage_lifespan(){
        return this.average_lifespan;
    }
    public void setHomeworld(String homeworld){
        this.homeworld = homeworld;
    }
    public String getHomeworld(){
        return this.homeworld;
    }
    public void setLanguage(String language){
        this.language = language;
    }
    public String getLanguage(){
        return this.language;
    }
    public void setPeople(List<String> people){
        this.people = people;
    }
    public List<String> getPeople(){
        return this.people;
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