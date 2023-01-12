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
import org.teamtree.objectaid.MVC.Composant.ComponentAddClass;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.render.ApplicationLayoutProjectLoadedRender;
import org.teamtree.objectaid.MVC.Vue.VueClasseAffichage;
import org.teamtree.objectaid.Service.SqueletteService;

import java.util.ArrayList;

/**
 * Classe qui permet de gérer tous les MenuItem de l'application
 */
public class MenuItemController implements EventHandler<ActionEvent> {

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
        switch (((MenuItem) event.getSource()).getText()) {
            case "Attributs" -> model.afficherAttributsSelection();
            case "Méthodes" -> model.afficherMethodesSelection();
            case "Constructeurs" -> model.afficherConstructeursSelection();
            case "Cacher" -> {
                for (MenuItem m : ApplicationLayoutProjectLoadedRender.menubar.getMenus().get(1).getItems()) {
                    if (m.getText().equals(model.getCurrentClickedClass().getNom())) {
                        m.setStyle("-fx-text-fill: red");
                    }
                }
                model.ajouterClasseCachee(model.getCurrentClickedClass());
            }
            case "Afficher/Cacher" -> {
                VueClasseAffichage classe = model.getClasse(((MenuItem) event.getSource()).getParentMenu().getText()).get().getClasseAffichage();
                if (!model.getHiddenClasses().contains(classe)) {
                    model.setCurrentClickedClass(classe);
                    model.ajouterClasseCachee(classe);
                    ((MenuItem) event.getSource()).getParentMenu().setStyle("-fx-text-fill: red");
                } else {
                    model.supprimerClasseCachee(classe);
                    ((MenuItem) event.getSource()).getParentMenu().setStyle("-fx-text-fill: black");
                }
            }
            case "Supprimer" -> {
                VueClasseAffichage classe1 = model.getCurrentClickedClass();
                model.supprimerClasseAffichage(classe1);
                ApplicationLayoutProjectLoadedRender.menubar.getMenus().get(1).getItems().removeIf(m -> m.getText().equals(model.getCurrentClickedClass().getNom()));
            }
            case "Générer le squelette" -> {
                ClasseEntiere classeE = model.getCurrentClickedClass().getClasseEntiere();
                SqueletteService squeletteService = new SqueletteService();
                squeletteService.genererSqueletteUniqueClasse(classeE);
            }
            case "Cacher interface" -> model.afficherInterfaceHeritageSelection("Implementation");
            case "Cacher heritage" -> model.afficherInterfaceHeritageSelection("Heritage");
            case "Supprimer les classes" -> {
                if (model.getClasses().size() > 0) {
                    model.supprimerClassesAffichage();
                    ApplicationLayoutProjectLoadedRender.menubar.getMenus().get(1).getItems().clear();
                }
            }
        }
        if(((MenuItem)event.getSource()).getId() != null) {
            ComponentAddClass componentAddClass = new ComponentAddClass(model);
            switch (((MenuItem) event.getSource()).getId()) {
                case "ajouterAttribut" -> componentAddClass.ajouterAttribut();
                case "ajouterConstructeur" -> componentAddClass.ajouterContructeur();
                case "ajouterMethode" -> componentAddClass.ajouterMethode();
            }
        }
    }
}
