package org.teamtree.objectaid.MVC.Vue;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import org.teamtree.objectaid.ClasseEntiere;
import org.teamtree.objectaid.MVC.Model.Model;

public class VueClasse extends VBox implements Observateur {

    private final Model model;
    private final ClasseEntiere classeEntiere;

    public VueClasse(final Model mod, final ClasseEntiere dto) {

        model = mod;
        classeEntiere = dto;

        final var title = new Label("Class");
        final var separator = new Rectangle(0, 0, 100, 1);
        final var attributes = new Label("Attributes");
        final var methodSeparator = new Rectangle(0, 0, 100, 1);
        final var methods = new Label("Methods");

        getChildren().addAll(title, separator, attributes, methodSeparator, methods);

        setStyle("-fx-border-color: black; -fx-border-width: 1px;");
    }

    @Override
    public void actualiser() {
        throw new Error("Not yet implemented");
    }
}
