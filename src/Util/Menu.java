package Util;
import Batallas.Rappers;

import java.util.*;

public class Menu {
    private String opcio;
    private Scanner scanner;
    private Rappers rappers;
    private String nom;
    private String artisticname;
    private String date;
    private String country;
    private int level;
    private String photo;


    public Menu(){
        scanner = new Scanner(System.in);


    }

    public void printMenu1() {
        System.out.println(" ");
        System.out.println("Welcome to competition:" + "competition.getname");
        System.out.println("Starts on " + "competition.startDate");
        System.out.println("Ends on "+"competition.endDate");
        System.out.println("Phases: 3");//maybe aqui es pot posar un phases.size
        System.out.println("Currently: "+ "rappers.size\n");
        System.out.println("Competition"+"booleano"+"Do you want to:\n");
        System.out.println("1.Register");//maybe aqui es pot posar un phases.size
        System.out.println("2.Leave\n");
    }
    public void demanaOpcio(){
        System.out.println("Choose an option:\n");
        opcio = scanner.next();
    }
    public String getOpcio() {
        return opcio;
    }
    public void optionMenu(){
        do{
            printMenu1();
            demanaOpcio();
            switch (getOpcio()){
                case "1":
                    registre();
                    break;

                case "2":
                    break;

                default:
            }
        }while(!getOpcio().equals("2"));
    }
    public void registre(){
        System.out.println("Please, enter your personal information:");
        System.out.println("-Full name:");
        nom = scanner.next();
        System.out.println("Artistic name:");
        artisticname = scanner.next();
        System.out.println("Birth date (dd/MM/YYYY):");
        date = scanner.next();
        System.out.println("Country");
        country = scanner.next();
        System.out.println("Level");
        level = Integer.parseInt(scanner.next());
        System.out.println("Photo URL");
        photo = scanner.next();
        System.out.println("");
        Rappers raper = new Rappers(nom,artisticname,date,country,level,photo); //aixo registra un instancia en general o nomes squi? sino s ha de cambiar y fer set?
        System.out.println("Registration Completed");

    }


}
