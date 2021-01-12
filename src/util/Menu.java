package Util;
import database.competitions.Rapper;
import database.competitions.Root;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;


public class Menu {
    private static final int MIN = 1;
    private static final int MAX = 2;
    private static final int MAX2 = 4;

    private int opcio;
    private int index;
    private int indexRival;
    private int indexPersona;
    private int currentPhase = 0;
    private String aux;

    private String nom;
    private String artisticName;
    private String date;
    private String country;
    private int level;
    private String photo;
    private String tipusBatalla = null;
    private String state = null;
    private Rapper rapper;


    public void printMenu(Root competitions) throws ParseException {
        System.out.println(" ");
        SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
        System.out.println("Welcome to competition: " + competitions.getCompetition().getName());
        System.out.println("Starts on " + competitions.getCompetition().getStartDate());
        System.out.println("Ends on " + competitions.getCompetition().getEndDate());
        System.out.println("Phases: " + competitions.getCompetition().getPhase().size());//maybe aqui es pot posar un phases.size
        System.out.println("Currently: " + competitions.getRappers().size() + " participants\n");
    }

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

    public void optionMenu(database.battles.Root batalles, Root competitions) throws ParseException {
        printMenu(competitions);
        do {
            SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
            Date actualDate = dateFormat.parse(String.valueOf(LocalDateTime.now()));

            if (actualDate.before(competitions.getCompetition().getStartDate())) {
                state = "before";
            } else if (actualDate.before(competitions.getCompetition().getEndDate())){ // no entra aquest menu. no detecta les dates
                state = "after";
            } else {
                state = "current";
            }

            switch (state){
                case "before":
                    System.out.println("\nCompetition hasn't started yet. Do you want to:\n\n1.Register\n2.Leave\n");
                    do {
                        askForOption();
                        switch (getOption()) {
                            case 1:
                                registre(competitions);
                                break;
                            case 2:
                                break;
                        }
                    } while (!validOption());
                    break;
                case "current":
                    do {
                        System.out.println("\nCompetition started. Do you want to:\n\n1.Login\n2.Leave\n");
                        askForOption();
                        switch (getOption()) {
                            case 1:
                                System.out.println("Enter your artistic name:");
                                artisticName = "";
                                artisticName = ScannerInput.askString();
                                index = comprovaLogin(competitions, artisticName);
                                if (index != -1) {
                                    indexRival = generaParelles(competitions, index);
                                    enterLobby(competitions, batalles, index, indexRival);

                                } else {
                                    System.out.println("Yo'bro, there's no " + artisticName + " in ma' list.\n");
                                }
                                break;
                            case 2:
                                break;
                        }
                    } while (!validOption());
                    break;
                case "after":
                    System.out.println("The winner is...");
                    break;
            }
        } while(!exit());
    }

    private int generaParelles(Root competitions, int index) {
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
        simulaBatalla( parelles);

        return indexRival;
    }



    private void enterLobby(Root competitions, database.battles.Root batalles, int index, int indexRival) {
        int currentPhase = 1, currentBattle = 1;
        String guanyador = null;
        boolean finalitzat = false;
        String tipusBatalla = getTipusBatalla();
        System.out.println(tipusBatalla);
        do {
            if (!finalitzat) {
                System.out.println("------------------------------------------------------------");
                System.out.println("Phase: " + currentPhase + "/" + competitions.getCompetition().getPhase().size() + " | Score: " + competitions.getRappers().get(index).getScore() + " | Battle " + currentBattle + "/2: " + tipusBatalla + " | Rival: " + competitions.getRappers().get(indexRival).getStageName() + "");
                System.out.println("------------------------------------------------------------");
                System.out.println("\n1.Start the battle\n2.Show ranking\n3.Create profile\n4.Leave competition\n");
            } else {
                System.out.println("------------------------------------------------------------");
                if (competitions.getRappers().get(index).getStageName() == guanyador) {
                    System.out.println("Winner: " + guanyador + " | Score: " + competitions.getRappers().get(index).getScore() + " | YOU WIN!!!! ");
                } else {
                    System.out.println("Winner: " + guanyador + " | Score: " + competitions.getRappers().get(index).getScore() + " | You've lost kid, I'm sure you'll do better next time...");

                }
                System.out.println("------------------------------------------------------------");
                System.out.println("\n1.Start the battle (deactivated)\n2.Show ranking\n3.Create profile\n4.Leave competition\n");
            }
            askForOption();
            switch (getOption()) {
                case 1:

                    //aqui generem la batalla i aixo
                    //aixo es per canviar de fase i de batalla
                    System.out.println("skr");
                    if (!finalitzat) {
                        if (currentPhase < competitions.getCompetition().getPhase().size() && currentBattle == 2) {

                            currentPhase++;
                            currentBattle = 1;
                        } else if (currentPhase == competitions.getCompetition().getPhase().size() && currentBattle == 2) {
                            finalitzat = true;
                        } else {
                            //doBattlee();

                            currentBattle++;
                        }
                        tipusBatalla = getTipusBatalla();
                    } else {
                        System.out.println("\nCompetition ended. You cannot battle anyone else!\n");
                    }
                    break;
                case 2:
                    showRanking(competitions, index);
                    break;
                case 3:
                    //aixo es fa a la fase 3 i 4 jej
                    break;
                case 4:
                    break;
            }
        } while (!exit2());
    }

    private void showRanking(Root competitions, int index) {
        LinkedList<Rapper> orderedRappers;
        orderedRappers = competitions.getRappers();
        orderedRappers.sort(Comparator.comparingInt(Rapper::getScore)); //aixo no tinc ni idea de si funciona, lo veremos mas tarde
        for (int i = 0; i < orderedRappers.size(); i++) {
            if (i == index) {
                System.out.println((i+1) + ". " + orderedRappers.get(i).getStageName() + " - " + orderedRappers.get(i).getScore() + " <-- You");
            } else {
                System.out.println((i+1) + ". " + orderedRappers.get(i).getStageName() + " - " + orderedRappers.get(i).getScore());
            }
        }
    }

    private String getTipusBatalla() {
        Random rn = new Random();
        int numero = rn.nextInt(3) + 1;

        switch (numero) {
            case 1:
                tipusBatalla = "sangre";
            case 2:
                tipusBatalla = "acapella";
            case 3:
                tipusBatalla = "escrita";
        }
        return tipusBatalla;
    }

    public void simulaBatalla(String[][] parelles){
        for (int i = 0; i < parelles.length; i++) {
            for (int j = 0; j < parelles[i].length; j++) {
                System.out.print(parelles[i][j] + " ");
            }
            System.out.println();
        }
        //agafem les parelles
        for (int i = 0; i < parelles.length; i++) {
            int primer = generaPrimer();

        }

    }

    public int generaPrimer(){
        int primer = (int) (Math.random()*2);
        return primer;
    }


    public void registre(Root competitions){
        System.out.println("-----------------------------------------");
        System.out.println("Please, enter your personal information:");
        System.out.println("- Full name: ");
        nom = ScannerInput.askString();
        System.out.println("- Artistic name: ");
        artisticName = ScannerInput.askString();
        System.out.println("- Birth date (dd/MM/YYYY): ");
        date = ScannerInput.askString();
        System.out.println("- Country: ");
        country = ScannerInput.askString();
        System.out.println("- Level: ");
        level = ScannerInput.askInteger();
        System.out.println("- Photo URL: ");
        ScannerInput.fflush();
        photo = ScannerInput.askString();
        Rapper rapper = new Rapper(nom, artisticName, date, country, level, photo); //aixo registra un instancia en general o nomes squi? sino s ha de cambiar y fer set?
        competitions.getRappers().add(rapper);
        System.out.println("\nRegistration completed!");
        System.out.println("-----------------------------------------");
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
