package Api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Connexio {

    public Connexio() {
    }

    private JsonObject getJsonForCountry(String country) {
        try {
            URL url = new URL("https://restcountries.eu/rest/v2/name/" + URLEncoder.encode(country, StandardCharsets.UTF_8.toString()));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            int status = con.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            if (status == 200) {
                return JsonParser.parseString(content.toString()).getAsJsonArray().get(0).getAsJsonObject();
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Error al fer la crida REST");
            return null;
        }
    }

}
