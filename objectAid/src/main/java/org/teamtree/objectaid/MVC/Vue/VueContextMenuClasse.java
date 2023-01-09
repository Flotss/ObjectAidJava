package org.teamtree.objectaid.MVC.Vue;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import org.teamtree.objectaid.MVC.Controller.MenuItemController;
import org.teamtree.objectaid.MVC.Model.Model;

/** Vue utilisée pour les boutons d'affichage d'une classe */
public class VueContextMenuClasse extends ContextMenu implements Observateur {

    /** Attribut qui represente le bouton pour afficher/masquer les attributs de la classe */
    private MenuItem itemAttributs;

    /** Attribut qui represente le bouton pour afficher/masquer les methodes de la classe */
    private MenuItem itemMethodes;

    /** Attribut qui represente le bouton pour afficher/masquer les constructeurs de la classe */
    private MenuItem itemConstructeurs;

    /** Attribut qui represente le model */
    private Model model;

    /**
     * ClasseAffichage sur laquel s'appuie cette vue
     */

    private VueClasseAffichage classe;

    /**
     * Constructeur de la classe
     * @param model model
     */
    public VueContextMenuClasse(Model model, VueClasseAffichage classe) {
        this.model = model;
        itemAttributs = new MenuItem("Attributs");
        itemAttributs.setOnAction(new MenuItemController(model));
        itemMethodes = new MenuItem("Méthodes");
        itemMethodes.setOnAction(new MenuItemController(model));
        itemConstructeurs = new MenuItem("Constructeurs");
        itemConstructeurs.setOnAction(new MenuItemController(model));
        this.getItems().addAll(itemAttributs, itemMethodes, itemConstructeurs);
        this.classe = classe;
    }

    /**
     * Methode qui permet de mettre à jour la vue
     */
    @Override
    public void actualiser() {
    }

    /**
     * Methode qui permet de retourner la classeAffichage de la vue
     * @return buttonAttributs
     */
    public VueClasseAffichage getClasse() {
        return classe;
    }
}
