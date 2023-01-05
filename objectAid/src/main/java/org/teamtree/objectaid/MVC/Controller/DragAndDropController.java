package org.teamtree.objectaid.MVC.Controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import org.teamtree.objectaid.Classe.ClasseAffichage;
import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.MVC.Model.Model;

/**
 * Classe qui permet de gérer le drag and drop
 */

public class DragAndDropController implements EventHandler<MouseEvent> {

    /** Modèle */
    private Model model;

    /**
     * Constructeur du controller
     * @param model Modèle
     */
    public DragAndDropController(Model model) {
        this.model = model;
    }

    /**
     * Méthode qui permet de gérer le drag and drop
     * @param event Evènement
     */
    @Override
    public void handle(MouseEvent event) {
        ClasseAffichage classe = (ClasseAffichage) event.getSource();
        if (model.getCurrentClickedClass().equals(classe.getNom()) && event.isPrimaryButtonDown()){
            int x = (int) event.getSceneX();
            int y = (int) event.getSceneY() - 25;
            ClasseEntiere classeEntiere = model.getClasse(model.getCurrentClickedClass()).get();
            classeEntiere.setX(x);
            classeEntiere.setY(y);
            model.notifierObservateur();
        }
    }

}
