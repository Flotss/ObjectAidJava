package org.teamtree.objectaid.Fabrique;

import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import org.teamtree.objectaid.Classe.Relations.Relation;
import org.teamtree.objectaid.MVC.Fleches.Fleche;
import org.teamtree.objectaid.MVC.Fleches.FlecheAssociation;
import org.teamtree.objectaid.MVC.Model.Model;

public class FlecheAggregation extends FlecheAssociation {

    private final Shape losange;

    public FlecheAggregation(Model model, Relation relation) {
        super(model, relation);
        this.losange = new Polygon(0, 0, 10, 5, 0, 10);
        this.losange.setFill(Color.BLACK);

        this.getChildren().add(losange);

        actualiser();
    }

    @Override
    public void actualiser(){
        super.actualiser();

        CubicCurve courbe = (CubicCurve) this.getChildren().get(0);

        double x = courbe.getEndX();
        double y = courbe.getEndY();

        losange.setTranslateX(x);
        losange.setTranslateY(y);
    }
}
