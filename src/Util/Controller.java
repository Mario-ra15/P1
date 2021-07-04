package Util;

import database.competitions.Root;

/**
 * Esta clase nos permite interactuar con el usuario
 */
public class Controller {

    private String aux;
    private int opcio;
    private String linia1;
    private String linia2;
    private String linia3;
    private String linia4;
    private String rima;
    private static final int MIN = 1;
    private static final int MAX = 2;
    private static final int MAX2 = 4;


    /**
     * mira si la opcio es la de sortir
     * @return boolea que confirma si surt o no
     */
    public boolean exit() {
        return opcio == MAX;
    }

    /**
     * mira si la opcio es la de sortir
     * @return boolea que confirma si surt o no
     */
    public boolean exit2() {
        return opcio == MAX2;
    }

    /**
     * demana la opció i comprova que no tingui mes d'un caracter i que sigui el caracter demanat.
     */
    public void askForOption() {
        System.out.println("Choose an option: ");
        aux = ScannerInput.askString();
        if (aux.length() > 1) {
            return;
        }
        try {
            opcio = Integer.parseInt(String.valueOf(aux.charAt(0)));
        } catch (NumberFormatException e) {
            System.out.println("La opció ha de ser un nombre.\n");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Has d'introduir un caracter.\n");
        }
    }
    public int getOption() {
        return opcio;
    }

    public boolean validOption() {
        return opcio >= MIN && opcio <= MAX;
    }
    public boolean validOption2() {
        return opcio >= MIN && opcio <= MAX2;
    }


    /**
     * Comprueba si el nombre artistico existe
     * @param competitions información de la competicion
     * @param artisticName nombre a comprovar
     * @return boolean que indica si esta o no.
     */
    public int comprovaLogin(Root competitions, String artisticName) {
        for(int i = 0; i < competitions.getRappers().size(); i++){
            if (competitions.getRappers().get(i).getStageName().equals(artisticName)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Compureba si el nombre del usuario que quiere mostrar existe
     * @param competitions información de los competidores
     * @param name nombre que buscamos
     * @return indica su posicion y -1 si no existe
     */
    public int comprovaUsuari(Root competitions, String name){
        for(int i = 0; i < competitions.getRappers().size(); i++){
            if (competitions.getRappers().get(i).getStageName().equals(name)) {
                return i;
            }
        }
        for(int i = 0; i < competitions.getRappers().size(); i++){
            if (competitions.getRappers().get(i).getRealName().equals(name)) {
                return i;
            }
        }
        return -1;

    }

    /**
     * Comprueba que la rima tenga almenos 4 letras para poder calcular la rima
     * @return Calcula la rima
     */
    public String askForRima(){

        linia1 = ScannerInput.askString();
        while (linia1.length() < 4){
            System.out.println("ese las barras han de ser de almenys 4 caracters bro,torna a repetirla");
            linia1 = ScannerInput.askString();
        }

        linia2 = ScannerInput.askString();
        while (linia2.length() < 4){
            System.out.println("ese las barras han de ser de almenys 4 caracters bro,torna a repetirla");
            linia2 = ScannerInput.askString();
        }

        linia3 = ScannerInput.askString();
        while (linia3.length() < 4){
            System.out.println("ese las barras han de ser de almenys 4 caracters bro,torna a repertirla");
            linia3 = ScannerInput.askString();
        }

        linia4 = ScannerInput.askString();
        while (linia4.length() < 4){
            System.out.println("ese las barras han de ser de almenys 4 caracters bro,torna a repetirla");
            linia4 = ScannerInput.askString();
        }

        rima = linia1+"\n"+linia2+"\n"+linia3+"\n"+linia4;
        return rima;
    }
}
