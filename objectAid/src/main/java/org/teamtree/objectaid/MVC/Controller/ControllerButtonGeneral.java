package org.teamtree.objectaid.MVC.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import org.teamtree.objectaid.MVC.Model.Model;

public class ControllerButtonGeneral implements EventHandler<ActionEvent> {

    private final Model model;
    private static boolean attributGenerauxAffiche = true;
    private static boolean constructeurGenerauxAffiche = true;
    private static boolean methodesGenerauxAffiche = true;

    public ControllerButtonGeneral(Model model) {
        this.model = model;
    }


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
            case "Attributs":
                model.afficherAttributsSelection();
                System.out.println("sfsdf");
                break;
            case "Méthodes":
                model.afficherMethodesSelection();
                break;
            case "Constructeurs":
                model.afficherConstructeursSelection();
                break;
        }
    }
}