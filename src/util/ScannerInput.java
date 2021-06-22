package Util;

import java.util.Scanner;

public class ScannerInput {
    private static Scanner in = new Scanner(System.in);

    /**
     * demana una String a l'usuari
     * @return la String llegida
     */
    public static String askString(){

        return in.nextLine();
    }

    /**
     * demana un enter a l'usuari
     * @return l'enter llegit
     */
    public static int askInteger() {
        return in.nextInt();
    }

    /**
     * neteja el buffer
     */
    public static void fflush() {
        in.nextLine();
    }

    /**
     * demana un Double a l'usuari
     * @return el Double llegit
     */
    public static double askDouble() {
        return in.nextDouble();
    }
}
