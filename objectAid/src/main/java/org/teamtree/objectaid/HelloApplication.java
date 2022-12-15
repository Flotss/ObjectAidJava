package org.teamtree.objectaid;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.MVC.Vue.VueClasse;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        final var pane = new Pane();
        final var scene = new Scene(pane);
        final var model = new Model();
        final var sampleClassEntiere = new ClasseEntiere("foo");
        final var classDiagram = new VueClasse(model, sampleClassEntiere);

        pane.getChildren().add(classDiagram);

        stage.setWidth(1024);
        stage.setHeight(768);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}