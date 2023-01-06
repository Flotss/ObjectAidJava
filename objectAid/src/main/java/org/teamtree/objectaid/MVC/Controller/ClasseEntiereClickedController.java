package org.teamtree.objectaid.MVC.Controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import org.teamtree.objectaid.MVC.Vue.VueClasseAffichage;
import org.teamtree.objectaid.MVC.Model.Model;

/**
 * Classe qui permet de gérer le clic sur une classe
 */
public class ClasseEntiereClickedController implements EventHandler<MouseEvent> {

    /** Modèle */
    private final Model model;

    /**
     * Constructeur
     * @param model Modèle
     */
    public ClasseEntiereClickedController(final Model model) {
        this.model = model;
    }

    @Override
    public void handle(MouseEvent event) {
        final var source = (VueClasseAffichage) event.getSource();

        addClickedEffect(source);
    }

    /**
     * Méthode qui permet d'affecter le classe en tant que classe sélectionnée
     * @param source Classe cliquée
     */
    private void addClickedEffect(final VueClasseAffichage source) {
        model.setCurrentClickedClass(source);
        model.setBarreBoutonsSpecifique();
        model.notifierObservateur("barre");
    }
}
