package org.teamtree.objectaid.Accessibilite;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 * Classe qui représente l'accessibilité : Protected
 */
public class Protected implements Accessibilite {
    @Override
    public String getAcces() {
        return "protected";
    }

    @Override
    public Shape getShape() {
        // Création d'un losange jaune
        Polygon losange = new Polygon();
        losange.getPoints().addAll(   0.0, 5.0,
                5.0, 0.0,
                10.0, 5.0,
                5.0, 10.0);
        losange.setFill(Color.YELLOW);
        losange.setStroke(Color.YELLOWGREEN);
        losange.setTranslateY(7);
        losange.setTranslateX(-3);
        return losange;
    }
}
