package database.battles;

import java.util.LinkedList;
import java.util.Random;

/**
 * En esta clase guardamos los temas de las diferentes batallas
 *
 * Guardem els temas, los métodos reciben la inforamción de la batalla para generar un tema random,
 */
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

    /**
     * Genera el tema aleatoriament
     * @param batalles información de las batallas
     * @return un tema aleatorio
     */

    public String generaTema(database.battles.Root batalles){

        Random rn = new Random();
        int numero = rn.nextInt( batalles.getThemes().size());
        tema = batalles.getThemes().get(numero).getName();

        return tema;
    }

    /**
     * Nos retorna la posición del tema
     * @param batalles La información de las batallas
     * @param tema El tema del que queremos saber la posición
     * @return La posició del tema
     */

    public int postema(database.battles.Root batalles, String tema) {
        for (int i = 0; i < batalles.getThemes().size(); i++) {
            if (batalles.getThemes().get(i).getName() == tema) {
                return i;
            }
        }
        return 6;
    }


}
