package org.teamtree.objectaid;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.MVC.Controller.ControllerButtonGeneral;
import org.teamtree.objectaid.MVC.Controller.GenererButtonController;
import org.teamtree.objectaid.MVC.Controller.MenuItemController;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.MVC.Vue.VueClasse;
import org.teamtree.objectaid.MVC.Vue.VueContextMenuClasse;


/**
 * Classe principale de l'application
 */
public class ObjectAidApplication extends Application {

    public static final MenuBar menuBar = new MenuBar();

    /**
     * Methode qui permet de lancer l'application
     */
    @Override
    public void start(Stage stage) throws ClassNotFoundException {
        Model model = new Model();
        VBox applicationLayout = new VBox();

        VueClasse vueClass = new VueClasse(model); // La vue se rajoute elle-même au modèle
        model.ajouterObservateur(vueClass);

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

//        ClasseEntiere c9 = new ClasseEntiere("org.teamtree.objectaid.Etat.Etat");
//        model.ajouterClasse(c9);

//        VueFleche vueFleche = new VueFleche(model);
//        model.ajouterObservateur(vueFleche);


        Menu menuItem = new Menu("Afficher/cacher");
        Menu listeClasse = new Menu("Liste des classes");
        MenuItem menuItem2 = new MenuItem("Constructeurs");
        menuItem2.setOnAction(new ControllerButtonGeneral(model));
        MenuItem menuItem3 = new MenuItem("Attributs");
        menuItem3.setOnAction(new ControllerButtonGeneral(model));
        MenuItem menuItem4 = new MenuItem("Methodes");
        menuItem4.setOnAction(new ControllerButtonGeneral(model));
        MenuItem menuItem5 = new MenuItem("Relations");
        menuItem5.setOnAction(new ControllerButtonGeneral(model));

        Menu supprimer_les_classes = new Menu("Supprimer les classes");
        MenuItem supprimerClasses = new MenuItem("Supprimer les classes");
        supprimerClasses.setOnAction(new ControllerButtonGeneral(model));
        supprimer_les_classes.getItems().add(supprimerClasses);

        menuItem.getItems().addAll(menuItem2, menuItem3, menuItem4, menuItem5);

        for (ClasseEntiere ce: model.getClasses()) {
            Menu nomClasseMenu = new Menu(ce.getClasseAffichage().getNom());
            MenuItem afficherCacherClasse = new MenuItem("Afficher/Cacher");
            afficherCacherClasse.setOnAction(new MenuItemController(model));
            MenuItem supprimerClasse = new MenuItem("Supprimer");
            supprimerClasse.setOnAction(new MenuItemController(model));
            nomClasseMenu.getItems().addAll(afficherCacherClasse, supprimerClasse);
            listeClasse.getItems().add(nomClasseMenu);
        }

        GenererButtonController genererButtonController = new GenererButtonController(model);
        Menu generer = new Menu("Générer");
        MenuItem genererSquelette = new MenuItem("Générer squelette");
        genererSquelette.setOnAction(genererButtonController);
        MenuItem genererUml = new MenuItem("Générer UML");
        genererUml.setOnAction(genererButtonController);
        MenuItem genererCompilation = new MenuItem("Générer compilation UML");
        genererCompilation.setOnAction(genererButtonController);
        generer.getItems().addAll(genererSquelette, genererUml, genererCompilation);


        menuBar.getMenus().addAll(menuItem, listeClasse, supprimer_les_classes, generer);

        applicationLayout.getChildren().addAll(menuBar,vueClass);

        VueContextMenuClasse contextMenu = new VueContextMenuClasse(model);
        model.ajouterObservateur(contextMenu);

        Scene scene = new Scene(applicationLayout, 1024, 768);
        stage.setScene(scene);
        stage.show();
        model.notifierObservateur("actualisation fleches");
    }

    public static void main(String[] args) {
        launch();
    }
}