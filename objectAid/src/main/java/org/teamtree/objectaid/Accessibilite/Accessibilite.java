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
    String getAcces();

    /**
     * Méthode qui retourne la forme de l'accessibilité
     * @return La forme de l'accessibilité
     */
    Shape getShape();

    /**
     * Méthode qui retourne le chaine de caractère de l'accessibilité en Uml
     * @return La chaine de caractère de l'accessibilité en Uml
     */
    String getUml();
}
