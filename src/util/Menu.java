package Util;
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
    private int indexRival;



    private String artisticName;

    private String state = null;
    Controller controller = new Controller();
    Rapper rapper = new Rapper();
    Phase phase = new Phase();
    Batalla batalla = new Batalla();
    Competition competition = new Competition();
    Theme theme = new Theme();


    public void printMenu(Root competitions) throws ParseException {
        System.out.println(" ");
        SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
        System.out.println("Welcome to competition: " + competitions.getCompetition().getName());
        System.out.println("Starts on " + competitions.getCompetition().getStartDate());
        System.out.println("Ends on " + competitions.getCompetition().getEndDate());
        System.out.println("Phases: " + competitions.getCompetition().getPhase().size());//maybe aqui es pot posar un phases.size
        System.out.println("Currently: " + competitions.getRappers().size() + " participants\n");
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
                                index = controller.comprovaLogin(competitions, artisticName);
                                if (index != -1) {

                                    indexRival = phase.generaParelles(competitions, index,batalles);
                                    enterLobby(competitions, batalles, index, indexRival);

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


    private void enterLobby(Root competitions, database.battles.Root batalles, int index, int indexRival) {

        String guanyador = null;
        boolean finalitzat = false;




        do {
            if (!finalitzat) {
                System.out.println("------------------------------------------------------------");
                System.out.println("Phase: " + phase.getCurrentPhase() + "/" + competitions.getCompetition().getPhase().size() + " | Score: " + competitions.getRappers().get(index).getScore() + " | Battle " + batalla.getCurrentBattle() + "/2: " + batalla.getTipusBatalla() + " | Rival: " + competitions.getRappers().get(indexRival).getStageName() + "");
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
            controller.askForOption();
            switch (controller.getOption()) {
                case 1:
                    batalla.GeneraTipusBatalla();

                    //aqui generem la batalla i aixo
                    //aixo es per canviar de fase i de batalla
                    System.out.println("skr");
                    if (!finalitzat) {
                        if (phase.getCurrentPhase() < competitions.getCompetition().getPhase().size() && batalla.getCurrentBattle() == 2) {

                            phase.sumaPhase(phase.getCurrentPhase());
                            batalla.setCurrentBattle(1);
                        } else if (phase.getCurrentPhase() == competitions.getCompetition().getPhase().size() && batalla.getCurrentBattle() == 2) {
                            finalitzat = true;
                        } else {
                            //doBattlee();
                            batalla.sumaBatalla(batalla.getCurrentBattle());
                            //batalla.sumaBatalla(batalla.getCurrentBattle());
                        }

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
        } while (!controller.exit2());
    }

    private void showRanking(Root competitions, int index) { //miarar on anva si a rapper o a phase.
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


}
