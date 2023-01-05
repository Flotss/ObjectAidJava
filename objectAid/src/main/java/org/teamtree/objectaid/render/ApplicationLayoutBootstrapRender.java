package org.teamtree.objectaid.render;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import org.teamtree.objectaid.MVC.Model.Model;

public class ApplicationLayoutBootstrapRender {
    private final Model model;
    private final BorderPane base;

    public ApplicationLayoutBootstrapRender(Model model) {
        this.model = model;
        this.base = new BorderPane();

        final var button = new Button("Charger un projet");
        base.setCenter(button);
    }

    public Scene getScene() {
        return new Scene(base, 1920, 1080);
    }
}
