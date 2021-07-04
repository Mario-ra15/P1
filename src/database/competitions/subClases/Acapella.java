package database.competitions.subClases;

import database.competitions.Batalla;

/**
 * Clase que donde calculamos la rima cuando la batalla es acapella
 */
public class Acapella  {

    /**
     * Calcula la puntuación de la rima
     * @param n núemero de rimas correctas
     * @return puntuación
     */
    public double calculaPunts(int n){
        double p = (6*Math.sqrt(n)+3)/2;

        return p ;
    }
}
