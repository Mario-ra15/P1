package database.competitions.subClases;

/**
 * Clase que donde calculamos la rima cuando la batalla es a Sangre
 */
public class Sangre {

    /**
     * Calcula la puntuación de la rima
     * @param n núemero de rimas correctas
     * @return puntuación
     */
    public double calculaPunts(int n){

        double p =  ((Math.PI*Math.pow(n,2))/4);

        return p ;
    }
}
