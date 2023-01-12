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

    /**
     * Méthode qui retroune la chaine d'information
     * @return La chaine d'information
     */
    public String toString(){
        return "Public";
    }

    /**
     * Méthode qui retourne la chaine de caractère de l'accessibilité en Uml
     * @return La chaine de caractère de l'accessibilité en Uml
     */
    public String getUml(){
        return "+";
    }
}
