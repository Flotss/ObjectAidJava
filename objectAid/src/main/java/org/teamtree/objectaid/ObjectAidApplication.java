package org.teamtree.objectaid;

import javafx.application.Application;
import javafx.stage.Stage;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.MVC.Vue.ApplicationLayoutView;


/**
 * Classe principale de l'application
 */
public class ObjectAidApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        Model model = new Model();

        final var applicationLayoutView = new ApplicationLayoutView(model, stage);
        model.ajouterObservateur(applicationLayoutView);

        applicationLayoutView.run();
    }
}