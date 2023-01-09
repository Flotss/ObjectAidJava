package org.teamtree.objectaid.MVC.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.MVC.Vue.VueButtonBarClasse;

public class MenuItemController implements EventHandler<ActionEvent> {

    private Model model;

    public MenuItemController(Model model) {
        this.model = model;
    }

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
        }

    }
}
