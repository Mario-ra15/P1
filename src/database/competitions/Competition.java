package database.competitions;

import java.util.Date;
import java.util.LinkedList;

public class Competition {
    private String name;
    private Date startDate; //s ha de mirar el format
    private Date endDate;//s ha de mirar el format
    private LinkedList<Phase> phases;

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

}
