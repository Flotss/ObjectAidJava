package org.teamtree.objectaid.Accessibilite;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 * Classe qui représente l'accessibilité : Protected
 */
public class Protected implements Accessibilite {
    /**
     * Méthode qui retourne le nom de l'accessibilité
     *
     * @return Le nom de l'accessibilité
     */
    @Override
    public String getAcces() {
        return "protected";
    }

    /**
     * Méthode qui retourne la forme de l'accessibilité
     *
     * @return La forme de l'accessibilité
     */
    @Override
    public Shape getShape() {
        // Création d'un losange jaune
        Polygon losange = new Polygon();
        losange.getPoints().addAll(0.0, 2.5, 2.5, 0.0, 5.0, 2.5, 2.5, 5.0);
        losange.setFill(Color.YELLOW);
        losange.setStroke(Color.YELLOWGREEN);
        losange.setTranslateY(5);
        losange.setTranslateX(-3);
        return losange;
    }

    /**
     * Méthode qui retroune la chaine d'information
     *
     * @return La chaine d'information
     */
    public String toString() {
        return "Protected";
    }

    /**
     * Méthode qui retourne la chaine de caractère de l'accessibilité en Uml
     *
     * @return La chaine de caractère de l'accessibilité en Uml
     */
    public String getUml() {
        return "#";
    }
}
