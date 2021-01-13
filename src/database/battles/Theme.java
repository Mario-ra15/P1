package database.battles;

import java.util.LinkedList;
import java.util.Random;

public class Theme {
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

    /*public String generaTema(database.battles.Root batalles){
        String tema ;
        Random rn = new Random();
        int numero = rn.nextInt( )+ 1;

        return tema;
    }*/
}
