package org.teamtree.objectaid.MVC.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import org.teamtree.objectaid.MVC.Model.Model;

/**
 * Classe qui permet de gérer tous les MenuItem de l'application
 */
public class MenuItemController implements EventHandler<ActionEvent> {

    /** Modèle */
    private final Model model;

    /**
     * Constructeur
     * @param model Modèle
     */
    public MenuItemController(Model model) {
        this.model = model;
    }

    /**
     * Méthode qui permet de gérer les MenuItem de l'application
     * @param event Evènement
     */
    @Override
    public void handle(ActionEvent event) {
        switch (((MenuItem)event.getSource()).getText()){
            case "Attributs":
                model.afficherAttributsSelection();
                break;
            case "Méthodes":
                model.afficherMethodesSelection();
                break;
            case "Constructeurs":
                model.afficherConstructeursSelection();
                break;
            case "Cacher la classe":
                model.ajouterClasseCachee(model.getCurrentClickedClass());
                break;
        }
    }
}
