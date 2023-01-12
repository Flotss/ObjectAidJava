package org.teamtree.objectaid.Classe.Relations;

/**
 * Cette classe représente une relation entre deux classes.
 */
public abstract class Relation {
    /**
     * La classe de départ
     */
    private final String depart;

    /**
     * La classe de destination
     */
    private final String destination;

    /**
     * Constructeur de la classe
     * @param depart La classe de départ
     * @param destination La classe de destination
     */
    public Relation(String depart, String destination) {
        this.depart = depart;
        this.destination = destination;
    }

    /**
     * Getter de la classe de départ
     * @return La classe de départ
     */
    public String getDepart(){
        return depart;
    }

    /**
     * Getter de la classe de destination
     * @return La classe de destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Méthode d'affichage de la relation
     */
    public abstract String toString();


    /**
     * Méthode qui retourne l'uml de la relation
     * @return L'uml de la relation
     */
    public abstract String getUml();
}
