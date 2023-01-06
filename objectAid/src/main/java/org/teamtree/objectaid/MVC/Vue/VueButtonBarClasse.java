package org.teamtree.objectaid.MVC.Vue;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import org.teamtree.objectaid.MVC.Controller.ControllerButtonGeneral;
import org.teamtree.objectaid.MVC.Model.Model;

/** Vue utilisée pour les boutons d'affichage d'une classe */
public class VueButtonBarClasse extends HBox implements Observateur {

    /** Attribut qui represente le bouton pour afficher/masquer les attributs de la classe */
    private Button buttonAttributs;

    /** Attribut qui represente le bouton pour afficher/masquer les methodes de la classe */
    private Button buttonMethodes;

    /** Attribut qui represente le bouton pour afficher/masquer les constructeurs de la classe */
    private Button buttonConstructeurs;

    /** Attribut qui represente le booleen permettant de savoir quand la vue est censée afficher les boutons */
    private boolean afficherButtonBarClasse;

    /** Attribut qui represente le modele */
    private Model model;

    /**
     * Constructeur de la classe
     * @param model modele
     */
    public VueButtonBarClasse(Model model) {
        this.model = model;
        buttonAttributs = new Button("Attributs");
        buttonAttributs.setOnAction(new ControllerButtonGeneral(model));
        buttonMethodes = new Button("Méthodes");
        buttonMethodes.setOnAction(new ControllerButtonGeneral(model));
        buttonConstructeurs = new Button("Constructeurs");
        buttonConstructeurs.setOnAction(new ControllerButtonGeneral(model));
        afficherButtonBarClasse = model.getBarreBoutonsSpecifique();
    }

    /**
     * Methode qui permet de mettre à jour la vue
     */
    @Override
    public void actualiser() {
        afficherButtonBarClasse = model.getBarreBoutonsSpecifique();
        this.getChildren().clear();
        if (afficherButtonBarClasse){
            this.getChildren().addAll(buttonAttributs, buttonMethodes, buttonConstructeurs);
        }
    }
}
