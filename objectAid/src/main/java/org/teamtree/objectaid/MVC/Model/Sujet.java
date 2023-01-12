package org.teamtree.objectaid.MVC.Model;

import org.teamtree.objectaid.MVC.Vue.Observateur;

/**
 * Interface qui permet d'utiliser le pattern MVC
 * Et donc le patron de conception Observateur
 */
interface Sujet {

    /**
     * Méthode qui permet d'ajouter une observateur
     * @param o L'observateur à ajouter
     */
    void ajouterObservateur(Observateur o);

    /**
     * Méthode qui permet de supprimer un observateur
     * @param o L'observateur à supprimer
     */
    void supprimerObservateur(Observateur o);

    /**
     * Méthode qui permet d'actualiser les observateurs
     */
    void notifierObservateur();
}
