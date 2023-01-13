package org.teamtree.objectaid.MVC.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.MVC.Vue.VueClasseAffichage;

/**
 * Classe qui permet de gérer la modification de la taille d'une classe
 * et de la mettre à jour dans le modèle
 */
public class ListenerModificationTailleClasse implements ChangeListener {

    /**
     * Modèle
     */
    private final Model model;

    /**
     * La vue affichée en relation avec le modèle
     */
    private final VueClasseAffichage vueClasseAffichage;

    /**
     * Constructeur du listener
     *
     * @param model              Modèle
     * @param vueClasseAffichage Vue affichée en relation avec le modèle
     */
    public ListenerModificationTailleClasse(Model model, VueClasseAffichage vueClasseAffichage) {
        this.model = model;
        this.vueClasseAffichage = vueClasseAffichage;
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        model.notifierObservateurFlecheSpecifique(this.vueClasseAffichage);
    }
}
