package org.teamtree.objectaid.Classe.Relations;

import org.teamtree.objectaid.Classe.Attribut;

/**
 * Cette classe représente aggregation entre deux classes.
 */
public class Composition extends Association {

    /**
     * Constructeur de la classe
     * @param depart La classe de départ
     * @param destination La classe de destination
     * @param attribut L'attribut de l'association
     * @param cardinalite1 Cardinalité n°1 de l'association
     * @param cardinalite2 Cardinalité n°2 de l'association
     */
    public Composition(String depart, String destination, Attribut attribut, String cardinalite1, String cardinalite2) {
        super(depart, destination, attribut, cardinalite1, cardinalite2);
    }


    /**
     * Méthode qui retourne l'uml de l'association
     * @return L'uml de l'association
     */
    @Override
    public String getUml() {
        return getDepart() + " *--> " + getDestination() + " : " + attribut.getAccessibilite().getUml() + " " + attribut.getNom();
    }

    @Override
    public String toString() {
        return "Composition : " + getDepart() + " *--> " + getDestination() + " : " + attribut.getAccessibilite().getUml() + " " + attribut.getNom();
    }
}
