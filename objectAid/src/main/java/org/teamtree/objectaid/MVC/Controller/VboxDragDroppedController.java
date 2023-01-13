package org.teamtree.objectaid.MVC.Controller;

import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.MVC.Vue.VueListeClasse;

import javafx.scene.input.DragEvent;

/**
 * Représente le controller qui gère le drag and drop sur la liste des classes.
 * C'est-à-dire quand on détecte un drop dans le vbox.
 */
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

            // Si on trouve le packet, on l'ajoute à la liste des classes en veillant à éviter les doublons dans la VueClasse
            entrySearch.ifPresent(
                stringClassEntry -> model.getClasse(itemContent).ifPresentOrElse(classe -> {
                }, () -> {
                    final var classeEntiere = new ClasseEntiere(stringClassEntry.getValue());

                    this.model.ajouterClasse(classeEntiere, (int) event.getX(), (int) event.getY() - 25);
                    this.model.notifierObservateur(VueListeClasse.class);
                }));
        }

        event.setDropCompleted(success);
        event.consume();
    }
}
