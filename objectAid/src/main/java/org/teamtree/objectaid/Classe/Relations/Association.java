package org.teamtree.objectaid.Classe.Relations;

public class Association extends Relation {
    private String nom;
    private String cardinalite1;
    private String cardinalite2;

    public Association(String nom, String cardinalite1, String cardinalite2) {
        super(nom);
        this.cardinalite1 = cardinalite1;
        this.cardinalite2 = cardinalite2;
    }

    public String getCardinalite1() {
        return cardinalite1;
    }

    public String getCardinalite2() {
        return cardinalite2;
    }

    @Override
    public String toString() {
        return "Association: " + getNom(); //+ " (" + cardinalite1 + " - " + cardinalite2 + ")"; // TODO: Add cardinalities
    }
}

