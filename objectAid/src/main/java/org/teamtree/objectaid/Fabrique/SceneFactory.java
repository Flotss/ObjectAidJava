package org.teamtree.objectaid.Fabrique;

import javafx.scene.Scene;

/**
 * Interface qui sera implémentée par les classes qui fabriqueront des scènes
 */
public interface SceneFactory {

    /**
     * Méthode qui fabrique une scène
     *
     * @return La scène : Scene
     */
    Scene getScene();
}
