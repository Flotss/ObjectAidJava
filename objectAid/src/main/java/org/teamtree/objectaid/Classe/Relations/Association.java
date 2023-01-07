package org.teamtree.objectaid.Classe.Relations;

import org.teamtree.objectaid.Classe.Attribut;

/**
 * Cette classe représente une association entre deux classes.
 */
public class Association extends Relation {

    /**
     * L'attribut de l'association
     */
    private final Attribut attribut;

    /**
     * Cardinalité n°1 de l'association
     */
    private final String cardinalite1;

    /**
     * Cardinalité n°2 de l'association
     */
    private final String cardinalite2;

    /**
     * Constructeur de la classe
     * @param depart La classe de départ
     * @param destination La classe de destination
     * @param attribut L'attribut de l'association
     * @param cardinalite1 Cardinalité n°1 de l'association
     * @param cardinalite2 Cardinalité n°2 de l'association
     */
    public Association(String depart, String destination, Attribut attribut, String cardinalite1, String cardinalite2) {
        super(depart, destination);
        this.attribut = attribut;
        this.cardinalite1 = cardinalite1;
        this.cardinalite2 = cardinalite2;
    }

    /**
     * Getter de l'attribut de l'association
     * @return L'attribut de l'association
     */
    public Attribut getAttribut() {
        return attribut;
    }

    /**
     * Getter de la cardinalité n°1 de l'association
     * @return La cardinalité n°1 de l'association
     */
    public String getCardinalite1() {
        return cardinalite1;
    }

    /**
     * Getter de la cardinalité n°2 de l'association
     * @return La cardinalité n°2 de l'association
     */
    public String getCardinalite2() {
        return cardinalite2;
    }

    /**
     * Méthode d'affichage de l'association
     */
    @Override
    public String toString() {
        return "Association: " + getDepart() + " -> " + getDestination() + " (" + attribut.getType() + ")" + " " + attribut.getNom();
        //+ " (" + cardinalite1 + " - " + cardinalite2 + ")"; // TODO: Add cardinalities
    }
}

