package org.teamtree.objectaid.MVC.Vue;

import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import org.teamtree.objectaid.MVC.Controller.ControllerButtonGeneral;
import org.teamtree.objectaid.MVC.Model.Model;

/** Vue utilisée pour les boutons d'affichage d'une classe */
public class VueButtonBarClasse extends ContextMenu implements Observateur {

    /** Attribut qui represente le bouton pour afficher/masquer les attributs de la classe */
    private MenuItem buttonAttributs;

    /** Attribut qui represente le bouton pour afficher/masquer les methodes de la classe */
    private MenuItem buttonMethodes;

    /** Attribut qui represente le bouton pour afficher/masquer les constructeurs de la classe */
    private MenuItem buttonConstructeurs;

    /** Attribut qui represente le model */
    private Model model;

    /**
     * Constructeur de la classe
     * @param model model
     */
    public VueButtonBarClasse(Model model) {
        this.model = model;
        buttonAttributs = new MenuItem("Attributs");
        buttonAttributs.setOnAction(new ControllerButtonGeneral(model));
        buttonMethodes = new MenuItem("Méthodes");
        buttonMethodes.setOnAction(new ControllerButtonGeneral(model));
        buttonConstructeurs = new MenuItem("Constructeurs");
        buttonConstructeurs.setOnAction(new ControllerButtonGeneral(model));
        this.getItems().addAll(buttonAttributs, buttonMethodes, buttonConstructeurs);
    }

    /**
     * Methode qui permet de mettre à jour la vue
     */
    @Override
    public void actualiser() {

    }
}
