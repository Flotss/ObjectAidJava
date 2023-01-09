package org.teamtree.objectaid.Accessibilite;

import javafx.scene.shape.Shape;

/**
 * Classe qui représente l'accessibilité
 * Exemple : public, private, protected, default
 */
public interface Accessibilite {

    /**
     * Méthode qui retourne l'accessibilité
     * @return L'accessibilité
     */
    public String getAcces();

    /**
     * Méthode qui retourne la forme de l'accessibilité
     * @return La forme de l'accessibilité
     */
    public Shape getShape();
}
