package org.teamtree.objectaid.render;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import org.teamtree.objectaid.Fabrique.SceneFactory;
import org.teamtree.objectaid.MVC.Model.Model;

public class ApplicationLayoutBootstrapRender implements SceneFactory {
    private final Model model;
    private final BorderPane base;

    public ApplicationLayoutBootstrapRender(Model model) {
        this.model = model;
        this.base = new BorderPane();

        final var button = new Button("Charger un projet");

        //TODO: to be removed, replace by a controller
        button.setOnAction(event -> {
            final var fileChooser = new DirectoryChooser();
            fileChooser.setTitle("Ouvrir un projet");

            final var file = fileChooser.showDialog(null);
            model.setCurrentProject(file);
        });

        base.setCenter(button);
    }

    public Scene getScene() {
        return new Scene(base, 1920, 1080);
    }
}
