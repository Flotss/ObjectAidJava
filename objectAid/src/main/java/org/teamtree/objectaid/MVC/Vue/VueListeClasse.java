package org.teamtree.objectaid.MVC.Vue;

import javafx.collections.ObservableList;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.teamtree.objectaid.MVC.Controller.ControllerButtonGeneral;
import org.teamtree.objectaid.MVC.Controller.MenuItemController;
import org.teamtree.objectaid.MVC.Model.Model;

import java.util.ArrayList;

public class VueListeClasse extends Menu implements Observateur {
    private final Model model;

    /**
     * Constructs a Menu and sets the display text with the specified text.
     *
     * @param text  the text to display on the menu button
     * @param model Modèle
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

        // Pouvoir ajouter une classe
        final var menuItemAjouterClasse = new MenuItem("Ajouter une classe");
        menuItemAjouterClasse.setId("ajouterClasse");
        menuItemAjouterClasse.setOnAction(new MenuItemController(model));
        getItems().add(menuItemAjouterClasse);
    }

    public void changerCouleurTexte(String nom, String couleur) {
        for (MenuItem m : this.getItems()) {
            if (m.getText().equals(nom)) {
                m.setStyle("-fx-text-fill: " + couleur);
            }
        }
    }


    public ObservableList<MenuItem> getItemsVue() {
        return this.getItems();
    }
}
