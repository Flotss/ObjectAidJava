package org.teamtree.objectaid.MVC.Vue;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.render.ApplicationLayoutBootstrapRender;

public class ApplicationLayoutView extends Pane implements Observateur {

    private final Model model;
    private final Stage stage;

    public ApplicationLayoutView(Model model, Stage stage) {
        this.model = model;
        this.stage = stage;

        actualiser();
    }


    /**
     * Méthode qui permet de mettre à jour l'observateur
     */
    @Override
    public void actualiser() {

        this.getChildren().clear();

        switch (model.getApplicationState()) {
            case BOOTSTRAP -> stage.setScene(new ApplicationLayoutBootstrapRender(model).getScene());
            case PROJECT_LOADED -> stage.setScene(new ApplicationLayoutProjectLoadedRender(model).getScene());
        }
    }

    public void run() {
        final var scene = switch(model.getApplicationState()) {
            case BOOTSTRAP -> new ApplicationLayoutBootstrapRender(model);
            case PROJECT_LOADED -> new ApplicationLayoutProjectLoadedRender(model);
        };

        stage.setScene(scene.getScene());
        stage.show();
    }
}
