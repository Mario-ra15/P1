package Batallas;
import com.google.gson.JsonArray;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;


public class ReadJson {
    private LinkedList<Lloc> llocs;
    private LinkedList<Monument> monuments;
    private LinkedList<Restaurant> restaurants;
    private LinkedList<Hotel> hotels;
    private LinkedList<Location> locations;

    public ReadJason() {
        llocs = new LinkedList<>();
        monuments = new LinkedList<>();
        restaurants = new LinkedList<>();
        hotels = new LinkedList<>();
        locations = new LinkedList<>();
    }

    public LinkedList<Location> getLlocs()
    {return locations;}


    //Funcio per lleguir el Json
    public void readJson() {
        Gson gson = new Gson();
        try {
            JsonReader reader = new JsonReader(new FileReader("localitzacions.json"));
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
