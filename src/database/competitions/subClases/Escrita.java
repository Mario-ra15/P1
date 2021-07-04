package database.competitions.subClases;


/**
 * Clase que donde calculamos la rima cuando la batalla es Escrita
 */
public class Escrita {
    /**
     * Calcula la puntuación de la rima
     * @param n núemero de rimas correctas
     * @return puntuación
     */
    public double calculaPunts(int n){

        return (1+3*n);
    }
}
