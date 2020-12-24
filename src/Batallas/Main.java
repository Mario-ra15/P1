package Batallas;
import Util.Menu;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import javax.xml.crypto.Data;

public class Main {

    public static void main(String[] args) {
        ReadJson data = new ReadJson();
        data.readJson();

        Menu menu = new Menu( );
        menu.optionMenu();

    }
}
