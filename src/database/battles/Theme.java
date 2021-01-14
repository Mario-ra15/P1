package database.battles;

import java.util.LinkedList;
import java.util.Random;

public class Theme {
    private String tema;
    private String name;
    private LinkedList<Rhyme> rhymes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Rhyme> getRhymes() {
        return rhymes;
    }

    public void setRhymes(LinkedList<Rhyme> rhymes) {
        this.rhymes = rhymes;
    }

    public String generaTema(database.battles.Root batalles){

        Random rn = new Random();
        int numero = rn.nextInt( batalles.getThemes().size());
        tema = batalles.getThemes().get(numero).getName();

        return tema;
    }

    public int postema(database.battles.Root batalles, String tema) {
        for (int i = 0; i < batalles.getThemes().size(); i++) {
            if (batalles.getThemes().get(i).getName() == tema) {
                return i;
            }
        }
        return 6;
    }
}
