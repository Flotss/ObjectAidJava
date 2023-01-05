package org.teamtree.objectaid.Classe.Relations;

public class Implementation extends Relation {

    public Implementation(String depart, String destination) {
        super(depart, destination);
    }

    @Override
    public String toString() {
        return "Implementation: " + getDepart() + " -> " + getDestination();
    }
}
