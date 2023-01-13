package org.teamtree.objectaid.MVC.Model;

/**
 * Enumeration des différents états de l'application
 */
public enum ApplicationState {
    // État initial de l'application, quand aucun projet n'est ouvert.
    BOOTSTRAP,

    // État de l'application quand un projet est ouvert.
    PROJECT_LOADED,
}
