package database.competitions;

import database.battles.Theme;

import java.util.LinkedList;
import java.util.Random;

/**
 * Guardamos la información referente a la phase,
 *
 * Los metodo rebien información de la competición y nos perimten hacer las batallas
 */
public class Phase {
    private double budget;
    private String country;
    private int currentPhase = 1;
    private String tipus;

    Batalla batalla = new Batalla();


    public int getCurrentPhase() {
        return currentPhase;
    }

    public void sumaPhase(int Phase){
        currentPhase++;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    /**
     * Genera una matriz de dos columnas para saber los emparejamientos
     * @param index posición de nuestro competidor
     * @param competidors lista de los competidores actuales
     * @return una matriz de X filas por 2 columnas para los enfrentamientos
     */
    public String[][] generaParelles(int index,LinkedList<String> competidors ) {

        int numero ;

        if (competidors.size()%2 != 0 && competidors.size() != 1 ) { //Borrem en cas de que siguin imparells

            numero = (int) (Math.random()*competidors.size() - 1);
            while (index == numero ) {
                numero = (int) (Math.random()*competidors.size() - 1);
                System.out.println("ha tocat el " + numero);
            }
            System.out.println("Com son imparells aleatoriament borrem " + competidors.get(numero));
            competidors.remove(numero);
        }

        //Generem les parelles

        int nbatalles = competidors.size()/2;

        String parelles [][] = new String [nbatalles][2];



        for (int i = 0; i < competidors.size(); i++) {
            // recorrerem el vector, per a cada posicio generem una posicio random de la matriu i si esta buit s omple
            int fila = (int) (Math.random()*nbatalles);
            int columna = (int) (Math.random()*2);
            while(parelles[fila][columna] != null){
                fila = (int) (Math.random()*nbatalles);
                columna = (int) (Math.random()*2);
            }
            parelles[fila][columna] = competidors.get(i);
        }


        return parelles;
    }


    /**
     * Busca el rival de nuestro participante en la lista de las parejas
     * @param name nombre de nuestro participante
     * @param parelles lista de las parejas para esta batalla
     * @return nombre del rival
     */
    public String buscarRival(String name, String[][]parelles ) {
        //busquem l index del rival
        String rival = "";
        for (int i = 0; i < parelles.length; i++) {
            for (int j = 0; j < parelles[i].length; j++) {

                if(name.equals(parelles[i][j]) ){
                    if(j == 0) rival = parelles[i][1];
                    if(j==1) rival = parelles[i][0];
                }
            }
        }
        if (rival == "") rival = "Bro has perdido";
        return rival;
    }

    /**
     * Comprueva si nuestro personaje esta eliminado
     * @param competidors lista de los competidores
     * @param artisticName nombre del competidor
     * @return BoOlean que indica si esta o no
     */
    public boolean comprovaUsuari (LinkedList<String> competidors, String artisticName){
        boolean eliminat = false;
        for(int i = 0; i < competidors.size(); i++){
            if (competidors.get(i).equals(artisticName)) {
                eliminat = true;
            }
        }
        return eliminat;
    }

}
