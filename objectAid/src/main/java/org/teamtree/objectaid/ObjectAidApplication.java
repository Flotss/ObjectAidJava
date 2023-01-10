package org.teamtree.objectaid;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.teamtree.objectaid.MVC.Controller.ControllerButton;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.MVC.Vue.ApplicationLayoutView;

import java.io.IOException;

public class ObjectAidApplication extends Application {
    public static void main(String[] args) throws ClassNotFoundException {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        Model model = new Model();
        VBox applicationLayout = new VBox();
/**
 ClasseEntiere c = new ClasseEntiere("org.teamtree.objectaid.Classe.ClasseEntiere");
 model.ajouterClasse(c);

 ClasseEntiere c1 = new ClasseEntiere("org.teamtree.objectaid.Classe.Attribut");
 model.ajouterClasse(c1);

 ClasseEntiere c2 = new ClasseEntiere("org.teamtree.objectaid.Classe.Constructeur");
 model.ajouterClasse(c2);

 ClasseEntiere c3 = new ClasseEntiere("org.teamtree.objectaid.Classe.Methode");
 model.ajouterClasse(c3);

 ClasseEntiere c4 = new ClasseEntiere("org.teamtree.objectaid.Classe.Parametre");
 model.ajouterClasse(c4);

 ClasseEntiere c5 = new ClasseEntiere("org.teamtree.objectaid.Classe.DefinitionClasse");
 model.ajouterClasse(c5);

 ClasseEntiere c6 = new ClasseEntiere("org.teamtree.objectaid.Entite.Entite");
 model.ajouterClasse(c6);

 ClasseEntiere c7 = new ClasseEntiere("org.teamtree.objectaid.Entite.Classe");
 model.ajouterClasse(c7);

 ClasseEntiere c8 = new ClasseEntiere("org.teamtree.objectaid.Entite.Interface");
 model.ajouterClasse(c8);


 VueClasse vue = new VueClasse(model);
 model.ajouterObservateur(vue);
 */
        HBox buttonBar = new HBox();
        ControllerButton controllerButton = new ControllerButton(model);
        final var attributesDisplayButton = new Button("Afficher les attributs");
        attributesDisplayButton.setOnAction(controllerButton);

        final var methodsDisplayButton = new Button("Afficher les méthodes");
        methodsDisplayButton.setOnAction(controllerButton);

        final var constructorsDisplayButton = new Button("Afficher les constructeurs");
        constructorsDisplayButton.setOnAction(controllerButton);

        buttonBar.getChildren().addAll(attributesDisplayButton, methodsDisplayButton, constructorsDisplayButton);

        //applicationLayout.getChildren().addAll(buttonBar);

        final var applicationLayoutView = new ApplicationLayoutView(model, stage);
        model.ajouterObservateur(applicationLayoutView);

        applicationLayoutView.run();
    }
}