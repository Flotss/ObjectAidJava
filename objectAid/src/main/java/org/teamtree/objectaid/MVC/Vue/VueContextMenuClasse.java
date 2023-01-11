package org.teamtree.objectaid.MVC.Vue;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import org.teamtree.objectaid.MVC.Controller.MenuItemController;
import org.teamtree.objectaid.MVC.Model.Model;

/** Vue utilisée pour les boutons d'affichage d'une classe */
public class VueContextMenuClasse extends ContextMenu implements Observateur {

    /** Attribut qui represente le bouton pour afficher/masquer les attributs de la classe */
    private final MenuItem itemAttributs;

    /** Attribut qui represente le bouton pour afficher/masquer les methodes de la classe */
    private final MenuItem itemMethodes;

    /** Attribut qui represente le bouton pour afficher/masquer les constructeurs de la classe */
    private final MenuItem itemConstructeurs;

    /** Attribut qui represente le bouton pour cacher la classe */
    private final MenuItem itemCacherClasse;

    /** Attribut qui represente le bouton pour supprimer la classe */
    private final MenuItem itemSupprimerClasse;

    /** Attribut qui represente le model */
    private final Model model;

    /**
     * ClasseAffichage sur laquel s'appuie cette vue
     */

    private VueClasseAffichage classe;

    /**
     * Constructeur de la classe
     * @param model model
     */
    public VueContextMenuClasse(Model model) {
        this.model = model;
        itemAttributs = new MenuItem("Attributs");
        itemAttributs.setOnAction(new MenuItemController(model));
        itemMethodes = new MenuItem("Méthodes");
        itemMethodes.setOnAction(new MenuItemController(model));
        itemConstructeurs = new MenuItem("Constructeurs");
        itemConstructeurs.setOnAction(new MenuItemController(model));
        itemCacherClasse = new MenuItem("Cacher");
        itemCacherClasse.setOnAction(new MenuItemController(model));
        itemSupprimerClasse = new MenuItem("Supprimer");
        itemSupprimerClasse.setOnAction(new MenuItemController(model));
        this.getItems().addAll(itemAttributs, itemMethodes, itemConstructeurs, itemCacherClasse);
        this.classe = null;
    }

    /**
     * Methode qui permet de retourner la classeAffichage de la vue
     * @return buttonAttributs
     */
    public VueClasseAffichage getClasse() {
        return classe;
    }

    public void setCoordonnees(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    public void setClasse(VueClasseAffichage classe) {
        this.classe = classe;
    }

    @Override
    public void actualiser() {
        this.show(classe, this.getX(), this.getY());
    }
}
