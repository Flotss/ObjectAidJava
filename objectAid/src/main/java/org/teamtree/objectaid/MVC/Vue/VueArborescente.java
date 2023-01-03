package org.teamtree.objectaid.MVC.Vue;

import javafx.scene.control.TreeView;
import org.teamtree.objectaid.MVC.Model.Model;

/**
 * Classe qui permet de représenter la vue arborescente
 */
public class VueArborescente extends TreeView implements Observateur {

    /** Modèle */
    private final Model model;

    /**
     * Constructeur de la classe VueArborescente
     * @param model Modèle
     */
    public VueArborescente(final Model model) {
        this.model = model;
    }

    @Override
    public void actualiser() {
        throw new Error("Not yet implemented");
    }
}
