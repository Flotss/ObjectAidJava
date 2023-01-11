package org.teamtree.objectaid.Classe.Relations;

public class Heritage extends Relation {

    /**
     * Constructeur de la classe
     * @param depart La classe de départ
     * @param destination La classe de destination
     */
    public Heritage(String depart, String destination) {
        super(depart, destination);
    }

    /**
     * Méthode d'affichage de la relation
     */
    @Override
    public String toString() {
        return "Heritage: " + getDepart() + " -> " + getDestination();
    }

    /**
     * Méthode qui retourne l'uml de la relation
     * @return L'uml de la relation
     */
    @Override
    public String getUml() {
        return getDestination() + " <|-- " + getDepart();
    }
}
