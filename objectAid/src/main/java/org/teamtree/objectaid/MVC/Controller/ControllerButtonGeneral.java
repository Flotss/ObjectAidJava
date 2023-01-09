package org.teamtree.objectaid.MVC.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
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
            case "Attributs":
                model.afficherAttributsSelection();
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