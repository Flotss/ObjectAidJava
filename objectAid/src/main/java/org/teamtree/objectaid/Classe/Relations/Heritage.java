package org.teamtree.objectaid.Classe.Relations;

public class Heritage extends Relation {

    public Heritage(String depart, String destination) {
        super(depart, destination);
    }

    @Override
    public String toString() {
        return "Heritage: " + getDepart() + " -> " + getDestination();
    }
}
