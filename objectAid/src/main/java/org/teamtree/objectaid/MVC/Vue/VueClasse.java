package org.teamtree.objectaid.MVC.Vue;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VueClasse extends VBox implements Observateur {

    public VueClasse() {
        final var title = new Label("Class");
        getChildren().add(title);
        final var line = new Label("________________________");
        getChildren().add(line);
        final var name = new Label("Name");
        getChildren().add(name);
        final var attributes = new Label("Attributes");
        getChildren().add(attributes);
        final var methods = new Label("Methods");
        getChildren().add(methods);

        setStyle("-fx-border-color: black; -fx-border-width: 1px;");
    }

    @Override
    public void actualiser() {
        throw new Error("Not yet implemented");
    }
}
