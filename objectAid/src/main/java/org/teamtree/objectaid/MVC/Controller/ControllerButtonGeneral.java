package org.teamtree.objectaid.MVC.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.teamtree.objectaid.MVC.Model.Model;

/**
 * Classe qui permet de gérer tous les boutons de l'application
 */
public class ControllerButtonGeneral implements EventHandler<ActionEvent> {

    /** Modèle */
    private final Model model;

    /** Booleens pour savoir l'etat de l'affichage des boutons de l'affichage general des classes */
    private static boolean attributGenerauxAffiche = true;
    private static boolean constructeurGenerauxAffiche = true;
    private static boolean methodesGenerauxAffiche = true;
    private static boolean relationsGeneralesAffiche = true;

    /**
     * Constructeur
     * @param model Modèle
     */
    public ControllerButtonGeneral(Model model) {
        this.model = model;
    }

    /**
     * Méthode qui permet de gérer les boutons de l'application
     * @param event Evènement
     */
    @Override
    public void handle(ActionEvent event) {
        switch (((Button)event.getSource()).getText()){
            case "Afficher les attributs":
                attributGenerauxAffiche = !attributGenerauxAffiche;
                model.afficherAttributs(attributGenerauxAffiche);
                break;
            case "Afficher les méthodes":
                methodesGenerauxAffiche = !methodesGenerauxAffiche;
                model.afficherMethodes(methodesGenerauxAffiche);
                break;
            case "Afficher les constructeurs":
                constructeurGenerauxAffiche = !constructeurGenerauxAffiche;
                model.afficherConstructeurs(constructeurGenerauxAffiche);
                break;
            case "Afficher les relations":
                relationsGeneralesAffiche = !relationsGeneralesAffiche;
                model.afficherRelations(relationsGeneralesAffiche);
                break;
            case "Classe cachée" :
                Stage stage = new Stage();
                VBox vBox = new VBox();
                for (var classe : model.getHiddenClasses()) {
                    StackPane stackPane = new StackPane();
                    stackPane.setPadding(new Insets(10, 10, 10, 10));
                    Label label = new Label(classe.getNom());
                    label.setFont(new Font(20));
                    stackPane.setOnMouseClicked(new ControllerButtonClasseCachee(model, classe));
                    stackPane.getChildren().add(label);
                    vBox.getChildren().add(stackPane);
                }
                if (vBox.getChildren().isEmpty()) {
                    Label label = new Label("Aucune classe cachée");
                    label.setFont(new Font(20));
                    vBox.getChildren().add(label);
                }

                stage.setScene(new Scene(vBox));
                stage.setX(800);
                stage.setAlwaysOnTop(true);
                stage.setWidth(200);
                stage.show();
                break;
        }

    }
}