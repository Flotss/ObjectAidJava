package org.teamtree.objectaid.Fabrique;

import org.teamtree.objectaid.Classe.Relations.Relation;
import org.teamtree.objectaid.MVC.Fleches.Fleche;
import org.teamtree.objectaid.MVC.Fleches.FlecheAssociation;
import org.teamtree.objectaid.MVC.Fleches.FlecheHeritage;
import org.teamtree.objectaid.MVC.Fleches.FlecheImplementation;
import org.teamtree.objectaid.MVC.Model.Model;

public class FabriqueAffichageFleche {
        public static Fleche creerAffichageFleche(Model model, Relation relation){

            return switch (relation.getClass().getSimpleName()) {
                case "Association" -> new FlecheAssociation(model, relation);
                case "Heritage" -> new FlecheHeritage(model, relation);
                case "Implementation" -> new FlecheImplementation(model, relation);
                default -> null;
            };
        }
}
