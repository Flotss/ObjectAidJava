package org.teamtree.objectaid.Etat;

/**
 * Classe qui représente l'état
 * Exemple : Abstract, Final, Static
 */
public interface Etat {

    /**
     * Méthode qui retourne l'état
     *
     * @return L'état
     */
    String getEtat();


    /**
     * Méthode qui retourne la forme UML de l'état
     *
     * @return La forme UML de l'état
     */
    String getUml();
}
