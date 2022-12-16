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


        Scene scene = new Scene(vue, 1024, 768);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws ClassNotFoundException {
        launch();
    }
}