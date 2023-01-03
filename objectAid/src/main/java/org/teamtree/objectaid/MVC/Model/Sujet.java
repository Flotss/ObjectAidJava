package org.teamtree.objectaid.MVC.Model;

import org.teamtree.objectaid.MVC.Vue.Observateur;

/**
 * Interface qui permet d'utiliser le pattern MVC
 * Et donc le patron de conception Observateur
 */
interface Sujet {
    void ajouterObservateur(Observateur o);
    void supprimerObservateur(Observateur o);
    void notifierObservateur();
}
