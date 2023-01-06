package org.teamtree.objectaid.Classe;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ButtonBarClasse extends HBox {

    private Button buttonAttributs;
    private Button buttonMethodes;
    private Button buttonConstructeurs;
    private boolean afficherButtonBarClasse;

    public ButtonBarClasse() {
        super();
        buttonAttributs = new Button("Attributs");
        buttonMethodes = new Button("Méthodes");
        buttonConstructeurs = new Button("Constructeurs");
        afficherButtonBarClasse = false;
    }

    public void setButtons(){
        if (!afficherButtonBarClasse){
            this.getChildren().addAll(buttonAttributs, buttonMethodes, buttonConstructeurs);
            afficherButtonBarClasse = true;
        } else {
            this.getChildren().clear();
            afficherButtonBarClasse = false;
        }

    }


}
