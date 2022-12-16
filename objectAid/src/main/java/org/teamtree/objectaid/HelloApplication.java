package org.teamtree.objectaid;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.MVC.Vue.VueClasse;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        Model model = new Model();

        ClasseEntiere c = new ClasseEntiere("org.teamtree.objectaid.Classe.ClasseEntiere");
        model.ajouterClasse(c);

        VueClasse vue = new VueClasse(model);
        System.out.println("ok");
        model.ajouterObservateur(vue);


        Scene scene = new Scene(vue, 1024, 768);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws ClassNotFoundException {
        launch();
    }
}