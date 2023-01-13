package org.teamtree.objectaid.MVC.Controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import org.teamtree.objectaid.MVC.Model.Model;

/**
 * Représente la base de tous les contrôleurs de l'application.
 *
 * @param <T> Le type d'événement que le contrôleur doit gérer.
 */
public abstract class ControllerBase<T extends Event> implements EventHandler<T> {

    protected final Model model;

    protected ControllerBase(final Model model) {
        this.model = model;
    }
}
