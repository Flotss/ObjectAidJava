package org.teamtree.objectaid.MVC.Controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import org.teamtree.objectaid.Classe.ClasseAffichage;
import org.teamtree.objectaid.MVC.Model.Model;

public class ClasseEntiereClickedController implements EventHandler<MouseEvent> {

    private final Model model;

    public ClasseEntiereClickedController(final Model model) {
        this.model = model;
    }

    @Override
    public void handle(MouseEvent event) {
        final var source = (ClasseAffichage) event.getSource();

        addClickedEffect(source);
    }

    private void addClickedEffect(final ClasseAffichage source) {
        model.setCurrentClickedClass(source.getNom());

        model.notifierObservateur();
    }
}
