package dev.davidsima.swdata.models;

import java.util.List;

public class Vehicle {
    private String name;

    private String model;

    private String manufacturer;

    private String cost_in_credits;

    private String length;

    private String max_atmosphering_speed;

    private String crew;

    private String passengers;

    private String cargo_capacity;

    private String consumables;

    private String vehicle_class;

    private List<String> pilots;

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
    public void setModel(String model){
        this.model = model;
    }
    public String getModel(){
        return this.model;
    }
    public void setManufacturer(String manufacturer){
        this.manufacturer = manufacturer;
    }
    public String getManufacturer(){
        return this.manufacturer;
    }
    public void setCost_in_credits(String cost_in_credits){
        this.cost_in_credits = cost_in_credits;
    }
    public String getCost_in_credits(){
        return this.cost_in_credits;
    }
    public void setLength(String length){
        this.length = length;
    }
    public String getLength(){
        return this.length;
    }
    public void setMax_atmosphering_speed(String max_atmosphering_speed){
        this.max_atmosphering_speed = max_atmosphering_speed;
    }
    public String getMax_atmosphering_speed(){
        return this.max_atmosphering_speed;
    }
    public void setCrew(String crew){
        this.crew = crew;
    }
    public String getCrew(){
        return this.crew;
    }
    public void setPassengers(String passengers){
        this.passengers = passengers;
    }
    public String getPassengers(){
        return this.passengers;
    }
    public void setCargo_capacity(String cargo_capacity){
        this.cargo_capacity = cargo_capacity;
    }
    public String getCargo_capacity(){
        return this.cargo_capacity;
    }
    public void setConsumables(String consumables){
        this.consumables = consumables;
    }
    public String getConsumables(){
        return this.consumables;
    }
    public void setVehicle_class(String vehicle_class){
        this.vehicle_class = vehicle_class;
    }
    public String getVehicle_class(){
        return this.vehicle_class;
    }
    public void setPilots(List<String> pilots){
        this.pilots = pilots;
    }
    public List<String> getPilots(){
        return this.pilots;
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
