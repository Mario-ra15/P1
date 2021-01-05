package Util;
import database.competitions.Rapper;
import database.competitions.Root;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class Menu {
    private static final int MIN = 1;
    private static final int MAX = 2;
    
    private int opcio;
    private String aux;

    private String nom;
    private String artisticName;
    private String date;
    private String country;
    private int level;
    private String photo;
    private String state = null;
    private Rapper rapper;
    private Boolean error;

    public void printMenu(Root competitions) throws ParseException {
        System.out.println(" ");
        SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
        System.out.println("Welcome to competition: " + competitions.getCompetition().getName());
        System.out.println("Starts on " + competitions.getCompetition().getStartDate());
        System.out.println("Ends on " + competitions.getCompetition().getEndDate());
        System.out.println("Phases: " + competitions.getCompetition().getPhase().size());//maybe aqui es pot posar un phases.size
        System.out.println("Currently: " + competitions.getRappers().size() + " participants\n");
    }

    public void printCompMenu(Root competitions) throws ParseException{
        System.out.println("");
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.print("Phase: " + "X | ");
        System.out.print("Score: " + "X | ");
        System.out.print("Battle " + "X : " + "X |");
        System.out.print("Rival: " + "X\n");
        System.out.println("----------------------------------------------------------------------------------------------------\n\n");
        System.out.println("1.Start the battle");
        System.out.println("2.Show ranking");
        System.out.println("3.Create profile");
        System.out.println("Leave competition\n");
    }

    /**
     * mira si la opcio es la de sortir
     * @return boolea que confirma si surt o no
     */
    public boolean exit() {
        return opcio == MAX;
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
                    System.out.println("Competition started. Do you want to:\n\n1.Login\n2.Leave\n");
                    do {
                        askForOption();
                        switch (getOption()) {
                            case 1:

                                System.out.println("Enter your artistic name:");
                                artisticName = "";
                                artisticName = ScannerInput.askString(); //es pot fer servir la mateixa variable no? el regsitre aqui ya no el fem servir, potsre inicialitzar a 0 abans?
                                error = comprovaLogin(competitions, artisticName);
                                if (error) Startmenu(batalles, competitions);
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
        }while (!exit());
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
    public boolean comprovaLogin(Root competitions, String artisticName) {

        for(int i =0; i < competitions.getRappers().size(); i++){
                if (competitions.getRappers().get(i).getStageName().equals(artisticName)) {

                    return true;
                }
        }
        System.out.println("Yo'bro, there's no " + artisticName +" in ma' list.\n");
        return false;
    }

    public void Startmenu (database.battles.Root batalles, Root competitions) throws ParseException{
        printCompMenu(competitions);
        do {
            askForOption();
            switch (getOption()) {
                case 1:

                    System.out.println("Empieza la battle");
                    break;

                case 2:
                    System.out.println("Quieres ver el ranking");
                    break;

                case 3:
                    System.out.println("Quieres crear perfil");
                    break;

                case 4:
                    break;
            }

        } while (!validOption());


    }
}
