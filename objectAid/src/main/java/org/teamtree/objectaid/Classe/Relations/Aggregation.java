package org.teamtree.objectaid.Classe.Relations;

import org.teamtree.objectaid.Classe.Attribut;

/**
 * Cette classe représente aggregation entre deux classes.
 */
public class Aggregation extends Association {

    /**
     * Constructeur de la classe
     * @param depart La classe de départ
     * @param destination La classe de destination
     * @param attribut L'attribut de l'association
     * @param cardinalite1 Cardinalité n°1 de l'association
     * @param cardinalite2 Cardinalité n°2 de l'association
     */
    public Aggregation(String depart, String destination, Attribut attribut, String cardinalite1, String cardinalite2) {
        super(depart, destination, attribut, cardinalite1, cardinalite2);
    }
}
