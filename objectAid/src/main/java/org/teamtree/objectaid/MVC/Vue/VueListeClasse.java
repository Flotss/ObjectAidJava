package org.teamtree.objectaid.MVC.Vue;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.teamtree.objectaid.MVC.Controller.ControllerButtonGeneral;
import org.teamtree.objectaid.MVC.Controller.MenuItemController;
import org.teamtree.objectaid.MVC.Model.Model;

public class VueListeClasse extends Menu implements Observateur {
    private final Model model;

    /**
     * Constructs a Menu and sets the display text with the specified text.
     *
     * @param text  the text to display on the menu button
     * @param model
     */
    public VueListeClasse(final String text, final Model model) {
        super(text);
        this.model = model;

        actualiser();
    }

    /**
     * Méthode qui permet de mettre à jour l'observateur
     */
    @Override
    public void actualiser() {
        this.getItems().clear();

        model.getClasses().forEach(classe -> {
            final var menuItem = new Menu(classe.getNom());
            final var menuItemAttribut = new MenuItem("Afficher/Cacher");
            final var menuItemMethodes = new MenuItem("Supprimer");

            menuItemAttribut.setOnAction(new MenuItemController(model));
            menuItemMethodes.setOnAction(new ControllerButtonGeneral(model));

            menuItem.getItems().addAll(menuItemAttribut, menuItemMethodes);

            getItems().add(menuItem);
        });
    }

    public void changerCouleurTexte() {
        this.setStyle("-fx-text-fill: red;");
    }
}
