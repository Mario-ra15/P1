package Util;
import Api.Connexio;
import database.battles.Theme;
import database.competitions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;


public class Menu {

    private int index;
    private int indexShow;
    private String[][] parelles;
    private String pais;
    private String artisticName;
    private String artisticNameShow;
    private String state = null;

    private Controller controller = new Controller();
    private Rapper rapper = new Rapper();
    private Phase phase = new Phase();
    private Batalla batalla = new Batalla();
    private Competition competition = new Competition();
    private Theme theme = new Theme();
    private Connexio connexio = new Connexio();
    private LinkedList<String> competidors = new LinkedList<String>();

    /**
     * Printa el primer menu que trobem
     * @param competitions Inforamción de la competición
     * @throws ParseException exception
     */
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
     * Menu que mira en que fase de la competición nos encontramos
     * @param batalles información de las batallas
     * @param competitions información de las competiciones
     * @throws ParseException exception
     */
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
                        controller.askForOption();
                        switch (controller.getOption()) {
                            case 1:
                                competition.registre(competitions);
                                break;
                            case 2:
                                break;
                        }
                    } while (!controller.validOption());
                    break;
                case "current":
                    do {
                        System.out.println("\nCompetition started. Do you want to:\n\n1.Login\n2.Leave\n");
                        controller.askForOption();
                        switch (controller.getOption()) {
                            case 1:
                                System.out.println("Enter your artistic name:");
                                artisticName = "";
                                artisticName = ScannerInput.askString();
                                index = controller.comprovaLogin(competitions, artisticName); //obtenim el id del nostre participant
                                if (index != -1) {
                                    //omplim la llista de competirdors.
                                    for(int i = 0; i < competitions.getRappers().size(); i++ ){
                                        competidors.add(competitions.getRappers().get(i).getStageName()); //array dels competirdors
                                    }
                                    enterLobby(competitions, batalles, index);

                                } else {
                                    System.out.println("Yo'bro, there's no " + artisticName + " in ma' list.\n");
                                }
                                break;
                            case 2:
                                break;
                        }
                    } while (!controller.validOption());
                    break;
                case "after":
                    System.out.println("The winner is...");
                    break;
            }
        } while(!controller.exit());
    }

    /**
     * Menu donde se hace toda la competición
     * @param competitions Información de los competidores
     * @param batalles Información de las batallas
     * @param index Posición de nuestro participante
     */
    private void enterLobby(Root competitions, database.battles.Root batalles, int index) {
        String rival;
        String guanyador = null;
        boolean finalitzat = false;
        boolean eliminat;
        batalla.generaTipusBatalla(); //obtenim el tipus de la batlla
        parelles = phase.generaParelles(index,competidors); //generem les parelles
        rival = phase.buscarRival(artisticName, parelles); //busquem el nostre rival

        //aqui es simulen les batalles


        do {
            if (!finalitzat) {
                System.out.println("------------------------------------------------------------");
                System.out.println("Phase: " + phase.getCurrentPhase() + "/" + competitions.getCompetition().getPhase().size() + " | Score: " + competitions.getRappers().get(index).getScore() + " | Battle " + batalla.getCurrentBattle() + "/2: " + batalla.getTipusBatalla() + " | Rival: "+ rival);
                System.out.println("------------------------------------------------------------");
                System.out.println("\n1.Start the battle\n2.Show ranking\n3.Create profile\n4.Leave competition\n");
                System.out.println("hi ha "+ competidors.size());
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
            controller.askForOption();
            switch (controller.getOption()) {
                case 1:
                   eliminat = phase.comprovaUsuari(competidors,artisticName);



                    if (!finalitzat) {
                        if (phase.getCurrentPhase() < competitions.getCompetition().getPhase().size() && batalla.getCurrentBattle() == 2) { //batalla 2 phase 1

                                batalla.simulaBatalla(parelles,batalles,competitions,competidors,artisticName);
                                if(eliminat) batalla.doBattle(batalles, competitions, competidors, artisticName, rival);//Aqui poner si hace batalla solo si esta la persona
                                phase.sumaPhase(phase.getCurrentPhase());
                                batalla.setCurrentBattle(1);
                                parelles = phase.generaParelles(index,competidors);
                                batalla.generaTipusBatalla();
                                rival = phase.buscarRival(artisticName, parelles);

                        } else if (phase.getCurrentPhase() == competitions.getCompetition().getPhase().size() && batalla.getCurrentBattle() == 2) { //batalla 2 phase 2

                            guanyador = competidors.get(0);
                            finalitzat = true;

                        } else { //batalla 1 phase 1


                            // Aqui simulem les batalles, sumem els punts y borrem els que perden.
                            if (competidors.size() == 2) { //si arribem a la final nosaltres

                                if (eliminat){
                                    batalla.doBattle(batalles, competitions, competidors, artisticName, rival);
                                } else {
                                    batalla.simulaBatalla(parelles, batalles, competitions, competidors, artisticName);
                                }

                                finalitzat = true;
                                guanyador = competidors.get(0);

                            } else { //mentre no sigui la final de la persona que juga

                                batalla.simulaBatalla(parelles, batalles, competitions, competidors, artisticName);
                                if(eliminat) batalla.doBattle(batalles, competitions, competidors, artisticName, rival);


                                if (competidors.size() == 1) { //si nomes queda 1

                                    finalitzat = true;
                                    guanyador = competidors.get(0);

                                } else {

                                    parelles = phase.generaParelles(index, competidors);
                                    batalla.generaTipusBatalla();
                                    rival = phase.buscarRival(artisticName, parelles);

                                }
                                batalla.sumaBatalla(batalla.getCurrentBattle());
                            }
                        }

                    } else {
                        System.out.println("\nCompetition ended. You cannot battle anyone else!\n");
                    }
                    break;
                case 2:
                    showRanking(competitions, artisticName);
                    break;
                case 3:
                    System.out.println("Enter the name of the rapper:");
                    artisticNameShow = "";
                    artisticNameShow = ScannerInput.askString();
                    indexShow = controller.comprovaUsuari(competitions, artisticNameShow); //obtenim el id del nostre participant
                    if (indexShow != -1) {
                        //Aqiu entre perque existeix el usuari

                       pais = competitions.getRappers().get(indexShow).getNationality();
                        System.out.printf("\nGetting information about their country of origin (%s)...\n\n", pais);


                    } else {
                        System.out.println("Yo'bro, there's no " + artisticNameShow + " in ma' list.\n");
                    }
                    break;
                case 4:
                    break;
            }
        } while (!controller.exit2());
    }

    /**
     * Nos muestra por pantalla el ranking de los participantes
     * @param competitions información de los competidores
     * @param artisticName nombre de nuestro participante
     */
    private void showRanking(Root competitions, String artisticName) { //miarar on anva si a rapper o a phase.
        LinkedList<Rapper> orderedRappers;
        orderedRappers = competitions.getRappers();
        orderedRappers.sort(Comparator.comparingDouble(Rapper::getScore)); //aixo no tinc ni idea de si funciona, lo veremos mas tarde
        Collections.reverse(orderedRappers);
        for (int i = 0; i < orderedRappers.size(); i++) {
            if (orderedRappers.get(i).getStageName().equals(artisticName)) {
                System.out.println((i+1) + ". " + orderedRappers.get(i).getStageName() + " - " + orderedRappers.get(i).getScore() + " <-- You");
            } else {
                System.out.println((i+1) + ". " + orderedRappers.get(i).getStageName() + " - " + orderedRappers.get(i).getScore());
            }
        }
    }


}
