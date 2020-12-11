package Batallas;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;


public class ReadJson {
    private LinkedList<Competition> competition;
    private LinkedList<String> countries;
    private LinkedList<Rappers> rappers;

    public void ReadJason() {
        competition = new LinkedList<>();
        countries = new LinkedList<>();
        rappers = new LinkedList<>();
    }

    //Funcio per lleguir el Json
    public void readJson() {
        Gson gson = new Gson();
        try {
            JsonReader reader = new JsonReader(new FileReader("competició.json"));
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<String> getCountries() {
        return countries;
    }

    public LinkedList<Rappers> getRappers() {
        return rappers;
    }

    public LinkedList<Competition> getCompetition() {
        return competition;
    }
}
