package org.teamtree.objectaid.MVC.Vue;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.teamtree.objectaid.Classe.ClasseAffichage;
import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.Fabrique.FabriqueAffichage;
import org.teamtree.objectaid.MVC.Controller.ClasseEntiereClickedController;
import org.teamtree.objectaid.MVC.Model.Model;

import java.util.ArrayList;

/**
 * Classe qui permet de représenter la vue des classes
 */
public class VueClasse extends Pane implements Observateur {

    /** Modèle */
    private final Model model;

    /** Liste des classes */
    private final ArrayList<VBox> classes;

    /**
     * Constructeur de la classe VueClasse
     * @param model Modèle
     */
    public VueClasse(Model model) {
        this.model = model;
        this.classes = new ArrayList<>();

        actualiser();
    }

    @Override
    public void actualiser() {
        // Mise a zero de la vue
        this.getChildren().clear();

        // Pour chaque classe
        for (ClasseEntiere c : model.getClasses()) {
            // On creation de l'affichage de la classe
            FabriqueAffichage f = new FabriqueAffichage(c);
            ClasseAffichage classe = f.affichage();

            setBorderColor(classe);

            this.getChildren().add(classe);

            // On ajoute le controller
            classe.setOnMouseClicked(new ClasseEntiereClickedController(model));
        }
    }

    /**
     * Méthode qui permet de mettre la couleur de la bordure de la classe
     * @param node ClasseAffichage à mettre en couleur
     */
    public void setBorderColor(final ClasseAffichage node) {
        final String color = model.getCurrentClickedClass().equals(node.getNom()) ? "blue" : "black";
        final var builderStyle = new StringBuilder();
        builderStyle.append(node.getStyle());
        builderStyle.append("-fx-border-color: ");
        builderStyle.append(color);
        builderStyle.append("; -fx-border-width: 1px;");

        node.setStyle(builderStyle.toString());
    }

}
