package Util;
import com.google.gson.stream.JsonToken;

import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Float.parseFloat;

public class Menu {



    public void printMenuUsuari() {
        System.out.println(" ");
        System.out.println("Welcome to competition:" + "competition.getname");
        System.out.println("Starts on " + "competition.startDate");
        System.out.println("Ends on "+"competition.endDate");
        System.out.println("Phases: 3");//maybe aqui es pot posar un phases.size
        System.out.println("Currently: "+ "rappers.size\n");
        System.out.println("Competition"+"booleano"+"Do you want to:\n");
    }

}
