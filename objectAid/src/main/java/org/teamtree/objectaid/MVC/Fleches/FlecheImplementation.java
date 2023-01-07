package org.teamtree.objectaid.MVC.Fleches;

import javafx.scene.paint.Color;
import org.teamtree.objectaid.Classe.Relations.Relation;
import org.teamtree.objectaid.MVC.Model.Model;

/**
 * Cette classe représente une flèche d'implémentation.
 */
public class FlecheImplementation extends Fleche {

    /**
     * Constructeur de la classe
     * @param model Le model de l'application
     * @param relation La relation entre les deux classes
     */
    public FlecheImplementation(Model model, Relation relation){
        super(model, relation);
        this.line.setStroke(Color.GREEN);
        this.arrow1.setStroke(Color.GREEN);
        this.arrow2.setStroke(Color.GREEN);
    }
}
