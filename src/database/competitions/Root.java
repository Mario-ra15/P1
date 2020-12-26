package database.competitions;

import database.competitions.Competition;
import database.competitions.Rapper;

import java.util.LinkedList;

public class Root {
    private Competition competition;
    private LinkedList<String> countries;
    private LinkedList<Rapper> rappers;

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public LinkedList<String> getCountries() {
        return countries;
    }

    public void setCountries(LinkedList<String> countries) {
        this.countries = countries;
    }

    public LinkedList<Rapper> getRappers() {
        return rappers;
    }

    public void setRappers(LinkedList<Rapper> rappers) {
        this.rappers = rappers;
    }
}
