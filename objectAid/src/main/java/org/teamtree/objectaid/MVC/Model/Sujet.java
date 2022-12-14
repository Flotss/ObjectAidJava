package org.teamtree.objectaid.MVC.Model;

import org.teamtree.objectaid.MVC.Vue.Observateur;

interface Sujet {
    void ajouterObservateur(Observateur o);
    void supprimerObservateur(Observateur o);
    void notifierObservateur();
}
