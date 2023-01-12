package org.teamtree.objectaid.MVC.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.ObjectAidApplication;

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
        switch (((MenuItem)event.getSource()).getText()){
            case "Attributs":
                System.out.println("gvvgyvyvygvy");
                attributGenerauxAffiche = !attributGenerauxAffiche;
                model.afficherAttributs(attributGenerauxAffiche);
                break;
            case "Methodes":
                methodesGenerauxAffiche = !methodesGenerauxAffiche;
                model.afficherMethodes(methodesGenerauxAffiche);
                break;
            case "Constructeurs":
                constructeurGenerauxAffiche = !constructeurGenerauxAffiche;
                model.afficherConstructeurs(constructeurGenerauxAffiche);
                break;
            case "Relations":
                relationsGeneralesAffiche = !relationsGeneralesAffiche;
                model.afficherRelations(relationsGeneralesAffiche);
                break;
            case "Supprimer les classes":
                model.supprimerClassesAffichage();
                ObjectAidApplication.menuBar.getMenus().get(1).getItems().clear();
                break;
        }
    }
}