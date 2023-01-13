package org.teamtree.objectaid.Accessibilite;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Classe qui représente l'accessibilité : Private
 */
public class Private implements Accessibilite {
    @Override
    public String getAcces() {
        return "private";
    }

    @Override
    public Shape getShape() {
        // Création d'un carré
        Rectangle rectangle = new Rectangle(5, 5);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.RED);
        rectangle.setTranslateY(7);
        rectangle.setTranslateX(-3);
        return rectangle;
    }

    /**
     * Méthode qui retroune la chaine d'information
     *
     * @return La chaine d'information
     */
    @Override
    public String toString() {
        return "Private";
    }

    /**
     * Méthode qui retourne la chaine de caractère de l'accessibilité en Uml
     *
     * @return La chaine de caractère de l'accessibilité en Uml
     */
    public String getUml() {
        return "-";
    }
}
