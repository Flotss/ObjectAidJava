package org.teamtree.objectaid.MVC.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.teamtree.objectaid.Accessibilite.*;
import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.Etat.Abstract;
import org.teamtree.objectaid.Etat.Etat;
import org.teamtree.objectaid.Etat.Final;
import org.teamtree.objectaid.Etat.Static;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.render.ApplicationLayoutProjectLoadedRender;
import org.teamtree.objectaid.MVC.Vue.VueClasseAffichage;
import org.teamtree.objectaid.MVC.Vue.VueContextMenuClasse;
import org.teamtree.objectaid.Service.SqueletteService;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui permet de gérer tous les MenuItem de l'application
 */
public class MenuItemController implements EventHandler<ActionEvent> {

    /** Booleens pour savoir l'etat de l'affichage des boutons de l'affichage general des classes */
    private static boolean attributGenerauxAffiche = true;
    private static boolean constructeurGenerauxAffiche = true;
    private static boolean methodesGenerauxAffiche = true;
    private static boolean relationsGeneralesAffiche = true;

    /** Modèle */
    private final Model model;

    /**
     * Constructeur
     * @param model Modèle
     */
    public MenuItemController(Model model) {
        this.model = model;
    }

    /**
     * Méthode qui permet de gérer les MenuItem de l'application
     * @param event Evènement
     */
    @Override
    public void handle(ActionEvent event) {
        switch (((MenuItem)event.getSource()).getText()){
            case "Attributs":
                model.afficherAttributsSelection();
                break;
            case "Méthodes":
                model.afficherMethodesSelection();
                break;
            case "Constructeurs":
                model.afficherConstructeursSelection();
                break;
            case "Cacher":
                for (MenuItem m: ApplicationLayoutProjectLoadedRender.menubar.getMenus().get(1).getItems()) {
                    if (m.getText().equals(model.getCurrentClickedClass().getNom())) {
                        m.setStyle("-fx-text-fill: red");
                    }
                }
                model.ajouterClasseCachee(model.getCurrentClickedClass());
                break;
            case "Afficher/Cacher":
                VueClasseAffichage classe = model.getClasse(((MenuItem) event.getSource()).getParentMenu().getText()).get().getClasseAffichage();
                if (!model.getHiddenClasses().contains(classe)) {
                    model.setCurrentClickedClass(classe);
                    model.ajouterClasseCachee(classe);
                    ((MenuItem) event.getSource()).getParentMenu().setStyle("-fx-text-fill: red");
                } else {
                    model.supprimerClasseCachee(classe);
                    ((MenuItem) event.getSource()).getParentMenu().setStyle("-fx-text-fill: black");
                }
                break;

            case "Supprimer":
                VueClasseAffichage classe1 = model.getCurrentClickedClass();
                model.supprimerClasseAffichage(classe1);
                ApplicationLayoutProjectLoadedRender.menubar.getMenus().get(1).getItems().removeIf(m -> m.getText().equals(model.getCurrentClickedClass().getNom()));
                break;
            case "Générer le squelette":
                ClasseEntiere classeE = model.getCurrentClickedClass().getClasseEntiere();
                SqueletteService squeletteService = new SqueletteService();
                squeletteService.genererSqueletteUniqueClasse(classeE);
                break;
            case "Cacher interface":
                model.afficherInterfaceHeritageSelection("Implementation");
                break;
            case "Cacher heritage":
                model.afficherInterfaceHeritageSelection("Heritage");
                break;
            case "Supprimer les classes":
                model.supprimerClassesAffichage();
                ApplicationLayoutProjectLoadedRender.menubar.getMenus().get(1).getItems().clear();
                break;
        }

        switch (((MenuItem)event.getSource()).getId()){
            case "ajouterMethode":
                System.out.println("POURQUOI TU VAS ICI CONNARDDDD");
                ajouterMethode();
                break;
        }
    }


    /**
     * Méthode qui permet de gérer la modification du classe
     */
    private void ajouterMethode() {
        Stage stage = new Stage();
        stage.setTitle("Ajouter une methode");

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10));

        ChoiceBox<Accessibilite> accessibiliteChoiceBox = new ChoiceBox<>();
        accessibiliteChoiceBox.getItems().addAll(new Public(), new Private(), new Default(), new Protected());
        accessibiliteChoiceBox.setValue(new Public());

        ChoiceBox<Etat> staticChoiceBox = new ChoiceBox<>();
        staticChoiceBox.getItems().addAll( new Static(),  null);
        staticChoiceBox.setValue(null);

        ChoiceBox<Etat> finalChoiceBox = new ChoiceBox<>();
        finalChoiceBox.getItems().addAll( new Final(),  null);
        finalChoiceBox.setValue(null);

        ChoiceBox<Etat> abstractChoiceBox = new ChoiceBox<>();
        abstractChoiceBox.getItems().addAll( new Abstract(),  null);
        abstractChoiceBox.setValue(null);

        TextField nomTextField = new TextField();
        nomTextField.setPromptText("Nom de la methode");

        TextField typeTextField = new TextField();
        typeTextField.setPromptText("Type de retour");

        TextField parametresTextField = new TextField();
        parametresTextField.setPromptText("Parametres ex  nom : String");

        Button ajouterButton = new Button("Ajouter");
        ajouterButton.setOnAction(event -> {
            ArrayList<Etat> etats = new ArrayList<>();
            if (staticChoiceBox.getValue() != null) {
                etats.add(staticChoiceBox.getValue());
            }
            if (finalChoiceBox.getValue() != null) {
                etats.add(finalChoiceBox.getValue());
            }
            if (abstractChoiceBox.getValue() != null) {
                etats.add(abstractChoiceBox.getValue());
            }


          model.ajouterMethode(accessibiliteChoiceBox.getValue(), etats, nomTextField.getText(), typeTextField.getText(), parametresTextField.getText());
            stage.close();
        });

        hBox.getChildren().addAll(accessibiliteChoiceBox, staticChoiceBox, abstractChoiceBox, finalChoiceBox, typeTextField, nomTextField, parametresTextField, ajouterButton);
        Scene scene = new Scene(hBox);

        stage.setScene(scene);
        stage.show();
    }
}
