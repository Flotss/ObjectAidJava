package org.teamtree.objectaid.MVC.Vue;

import javafx.scene.layout.Pane;
import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.Classe.Relations.Relation;
import org.teamtree.objectaid.Fabrique.FabriqueAffichage;
import org.teamtree.objectaid.Fabrique.FabriqueAffichageFleche;
import org.teamtree.objectaid.MVC.Controller.ClasseEntiereClickedController;
import org.teamtree.objectaid.MVC.Controller.DeplacementClasseDragAndDropController;
import org.teamtree.objectaid.MVC.Fleches.Fleche;
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

    /** HashMap qui represente la liste des classes à afficher, chaque classe possede comme clé le nom de la classe */
    private final HashMap<String, VueClasseAffichage> classes;

    /** Liste des fleches */
    private final List<Fleche> fleches;



    /**
     * Constructeur de la classe VueClasse
     * @param model Modèle
     */
    public VueClasse(Model model) {
        this.model = model;
        this.classes = new HashMap<>();
        this.fleches = new ArrayList<>();


        // Pour chaque classe
        for (ClasseEntiere classEntiere : model.getClasses()) {
            // On creation de l'affichage de la classe
            FabriqueAffichage f = new FabriqueAffichage(classEntiere, model);
            VueClasseAffichage classe = f.affichage();

//            setBorderColor(classe);

            this.getChildren().add(classe);

            // On ajoute le controller lorsqu'on clique sur la classe
            classe.setOnMouseClicked(new ClasseEntiereClickedController(model));

            //on ajoute le drag and drop de la classe
            classe.setOnMouseDragged(new DeplacementClasseDragAndDropController(model));

            classes.put(classe.getNom(),classe);
        }
        model.ajouterObservateur(this);

        // Pour chaque relation
        for (ClasseEntiere classEntiere : model.getClasses()) {
            for (Relation relation : model.getRelations(classEntiere)) {
                if (model.getVueClasseAffichage(relation.getDestination()) == null) continue;

                // Relation
                Fleche fleche = FabriqueAffichageFleche.creerAffichageFleche(model, relation);
                fleches.add(fleche);
                this.getChildren().add(fleche);
            }
        }
    }

    /**
     * Méthode qui permet de mettre a jour la vue
     */
    @Override
    public void actualiser() {
        for (VueClasseAffichage classe : classes.values()) {
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

    /**
     * Méthode qui permet de retourner la classe affichage correspondant au nom de la classe dans la liste des classes
     * @param nom String
     * @return classeAffichage
     */
    public VueClasseAffichage getClasseAffichage(String nom){
        return this.classes.get(nom);
    }

    public void actualiserFleches() {
        for (Fleche fleche : fleches) {
            fleche.actualiser();
        }
    }
}
