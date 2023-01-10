package org.teamtree.objectaid.MVC.Controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.MVC.Vue.VueClasseAffichage;

public class ControllerButtonClasseCachee implements EventHandler<MouseEvent> {

    private final Model model;
    private final VueClasseAffichage classe;

    public ControllerButtonClasseCachee(Model model, VueClasseAffichage classe) {
        this.model = model;
        this.classe = classe;
    }

    @Override
    public void handle(MouseEvent event) {
        model.supprimerClasseCachee(classe);
    }
}
