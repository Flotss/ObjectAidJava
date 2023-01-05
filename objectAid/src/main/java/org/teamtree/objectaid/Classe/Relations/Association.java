package org.teamtree.objectaid.Classe.Relations;

import org.teamtree.objectaid.Classe.Attribut;

public class Association extends Relation {
    private final Attribut attribut;
    private final String cardinalite1;
    private final String cardinalite2;

    public Association(String depart, String destination, Attribut attr, String cardinalite1, String cardinalite2) {
        super(depart, destination);
        this.attribut = attr;
        this.cardinalite1 = cardinalite1;
        this.cardinalite2 = cardinalite2;
    }

    public Attribut getAttribut() {
        return attribut;
    }

    public String getCardinalite1() {
        return cardinalite1;
    }

    public String getCardinalite2() {
        return cardinalite2;
    }

    @Override
    public String toString() {
        return "Association: " + getDepart() + " -> " + getDestination() + " (" + attribut.getType() + ")" + " " + attribut.getNom();
        //+ " (" + cardinalite1 + " - " + cardinalite2 + ")"; // TODO: Add cardinalities
    }
}

