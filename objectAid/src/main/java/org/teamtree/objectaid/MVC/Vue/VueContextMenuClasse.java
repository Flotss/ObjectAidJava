package org.teamtree.objectaid.MVC.Vue;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.teamtree.objectaid.MVC.Controller.MenuItemController;
import org.teamtree.objectaid.MVC.Model.Model;

/** Vue utilisée pour les boutons d'affichage d'une classe */
public class VueContextMenuClasse extends ContextMenu implements Observateur {

    /**
     * ClasseAffichage sur laquel s'appuie cette vue
     */

    private VueClasseAffichage classe;

    /**
     * Constructeur de la classe
     * @param model model
     */
    public VueContextMenuClasse(Model model) {
        /* Attribut qui represente le model */

        // Pouvoir afficher les attributs
        MenuItem itemAttributs = new MenuItem("Attributs");
        itemAttributs.setOnAction(new MenuItemController(model));

        // Pouvoir afficher les methodes
        MenuItem itemMethodes = new MenuItem("Méthodes");
        itemMethodes.setOnAction(new MenuItemController(model));

        // Pouvoir afficher les constructeurs
        MenuItem itemConstructeurs = new MenuItem("Constructeurs");
        itemConstructeurs.setOnAction(new MenuItemController(model));

        // Pouvoir cacher une classe
        MenuItem itemCacherClasse = new MenuItem("Cacher");
        itemCacherClasse.setOnAction(new MenuItemController(model));

        // Pouvoir supprimer une classe
        MenuItem itemSupprimerClasse = new MenuItem("Supprimer");
        itemSupprimerClasse.setOnAction(new MenuItemController(model));

        // Pouvoir générer le squelette de classe
        MenuItem itemGenererSquelette = new MenuItem("Générer le squelette");
        itemGenererSquelette.setOnAction(new MenuItemController(model));

        // Pouvoir cacher l'interface parent
        MenuItem itemInterface = new MenuItem("Cacher interface");
        itemInterface.setOnAction(new MenuItemController(model));

        // Pouvoir cacher la classe parent
        MenuItem itemHeritage = new MenuItem("Cacher heritage");
        itemHeritage.setOnAction(new MenuItemController(model));

        // Ajouter quelque chose dans une classe
        Menu itemAjouter = new Menu("Ajouter");


        // Pouvoir un attribut dans une classe
        MenuItem itemAjouterAttribut = new MenuItem("Attribut");
        itemAjouterAttribut.setId("ajouterAttribut");
        itemAjouterAttribut.setOnAction(new MenuItemController(model));

        // Pouvoir un constructeur dans une classe
        MenuItem itemAjouterConstructeur = new MenuItem("Constructeur");
        itemAjouterConstructeur.setId("ajouterConstructeur");
        itemAjouterConstructeur.setOnAction(new MenuItemController(model));

        // Pouvoir une méthode dans une classe
        MenuItem itemAjouterMethode = new MenuItem("Méthode");
        itemAjouterMethode.setId("ajouterMethode");
        itemAjouterMethode.setOnAction(new MenuItemController(model));

        itemAjouter.getItems().addAll(itemAjouterAttribut, itemAjouterConstructeur, itemAjouterMethode);



        // Ajout de tous ces items dans le menu contextuel
        this.getItems().addAll(itemAttributs, itemMethodes, itemConstructeurs, itemCacherClasse, itemSupprimerClasse, itemInterface, itemHeritage, itemGenererSquelette, itemAjouter);
        this.classe = null;
    }

    /**
     * Methode qui permet de retourner la classeAffichage de la vue
     * @return La classeAffichage de la vue
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
