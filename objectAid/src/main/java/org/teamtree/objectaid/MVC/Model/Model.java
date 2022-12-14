package org.teamtree.objectaid.MVC.Model;

import org.teamtree.objectaid.MVC.Vue.Observateur;

import java.util.HashSet;
import java.util.Set;

public class Model implements Sujet{
    private final Set<Observateur> observateurs;

    public Model() {
        this.observateurs = new HashSet<>();
    }


    public void ajouterObservateur(Observateur o) {
        observateurs.add(o);
    }

    public void supprimerObservateur(Observateur o) {
        observateurs.remove(o);
    }

    public void notifierObservateur() {
        for (Observateur o : observateurs) {
            o.actualiser();
        }
    }
}
