package org.teamtree.objectaid.MVC.Vue;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.teamtree.objectaid.Classe.ClasseAffichage;
import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.Fabrique.FabriqueAffichage;
import org.teamtree.objectaid.MVC.Controller.ClasseEntiereClickedController;
import org.teamtree.objectaid.MVC.Controller.DragAndDropController;
import org.teamtree.objectaid.MVC.Model.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui permet de représenter la vue des classes
 */
public class VueClasse extends Pane implements Observateur {

    /** Modèle */
    private final Model model;
    private final List<ClasseAffichage> classes;


    /**
     * Constructeur de la classe VueClasse
     * @param model Modèle
     */
    public VueClasse(Model model) {
        this.model = model;
        this.classes = new ArrayList<>();

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

            //on ajoute le drag and drop
            classe.setOnMouseDragged(new DragAndDropController(model));
            classes.add(classe);
        }

        actualiser();
    }

    @Override
    public void actualiser() {
        for (ClasseAffichage classe : classes) {
            setBorderColor(classe);
            classe.setLayoutX(model.getClasse(classe.getNom()).get().getX());
            classe.setLayoutY(model.getClasse(classe.getNom()).get().getY());
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
