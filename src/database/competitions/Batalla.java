package database.competitions;


import Util.Controller;
import database.battles.Rhyme;
import database.battles.Theme;
import database.competitions.subClases.*;

import java.util.LinkedList;
import java.util.Random;

/**
 * Esta clase gaurda toda la información de las batallas y hace los calculos necesarios para hacer la batalla
 *
 * Los metodos reciven la información del menu del transcurso de la competición y envia la información a las distintas clases
 * para hacer la batalla
 */
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
    private String rimaTu1;
    private String rimaTu2;


    Theme theme = new Theme();
    Rhyme rhyme = new Rhyme();
    Escrita be = new Escrita();
    Sangre bs = new Sangre();
    Acapella ba = new Acapella();
    Controller controller = new Controller();

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

    /**
     * Genera una de les tres batalles aletoriament
     */
    public void generaTipusBatalla() {
        Random rn = new Random();
        int numero = rn.nextInt(3) + 1;

        if(numero == 1){
            tipusBatalla = "sangre";
        } else if (numero == 2) {
            tipusBatalla = "acapella";
        }else{
            tipusBatalla = "escrita";
        }
    }

    /**
     * Esta clase nos simula la batalla cuando no somos los participantes
     * @param parelles Las parejas que harán las batallas en forma de matriz
     * @param batalles Información de las batllas
     * @param competitions Información de la competición
     * @param competidors Una lista de los competidores actuales
     * @param name El nombre de la persona ganadora.
     */
    public void simulaBatalla(String[][] parelles, database.battles.Root batalles, Root competitions, LinkedList<String> competidors,String name){

        //System.out.println("hi han\n" + competidors);
        //agafem les parelles
        //for (int i = 0; i < parelles.length; i++) {
          //  System.out.println(parelles[i][0] + " - " + parelles[i][1]);

        //}

        for (int i = 0; i < parelles.length; i++) { //Recorrem la matriu per filas
            if(parelles[i][0].equals(name) || parelles[i][1].equals(name)) { //saltem la nostra batlla

            }else{
                tema = theme.generaTema(batalles);
                n = theme.postema(batalles,tema);

                //Mirem el nivell del rapper1
                for (int j = 0; j < competitions.getRappers().size();j++) {
                    if (competitions.getRappers().get(j).getStageName().equals(parelles[i][0])) {
                        level1 = competitions.getRappers().get(j).getLevel();
                    }
                    //mirem el nivell del rapper 2
                    if (competitions.getRappers().get(j).getStageName().equals(parelles[i][1])) {
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
                actualitzaPunts(rapper1,rapper2,p1,p2,competitions);
                //fiquem els punts de cada
                actualitzaCompetidors(rapper1,rapper2,p1,p2,competidors);
            }
        }
    }


    /**
     * selecciones las rimas y calcula su puntuación
     * @param batalles Informació de les batalles
     * @param level nivel del participante
     * @return puntuación de la rima
     */
    private double buscarimas(database.battles.Root batalles, int level) {
        double punts;
        if(level == 1) {
            if(batalles.getThemes().get(n).getRhymes().get(0).get1().size() == 1){
                R =calculaRima(batalles.getThemes().get(n).getRhymes().get(0).get1().get(0),"nada");
            }else if(batalles.getThemes().get(n).getRhymes().get(0).get1().size()== 0) {
                R = calculaRima("nada", "nada");
            }else {
                R =calculaRima(batalles.getThemes().get(n).getRhymes().get(0).get2().get(0), batalles.getThemes().get(n).getRhymes().get(0).get2().get(1));
            }
        }else{
            if(batalles.getThemes().get(n).getRhymes().get(0).get2().size() == 1) {
                R = calculaRima(batalles.getThemes().get(n).getRhymes().get(0).get2().get(0),"nada");
            }else if(batalles.getThemes().get(n).getRhymes().get(0).get2().size() == 0) {
                R =  calculaRima("nada","nada");
            }else {
                R = calculaRima(batalles.getThemes().get(n).getRhymes().get(0).get2().get(0), batalles.getThemes().get(n).getRhymes().get(0).get2().get(1));
            }
        }

        punts = sumaPunts(R,tipusBatalla);

        return punts;

    }

    /**
     * Genera un número para seleccionar el primero
     * @return el número que ha salido
     */
    private int generaPrimer(){
        int primer = (int) (Math.random()*2);
        return primer;
    }

    /**
     * calcula el número de rimas correctas
     * @param rima1 La primera intervención
     * @param rima2 La segunda intervención
     * @return el numero de rimas correctas
     */

    public int calculaRima(String rima1, String rima2){
        //separem la rima en 4 frases
        int n1;
        int n2;
        if (rima1.equals("nada")){ return 0;}
        n1 = rhyme.calculaRima(rima1);
        if (rima2.equals("nada")){return n1;}
        n2 = rhyme.calculaRima((rima2));
        return (n1+n2);
    }


    /**
     *
     * hace el proceso del participante para la batalla
     * @param batalles Información de las batallas
     * @param competitions Información de las competiciones
     * @param competidors Lista de los competidores actuales
     * @param name Nombre de nuestro participante
     * @param rival nombre del rival al que enfrentamos
     */

    public void doBattle(database.battles.Root batalles, Root competitions, LinkedList<String> competidors,String name, String rival) {
        tema = theme.generaTema(batalles); //generem tema y la seva posicio
        n = theme.postema(batalles, tema);
        int ronda = 1;

        String rimacontrincant;

        //agafaem el level del rival
        for (int j = 0; j < competitions.getRappers().size(); j++) {
            if (competitions.getRappers().get(j).getStageName().equals(rival)) {
                level1 = competitions.getRappers().get(j).getLevel();
            }
        }
        //mirem qui comença (genera un numero entre 0 i 1)
        int primer = generaPrimer();

        System.out.println("Topic" + tema + "\n");
        System.out.println("A coin is tossed in the air and...");


        while(ronda <= 2) {
            if (primer == 0) {
                System.out.println(rival + " your turn! Drop it!\n");
                System.out.println(rival + "\n");
                rimacontrincant = rhyme.getRima(batalles, level1, ronda, n);
                System.out.println(rimacontrincant);
                System.out.println("\n");
                System.out.println("Your tourn\nEnter your verse: \n");
                System.out.println(name);
                if(ronda == 1) rimaTu1 = controller.askForRima();
                if (ronda ==2) rimaTu2 = controller.askForRima();
                System.out.println("\n");
                ronda++;
            }
            if (primer == 1) {
                System.out.println(name + " your turn! Drop it!\n");
                System.out.println("Your tourn\nEnter your verse: \n");
                System.out.println(name);
                if(ronda == 1) rimaTu1 = controller.askForRima();
                if (ronda ==2) rimaTu2 = controller.askForRima();
                System.out.println("\n");
                System.out.println(rival + "\n");
                rimacontrincant = rhyme.getRima(batalles, level1, ronda, n);
                System.out.println(rimacontrincant);
                System.out.println("\n");
                ronda++;
            }

        }

        //Mirem els punts que treu el nostre contrincant y els seus
       double punts1 = buscarimas(batalles, level1);
        int pT = calculaRima(rimaTu1,rimaTu2);
        double punts2 = sumaPunts(pT,tipusBatalla);



        System.out.println(punts1 + " puntos de " + rival);
        System.out.println(punts2 + " puntos de " + name);


        actualitzaPunts(rival,name,punts1,punts2,competitions);
        actualitzaCompetidors(rival,name,punts1,punts2,competidors);

       // System.out.println("han acabat\n" + competidors);


    }

    /**
     * Suma los puntos
     * @param punts número de rimas correctas
     * @param tipusBatalla tipo de batalla que seha hecho
     * @return el numero de puntos obtenido en esta batalla
     */

    private double sumaPunts(int punts, String tipusBatalla){
        double puntsFinals=0;
        if(tipusBatalla.equals("sangre")){puntsFinals = bs.calculaPunts(punts);}
        if(tipusBatalla.equals("escrita")){puntsFinals = be.calculaPunts(punts);}
        if(tipusBatalla.equals("acapella")){puntsFinals = ba.calculaPunts(punts);}
        return puntsFinals;
    }

    /**
     * Actualiza los puntos en el ranking de cada personaje
     * @param rapper1 nombre del rapero1
     * @param rapper2 nombre del rapero2
     * @param p1 puntos del rapero1
     * @param p2 puntos del rapero2
     * @param competitions Información de la competición
     */

    private void actualitzaPunts(String rapper1,String rapper2, double p1, double p2 ,Root competitions){
        for (int n = 0; n < competitions.getRappers().size(); n++ ){
            if(competitions.getRappers().get(n).getStageName().equals(rapper1)){
                competitions.getRappers().get(n).setScore((competitions.getRappers().get(n).getScore()+p1));
            }
            if(competitions.getRappers().get(n).getStageName().equals(rapper2)){
                competitions.getRappers().get(n).setScore((competitions.getRappers().get(n).getScore()+p2));
            }
        }

    }

    /**
     * Mira quin dels dos a guanyat y borra el competidor que ha perdut de la llista
     * @param rapper1 nom del rapero1
     * @param rapper2 nom del repero2
     * @param p1 puntos del rapero1
     * @param p2 puntos del rapero2
     * @param competidors lista de los competidores actuales
     */
    private void actualitzaCompetidors(String rapper1,String rapper2,double p1, double p2,LinkedList<String> competidors){

        if (p1 > p2 ) {

            for (int j = 0; j < competidors.size(); j++) {
                if (competidors.get(j).equals(rapper2)) {

                    competidors.remove(j);
                }
            }
        }else if(p2 >= p1){

            for (int j = 0; j < competidors.size(); j++) {
                if (competidors.get(j).equals(rapper1)) {

                    competidors.remove(j);
                }
            }
        }

    }
}
