package org.teamtree.objectaid.MVC.Vue;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import org.teamtree.objectaid.MVC.Controller.ControllerButtonGeneral;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.MVC.Vue.Observateur;

public class VueButtonBarClasse extends HBox implements Observateur {

    private Button buttonAttributs;
    private Button buttonMethodes;
    private Button buttonConstructeurs;
    private boolean afficherButtonBarClasse;

    private Model model;

    public VueButtonBarClasse(Model model) {
        this.model = model;
        buttonAttributs = new Button("Attributs");
        buttonAttributs.setOnAction(new ControllerButtonGeneral(model));
        buttonMethodes = new Button("Méthodes");
        buttonMethodes.setOnAction(new ControllerButtonGeneral(model));
        buttonConstructeurs = new Button("Constructeurs");
        buttonConstructeurs.setOnAction(new ControllerButtonGeneral(model));
        afficherButtonBarClasse = false;
    }

    @Override
    public void actualiser() {
        if (!afficherButtonBarClasse){
            this.getChildren().addAll(buttonAttributs, buttonMethodes, buttonConstructeurs);
        } else {
            this.getChildren().clear();
        }
        afficherButtonBarClasse = !afficherButtonBarClasse;
    }
}
