package org.teamtree.objectaid.MVC.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.MVC.Vue.VueClasseAffichage;

/**
 * Classe qui permet de gérer tous les MenuItem de l'application
 */
public class MenuItemController implements EventHandler<ActionEvent> {

    /** Booleens pour savoir l'etat de l'affichage des boutons de l'affichage general des classes */
    private static boolean attributGenerauxAffiche = true;
    private static boolean constructeurGenerauxAffiche = true;
    private static boolean methodesGenerauxAffiche = true;
    private static boolean relationsGeneralesAffiche = true;

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
        switch (((MenuItem)event.getSource()).getText()){
            case "Attributs":
                    model.afficherAttributsSelection();
                break;

            case "Méthodes":
                    model.afficherMethodesSelection();
                break;

            case "Constructeurs":
                    model.afficherConstructeursSelection();
                break;

            case "Cacher la classe":
                model.ajouterClasseCachee(model.getCurrentClickedClass());
                break;

            case "Afficher/Cacher":
                VueClasseAffichage classe = model.getClasse(((MenuItem) event.getSource()).getParentMenu().getText()).get().getClasseAffichage();
                System.out.println(model.getCurrentClickedClass());
                if (!model.getHiddenClasses().contains(classe)) {
                    model.setCurrentClickedClass(classe);
                    model.ajouterClasseCachee(classe);
                } else {
                    model.supprimerClasseCachee(classe);
                }
                break;

            case "Supprimer":
                VueClasseAffichage classe1 = model.getClasse(((MenuItem) event.getSource()).getParentMenu().getText()).get().getClasseAffichage();
                model.supprimerClasseAffichage(classe1);
                ((MenuItem) event.getSource()).getParentMenu().getParentMenu().getItems().remove(((MenuItem) event.getSource()).getParentMenu());
                break;
        }
    }
}
