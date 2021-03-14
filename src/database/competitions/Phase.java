package database.competitions;

import database.battles.Theme;

import java.util.LinkedList;
import java.util.Random;

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



    public String[][] generaParelles(int index,LinkedList<String> competidors ) {

        int numero ;
        System.out.println(competidors.size());
        if (competidors.size()%2 != 0 && competidors.size() != 1 ) { //Borrem en cas de que siguin imparells
            // quan queda nomes 1 se prende la wea, abans estaba el +1 pero reventaba si sortia el size max mes 1
            numero = (int) (Math.random()*competidors.size() - 1);
            while (index == numero ) { //fa bucle infinit aqui
                numero = (int) (Math.random()*competidors.size() - 1);
                System.out.println("ha tocat el " + numero);
            }
            System.out.println("aixi que borrem el " + competidors.get(numero));
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

}
