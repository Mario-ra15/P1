package database.competitions;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;


public class ReadJson {
    //Funcio per lleguir el Json
    public static Root jsonToClass() {
        Root root = null;
        try {
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader("src/files/competició.json"));
            root = gson.fromJson(reader, Root.class);
        } catch (FileNotFoundException e) {
            System.out.println("Error, no s'ha trobat el fitxer de competició.\n" +
                    "Sortint del programa...");
            System.exit(0);
        }
        return root;
    }
}
