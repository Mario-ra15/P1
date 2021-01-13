package Util;

import database.competitions.Root;

public class Controller {

    private String aux;
    private int opcio;
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
     * demana el nom del artista y comprova que existeixi.
     */
    public int comprovaLogin(Root competitions, String artisticName) {
        for(int i = 0; i < competitions.getRappers().size(); i++){
            if (competitions.getRappers().get(i).getStageName().equals(artisticName)) {
                return i;
            }
        }
        return -1;
    }
}
