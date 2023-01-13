package org.teamtree.objectaid.MVC.Controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.MVC.Vue.VueClasseAffichage;

/**
 * Classe qui permet de gérer le drag and drop
 */

public class DeplacementClasseDragAndDropController implements EventHandler<MouseEvent> {

    /**
     * Modèle
     */
    private final Model model;

    /**
     * Constructeur du controller
     *
     * @param model Modèle
     */
    public DeplacementClasseDragAndDropController(Model model) {
        this.model = model;
    }

    /**
     * Méthode qui permet de gérer le drag and drop
     *
     * @param event Evènement
     */
    @Override
    public void handle(MouseEvent event) {
        VueClasseAffichage classe = (VueClasseAffichage) event.getSource();
        if (model.getCurrentClickedClass() != null) {
            if (model.getCurrentClickedClass().getNom().equals(classe.getNom()) && event.isPrimaryButtonDown()) {
                int x = (int) event.getSceneX() - 250;
                int y = (int) event.getSceneY() - 25;
                model.deplacerClasse(x, y);
            }
        }
    }

}
