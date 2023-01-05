package org.teamtree.objectaid.Classe.Relations;

public class Association extends Relation {
    private final String nomAttribut;
    private final String type;
    private final String cardinalite1;
    private final String cardinalite2;

    public Association(String depart, String destination, String attribut, String type, String cardinalite1, String cardinalite2) {
        super(depart, destination);
        this.nomAttribut = attribut;
        this.type = type;
        this.cardinalite1 = cardinalite1;
        this.cardinalite2 = cardinalite2;
    }

    public String getNomAttribut() {
        return nomAttribut;
    }

    public String getType(){
        return type;
    }

    public String getCardinalite1() {
        return cardinalite1;
    }

    public String getCardinalite2() {
        return cardinalite2;
    }

    @Override
    public String toString() {
        return "Association: " + getDepart() + " -> " + getDestination() + " (" + getType() + ")" + " " + getNomAttribut();
        //+ " (" + cardinalite1 + " - " + cardinalite2 + ")"; // TODO: Add cardinalities
    }
}

