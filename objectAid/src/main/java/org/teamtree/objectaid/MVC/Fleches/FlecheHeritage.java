package org.teamtree.objectaid.MVC.Fleches;

import javafx.scene.paint.Color;
import org.teamtree.objectaid.Classe.Relations.Relation;
import org.teamtree.objectaid.MVC.Model.Model;

public class FlecheHeritage extends Fleche {

    public FlecheHeritage(Model model, Relation relation){
        super(model, relation);
        this.setStroke(Color.RED);
    }
}
