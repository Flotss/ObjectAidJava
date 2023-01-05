package org.teamtree.objectaid.render;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import org.teamtree.objectaid.Fabrique.SceneFactory;
import org.teamtree.objectaid.MVC.Model.ApplicationState;
import org.teamtree.objectaid.MVC.Model.Model;

public class ApplicationLayoutBootstrapRender implements SceneFactory {
    private final Model model;
    private final BorderPane base;

    public ApplicationLayoutBootstrapRender(Model model) {
        this.model = model;
        this.base = new BorderPane();

        final var button = new Button("Charger un projet");
        button.setOnAction(event -> model.setApplicationState(ApplicationState.PROJECT_LOADED));

        base.setCenter(button);
    }

    public Scene getScene() {
        return new Scene(base, 1920, 1080);
    }
}
