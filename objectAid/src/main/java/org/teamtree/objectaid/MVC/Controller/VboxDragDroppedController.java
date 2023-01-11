package org.teamtree.objectaid.MVC.Controller;

import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.MVC.Vue.VueClasse;

import javafx.scene.input.DragEvent;

public class VboxDragDroppedController extends ControllerBase<DragEvent> {

    public VboxDragDroppedController(final Model model) {
        super(model);
    }

    /**
     * Invoked when a specific event of the type for which this handler is
     * registered happens.
     *
     * @param event the event which occurred
     */
    @Override
    public void handle(final DragEvent event) {
        final var dragBoard = event.getDragboard();
        var success = false;

        if (dragBoard.hasString()) {
            final var itemContent = dragBoard.getString();
            success = true;

            final var entrySearch = this.model.getClassesPath().entrySet().stream()
                .filter(entry -> entry.getKey().equals(itemContent)).findFirst();

            if (entrySearch.isPresent()) {
                final var classeEntiere = new ClasseEntiere(entrySearch.get().getValue());
                this.model.ajouterClasse(classeEntiere);
                new VueClasse(model);
            }
        }

        event.setDropCompleted(success);
        event.consume();
    }
}
