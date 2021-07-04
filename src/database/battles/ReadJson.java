package database.battles;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Esta clase nos permite acceder al Json leerlo, y guardarlo en una clase.
 *
 * Recibe el Json y lo envia a otra clase
 */

public class ReadJson {

    /**
     * Método que le la información del Json
     *
     * @return Información del Jsno y la envia a la clase Root
     */
    public static Root jsonToClass() {
        Root root = null;
        try {
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader("src/files/batalles.json"));
            root = gson.fromJson(reader, Root.class);
        } catch (FileNotFoundException e) {
            System.out.println("Error, no s'ha trobat el fitxer de competició.\n" +
                    "Sortint del programa...");
            System.exit(0);
        }
        return root;
    }
}
