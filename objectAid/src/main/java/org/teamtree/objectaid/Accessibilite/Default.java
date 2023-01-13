package org.teamtree.objectaid.Accessibilite;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 * Classe qui représente l'accessibilité : Default
 */
public class Default implements Accessibilite {

    /**
     * Méthode qui retourne le nom de l'accessibilité
     *
     * @return Le nom de l'accessibilité
     */
    @Override
    public String getAcces() {
        return "default";
    }


    /**
     * Méthode qui retourne la forme de l'accessibilité
     *
     * @return La forme de l'accessibilité
     */
    @Override
    public Shape getShape() {
        // Création d'un triangle bleu
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(0.0, 0.0, 5.0, 0.0, 2.5, 5.0);
        triangle.setFill(Color.BLUE);
        triangle.setStroke(Color.BLUEVIOLET);
        triangle.setTranslateY(5);
        triangle.setTranslateX(-3);
        return triangle;
    }


    /**
     * Méthode qui retroune la chaine d'information
     *
     * @return La chaine d'information
     */
    @Override
    public String toString() {
        return "Default";
    }

    /**
     * Méthode qui retourne la chaine de caractère de l'accessibilité en Uml
     *
     * @return La chaine de caractère de l'accessibilité en Uml
     */
    public String getUml() {
        return "";
    }
}
