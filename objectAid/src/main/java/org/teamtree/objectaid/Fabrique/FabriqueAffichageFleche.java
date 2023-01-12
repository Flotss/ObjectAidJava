package org.teamtree.objectaid.Fabrique;

import org.teamtree.objectaid.Classe.Relations.Relation;
import org.teamtree.objectaid.MVC.Fleches.*;
import org.teamtree.objectaid.MVC.Model.Model;

/**
 * Cette classe permet de créer une fleche en fonction de la relation
 */
public class FabriqueAffichageFleche {

    /**
     * Méthode de création d'une fleche en fonction de la relation
     * @param relation La relation
     * @param model Le model
     * @return La fleche
     */
    public static Fleche creerAffichageFleche(Model model, Relation relation){

        return switch (relation.getClass().getSimpleName()) {
            case "Association" -> new FlecheAssociation(model, relation);
            case "Heritage" -> new FlecheHeritage(model, relation);
            case "Implementation" -> new FlecheImplementation(model, relation);
            case "Composition" -> new FlecheComposition(model, relation);
            default -> null;
        };
    }
}
