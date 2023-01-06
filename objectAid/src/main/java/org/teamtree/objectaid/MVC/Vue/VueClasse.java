package org.teamtree.objectaid.MVC.Vue;

import javafx.scene.layout.Pane;
import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.Fabrique.FabriqueAffichage;
import org.teamtree.objectaid.MVC.Controller.ClasseEntiereClickedController;
import org.teamtree.objectaid.MVC.Controller.DeplacementClasseDragAndDropController;
import org.teamtree.objectaid.MVC.Model.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Classe qui permet de représenter la vue des classes
 */
public class VueClasse extends Pane implements Observateur {

    /** Modèle */
    private final Model model;
    private final HashMap<String, VueClasseAffichage> classes;


    /**
     * Constructeur de la classe VueClasse
     * @param model Modèle
     */
    public VueClasse(Model model) {
        this.model = model;
        this.classes = new HashMap<>();


        // Pour chaque classe
        for (ClasseEntiere c : model.getClasses()) {
            // On creation de l'affichage de la classe
            FabriqueAffichage f = new FabriqueAffichage(c);
            VueClasseAffichage classe = f.affichage();
            classe.updateAttributsRelation(model);

//            setBorderColor(classe);

            this.getChildren().add(classe);

            // On ajoute le controller lorsqu'on clique sur la classe
            classe.setOnMouseClicked(new ClasseEntiereClickedController(model));

            //on ajoute le drag and drop de la classe
            classe.setOnMouseDragged(new DeplacementClasseDragAndDropController(model));

            classes.put(classe.getNom(),classe);
        }
    }

    /**
     * Méthode qui permet de mettre a jour la vue
     */

    @Override
    public void actualiser() {
        for (VueClasseAffichage classe : classes.values()) {
            ClasseEntiere classeEntiere = model.getClasse(classe.getNom()).get();
            classe.updateAttributsRelation(model);
            classe.afficherClasse();
        }
    }

//    /**
//     * Méthode qui permet de mettre la couleur de la bordure de la classe
//     * @param node ClasseAffichage à mettre en couleur
//     */
//    public void setBorderColor(final ClasseAffichage node) {
//        final String color = model.getCurrentClickedClass().equals(node.getNom()) ? "blue" : "black";
//        final var builderStyle = new StringBuilder();
//        builderStyle.append(node.getStyle());
//        builderStyle.append("-fx-border-color: ");
//        builderStyle.append(color);
//        builderStyle.append("; -fx-border-width: 1px;");
//
//        node.setStyle(builderStyle.toString());
//    }

    public VueClasseAffichage getClasseAffichage(String nom){
        return this.classes.get(nom);
    }
}
