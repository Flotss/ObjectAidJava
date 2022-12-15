package org.teamtree.objectaid.MVC.Vue;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.teamtree.objectaid.MVC.Model.Model;

public class VueClasse extends VBox implements Observateur {

    private final Model model;

    public VueClasse(final Model mod) {

        model = mod;

        final var title = new Label("Class");
        final var line = new Label("________________________");
        final var name = new Label("Name");
        final var attributes = new Label("Attributes");
        final var methods = new Label("Methods");

        getChildren().addAll(title, line, name, attributes, methods);

        setStyle("-fx-border-color: black; -fx-border-width: 1px;");
    }

    @Override
    public void actualiser() {
        throw new Error("Not yet implemented");
    }
}
