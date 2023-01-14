package org.teamtree.objectaid.Fabrique;

import org.teamtree.objectaid.Classe.Relations.Relation;
import org.teamtree.objectaid.MVC.Fleches.FlecheAssociation;
import org.teamtree.objectaid.MVC.Fleches.FlecheHeritage;
import org.teamtree.objectaid.MVC.Fleches.FlecheImplementation;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.MVC.Vue.Fleche;

/**
 * Cette classe permet de créer une fleche en fonction de la relation
 */
public class FabriqueAffichageFleche {

    /**
     * Méthode de création d'une fleche en fonction de la relation
     *
     * @param relation La relation
     * @param model    Le model
     * @return La fleche
     */
    public static Fleche creerAffichageFleche(Model model, Relation relation) {

        return switch (relation.getClass().getSimpleName()) {
            case "Association" -> new FlecheAssociation(model, relation);
            case "Heritage" -> new FlecheHeritage(model, relation);
            case "Implementation" -> new FlecheImplementation(model, relation);
            default -> null;
        };
    }
}
