package database.competitions;


import database.battles.Rhyme;
import database.battles.Theme;
import database.competitions.subClases.*;

import java.util.LinkedList;
import java.util.Random;

public class Batalla {
    private String tipusBatalla;
    private int currentBattle = 1;
    private String tema;
    private int R;
    private int level1 ;
    private int level2;
    private int n;
    private String rapper1;
    private String rapper2;
    Theme theme = new Theme();
    Rhyme rhyme = new Rhyme();
    Escrita be = new Escrita();
    Sangre bs = new Sangre();
    Acapella ba = new Acapella();

    public int getCurrentBattle() {
        return currentBattle;
    }


    public void sumaBatalla(int Battle){
        currentBattle++;
    }

    public String getTipusBatalla() {
        return tipusBatalla;
    }

    public void setCurrentBattle(int currentBattle) {
        this.currentBattle = currentBattle;
    }

    public void setTipusBatalla(String tipusBatalla) {
        this.tipusBatalla = tipusBatalla;
    }

    public void GeneraTipusBatalla() {
        Random rn = new Random();
        int numero = rn.nextInt(3) + 1;

        if(numero == 1){
            tipusBatalla = "sangre";
        } else if (numero == 2) {
            tipusBatalla = "acapella";
        }else{
            tipusBatalla = "escrita";
        }

        //nomes retorna escrita sigui el numero que sigui
        /*switch (numero) {
            case 1:
                tipusBatalla = "sangre";
            case 2:
                tipusBatalla = "acapella";
            case 3:
                tipusBatalla = "escrita";
        }*/

    }

   /* public void simulaBatalla(String[][] parelles, database.battles.Root batalles, Root competitions, LinkedList<String> competidors){
        System.out.println(" ");
        System.out.println("competidores Iniciales");
        System.out.println(" ");
        for (int l = 0; l < competidors.size(); l++){
            System.out.println(competidors.get(l));
        }
        GeneraTipusBatalla();

        //agafem les parelles
        for (int i = 0; i < parelles.length; i++) {


            int primer = generaPrimer();
            tema = theme.generaTema(batalles);
            n = theme.postema(batalles,tema);

            //Mirem el nivell del rapper1
            for (int j = 0; j < competitions.getRappers().size();j++) {
                if (competitions.getRappers().get(j).getStageName() == parelles[i][0]) {
                    level1 = competitions.getRappers().get(j).getLevel();
                }
                //mirem el nivell del rapper 2
                if (competitions.getRappers().get(j).getStageName() == parelles[i][1]) {
                    level2 = competitions.getRappers().get(j).getLevel();
                }
            }

                //Agafem les rimes del rapper1
                double p1 = buscarimas(batalles, level1);
                //agafem les rimes del rapper2
                double p2 = buscarimas(batalles, level2);


            //mirem qui es el guanyador y borrem l altre
            rapper1 = parelles[i][0];
            rapper2 = parelles[i][1];
            //fiquem els punts de cada jugador
            for (int n = 0; n < competitions.getRappers().size(); n++ ){
                if ( competitions.getRappers().get(n).getStageName() == rapper1 || competitions.getRappers().get(n).getStageName() == rapper2 ){
                    if(competitions.getRappers().get(n).getStageName() == rapper1){
                        competitions.getRappers().get(n).setScore((competitions.getRappers().get(n).getScore()+p1));
                    }
                    if(competitions.getRappers().get(n).getStageName() == rapper2){
                        competitions.getRappers().get(n).setScore((competitions.getRappers().get(n).getScore()+p2));
                    }
                }

            }



            if (p1 > p2 ) {
                System.out.println("ha guanyat " + rapper1);

                for (int j = 0; j < competidors.size(); j++) {
                    if (competidors.get(j).equals(rapper2)) {
                        System.out.println(("borramos a " + rapper2));
                        competidors.remove(j);
                    }
                }
            }else if(p2 >= p1){
                System.out.println("ha guanyat " +rapper2);

                for (int j = 0; j < competidors.size(); j++) {
                    if (competidors.get(j).equals(rapper1)) {
                        System.out.println(("borramos a " + rapper1));
                        competidors.remove(j);
                    }
                }
            }


        }
        System.out.println(" ");
        System.out.println("Lista de parejas");
        System.out.println(" ");
        for (int m = 0; m < parelles.length; m++) {
            for (int j = 0; j < parelles[m].length; j++) {
                System.out.print(parelles[m][j]+" ");
            }
            System.out.print("\n");
        }
        System.out.println(" ");
        System.out.println("competidores Finales");
        System.out.println(" ");
        for (int l = 0; l < competidors.size(); l++){
            System.out.println(competidors.get(l));
        }

    }*/

    private double buscarimas(database.battles.Root batalles, int level) {
        double punts = 0.0;
        if(level == 1) {
            if(batalles.getThemes().get(n).getRhymes().get(0).get1().size() == 1){
                R =preparaRima(batalles.getThemes().get(n).getRhymes().get(0).get1().get(0),"nada");
            }else if(batalles.getThemes().get(n).getRhymes().get(0).get1().size()== 0) {
                R = preparaRima("nada", "nada");
            }else {
                R =preparaRima(batalles.getThemes().get(n).getRhymes().get(0).get2().get(0), batalles.getThemes().get(n).getRhymes().get(0).get2().get(1));
            }
        }else{
            if(batalles.getThemes().get(n).getRhymes().get(0).get2().size() == 1) {
                R = preparaRima(batalles.getThemes().get(n).getRhymes().get(0).get2().get(0),"nada");
            }else if(batalles.getThemes().get(n).getRhymes().get(0).get2().size() == 0) {
                R =  preparaRima("nada","nada");
            }else {
                R = preparaRima(batalles.getThemes().get(n).getRhymes().get(0).get2().get(0), batalles.getThemes().get(n).getRhymes().get(0).get2().get(1));
            }
        }
        if(tipusBatalla.equals("sangre")){punts = bs.calculaPunts(R);}
        if(tipusBatalla.equals("escrita")){punts = be.calculaPunts(R);}
        if(tipusBatalla.equals("acapella")){punts = ba.calculaPunts(R);}

        return punts;

    }

    private int generaPrimer(){
        int primer = (int) (Math.random()*2);
        return primer;
    }

    public int preparaRima(String rima1, String rima2){
        //separem la rima en 4 frases
        int n1;
        int n2;
        if (rima1.equals("nada")){ return 0;}
        n1 = rhyme.calculaRima(rima1);
        if (rima2.equals("nada")){return n1;}
        n2 = rhyme.calculaRima((rima2));
        return (n1+n2);
    }



}
