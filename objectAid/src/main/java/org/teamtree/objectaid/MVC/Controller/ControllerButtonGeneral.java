package org.teamtree.objectaid.MVC.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.MVC.Vue.VueClasseAffichage;
import org.teamtree.objectaid.render.ApplicationLayoutProjectLoadedRender;

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
        switch (((MenuItem) event.getSource()).getText()) {
            case "Attributs" -> {
                System.out.println("gvvgyvyvygvy");
                attributGenerauxAffiche = !attributGenerauxAffiche;
                model.afficherAttributs(attributGenerauxAffiche);
            }
            case "Methodes" -> {
                methodesGenerauxAffiche = !methodesGenerauxAffiche;
                model.afficherMethodes(methodesGenerauxAffiche);
            }
            case "Constructeurs" -> {
                constructeurGenerauxAffiche = !constructeurGenerauxAffiche;
                model.afficherConstructeurs(constructeurGenerauxAffiche);
            }
            case "Relations" -> {
                relationsGeneralesAffiche = !relationsGeneralesAffiche;
                model.afficherRelations(relationsGeneralesAffiche);
            }
            case "Supprimer" -> {
                VueClasseAffichage classe1 = model.getVueClasseAffichage(((MenuItem) event.getSource()).getParentMenu().getText());
                model.supprimerClasseAffichage(classe1);
                ApplicationLayoutProjectLoadedRender.menubar.getMenus().get(1).getItems().remove(((MenuItem) event.getSource()).getParentMenu());
            }
            case "Supprimer les classes" -> {
                model.supprimerClassesAffichage();
                ApplicationLayoutProjectLoadedRender.menubar.getMenus().get(1).getItems().clear();
            }
            case "set is get" -> {
                System.out.println("jkjkh");
                model.inverserAffichageGetIsSet();
            }
        }
    }

}