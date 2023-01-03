package org.teamtree.objectaid.util;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Classe qui permet de créer une ligne de séparation
 */
public class LineSeparator extends Rectangle {

    /**
     * Constructeur de la classe LineSeparator
     * Qui crée une ligne de séparation
     * Donc un rectangle qui n'a pas de largeur
     */
    public LineSeparator() {
        setFill(Color.BLACK);
        setHeight(1);
        setWidth(100);
    }
}
