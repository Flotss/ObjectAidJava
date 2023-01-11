package org.teamtree.objectaid.Accessibilite;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 * Classe qui représente l'accessibilité : Default
 */
public class Default implements Accessibilite {

    @Override
    public String getAcces() {
        return "default";
    }

    @Override
    public Shape getShape() {
        // Création d'un triangle bleu
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(  0.0, 0.0,
                5.0, 0.0,
                2.5, 5.0);
        triangle.setFill(Color.BLUE);
        triangle.setStroke(Color.BLUEVIOLET);
        triangle.setTranslateY(5);
        triangle.setTranslateX(-3);
        return triangle;
    }

    public String toString(){
        return "Default";
    }
}
