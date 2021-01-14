import database.battles.Root;
import database.battles.Theme;
import database.competitions.ReadJson;
import Util.*;

import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws ParseException {

        Menu menu = new Menu();


        database.battles.Root batalles;
        batalles = database.battles.ReadJson.jsonToClass();

        database.competitions.Root competicions;
        competicions = database.competitions.ReadJson.jsonToClass();


        menu.optionMenu(batalles,competicions);

    }
}
