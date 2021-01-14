package database.competitions;


import database.battles.Theme;

import java.util.Random;

public class Batalla {
    private String tipusBatalla;
    private int currentBattle = 1;
    Theme theme = new Theme();

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

    public void simulaBatalla(String[][] parelles,database.battles.Root batalles,Root competitions){
        String tema;
        int n;

        //agafem les parelles
        for (int i = 0; i < parelles.length; i++) {

            int primer = generaPrimer();
            tema = theme.generaTema(batalles);
            n = theme.postema(batalles,tema);





        }

    }

    private int generaPrimer(){
        int primer = (int) (Math.random()*2);
        return primer;
    }


}
