package org.teamtree.objectaid.MVC.Fleches;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.teamtree.objectaid.Classe.Relations.Relation;
import org.teamtree.objectaid.MVC.Fleches.FlecheAssociation;
import org.teamtree.objectaid.MVC.Model.Model;

public class FlecheComposition extends FlecheAssociation {

    private final Polygon losange;

    public FlecheComposition(Model model, Relation relation) {
        super(model, relation);

        this.losange = new Polygon();
        losange.getPoints().addAll(   0.0,10.0,
                10.0, 0.0,
                20.0, 10.0,
                10., 20.0);
        losange.setFill(Color.BLACK);

        this.getChildren().addAll(losange);

        actualiser();
    }

    @Override
    public void actualiser() {
        super.actualiser();
        actualiserLosange();
    }


    private void actualiserLosange() {
        // L'angle de la fleche
        double angle = Math.atan2(line.getEndY() - line.getStartY(), line.getEndX() - line.getStartX());

        // Rotation du losange
        losange.setRotate(Math.toDegrees(angle) - 90);

        // Position du losange
        losange.setTranslateX(line.getStartX() - 10);
        losange.setTranslateY(line.getStartY() - 10);
    }

}
