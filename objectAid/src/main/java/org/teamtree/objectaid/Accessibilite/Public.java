package org.teamtree.objectaid.Accessibilite;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/**
 * Classe qui représente l'accessibilité : Public
 */
public class Public implements Accessibilite {
    @Override
    public String getAcces() {
        return "public";
    }

    @Override
    public Shape getShape() {
        // Création d'un cercle vert
        Circle cercle = new Circle(2.5);
        cercle.setFill(Color.GREEN);
        cercle.setStroke(Color.GREENYELLOW);
        cercle.setTranslateY(7);
        cercle.setTranslateX(-3);
        return cercle;
    }
}
