package org.teamtree.objectaid.MVC.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.Service.UmlService;
import org.teamtree.objectaid.Service.SqueletteService;

/**
 * Classe qui permet de gérer le clic sur le menu "Générer"
 */
public class GenererButtonController implements EventHandler<ActionEvent> {

    /**
     * Modèle
     */
    private final Model model;

    /**
     * Constructeur
     *
     * @param model Modèle
     */
    public GenererButtonController(Model model) {
        this.model = model;
    }

    @Override
    public void handle(ActionEvent event) {
        switch (((MenuItem) event.getSource()).getText()) {
            case "Générer squelette" -> {
                SqueletteService squeletteService1 = new SqueletteService();
                squeletteService1.genererSqueletteDiagramme(model.getClasses());
            }
            case "Générer UML" -> {
                UmlService umlService = new UmlService();
                umlService.genererUmltoFile(model.getClasses());
            }
        }
    }
}
