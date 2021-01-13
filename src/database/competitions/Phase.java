package database.competitions;

import java.util.Random;

public class Phase {
    private double budget;
    private String country;
    private int currentPhase = 1;
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



    public int generaParelles(Root competitions, int index,database.battles.Root batalles ) {
        int indexRival = 0;
        int numero = index;
        String rival = "";
        // si son imparells borra un
        if (competitions.getRappers().size() % 2 != 0) {
            while (numero == index) {
                Random rn = new Random();
                int n = competitions.getRappers().size() + 1;
                numero = rn.nextInt() % n;
            }
            System.out.println(competitions.getRappers().get(numero).getStageName());
            competitions.getRappers().remove(numero);
        }

        //Generem les parelles
        int nbatalles = competitions.getRappers().size()/2;
        String parelles [][] = new String [nbatalles][2];
        for (int i = 0; i < competitions.getRappers().size(); i++) {
            // recorrerem el vector, per a cada posicio generem una posicio random de la matriu i si esta buit s omple
            int fila = (int) (Math.random()*nbatalles);
            int columna = (int) (Math.random()*2);
            while(parelles[fila][columna] != null){
                fila = (int) (Math.random()*nbatalles);
                columna = (int) (Math.random()*2);
            }
            parelles[fila][columna] = competitions.getRappers().get(i).getStageName();
        }
        //Busquem el nostre rival

        for (int i = 0; i < parelles.length; i++) {
            for (int j = 0; j < parelles[i].length; j++) {
                if (parelles[i][j]==competitions.getRappers().get(numero).getStageName()){
                    if(j==0){
                        rival = parelles[i][1];
                    }else {
                        rival = parelles[i][0];
                    }
                }
            }
        }

        //busquem l index del rival
        for (int i = 0; i < competitions.getRappers().size(); i++) {
            if (competitions.getRappers().get(i).getStageName()== rival){
                indexRival = i;
            }

        }


        // Aqui simulem les batalles, sumem els punts y borrem els que perden.
        batalla.simulaBatalla(parelles,batalles,competitions);

        return indexRival;
    }
}
