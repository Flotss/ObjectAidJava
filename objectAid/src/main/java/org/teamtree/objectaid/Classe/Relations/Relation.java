package org.teamtree.objectaid.Classe.Relations;

public abstract class Relation {
    private final String depart;
    private final String destination;

    public Relation(String depart, String destination) {
        this.depart = depart;
        this.destination = destination;
    }

    public String getDepart(){
        return depart;
    }

    public String getDestination() {
        return destination;
    }

    public abstract String toString();
}
