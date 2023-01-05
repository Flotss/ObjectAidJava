package org.teamtree.objectaid.MVC.Fleches;

public interface Fleche {
    /**
     * Méthode qui permet de récupérer la classe de départ
     * @return Classe de départ
     */
    String getClasseDepart();

    /**
     * Méthode qui permet de récupérer la classe d'arrivée
     * @return Classe d'arrivée
     */
    String getClasseArrivee();

    /**
     * Méthode qui permet de récupérer le type de flèche
     * @return Type de flèche
     */
    String getTypeFleche();

}
