package org.teamtree.objectaid.render;

import org.teamtree.objectaid.Fabrique.SceneFactory;
import org.teamtree.objectaid.MVC.Controller.ProjectDirectoryChooserController;
import org.teamtree.objectaid.MVC.Model.Model;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class ApplicationLayoutBootstrapRender implements SceneFactory {

    private final Model model;
    private final BorderPane base;

    public ApplicationLayoutBootstrapRender(final Model model) {
        this.model = model;
        this.base = new BorderPane();

        final var button = new Button("Charger un projet");

        button.setOnAction(new ProjectDirectoryChooserController(model));

        base.setCenter(button);
    }

    public Scene getScene() {
        return new Scene(base, 800, 600);
    }
}
