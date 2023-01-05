package org.teamtree.objectaid.MVC.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import org.teamtree.objectaid.MVC.Model.Model;

public class ControllerButtonGeneral implements EventHandler<ActionEvent> {

    private final Model model;

    public ControllerButtonGeneral(Model model) {
        this.model = model;
    }


    @Override
    public void handle(ActionEvent event) {
        switch (((Button)event.getSource()).getText()){
            case "Afficher les attributs":
                model.afficherAttributs();
                break;
            case "Afficher les méthodes":
                model.afficherMethodes();
                break;
            case "Afficher les constructeurs":
                model.afficherConstructeurs();
                break;
        }
    }
}