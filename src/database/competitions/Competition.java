package database.competitions;

import Util.ScannerInput;

import java.util.Date;
import java.util.LinkedList;

public class Competition {
    private String name;
    private Date startDate;
    private Date endDate;
    private LinkedList<Phase> phases;
    private String nom;
    private String artisticName;
    private String date;
    private String country;
    private int level;
    private String photo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public LinkedList<Phase> getPhase() {
        return phases;
    }

    public void setPhase(LinkedList<Phase> phase) {
        this.phases = phase;
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

}
