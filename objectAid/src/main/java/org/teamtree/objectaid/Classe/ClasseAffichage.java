package org.teamtree.objectaid.Classe;

import javafx.scene.layout.VBox;

/**
 * Classe qui représente l'affichage d'une classe
 */
public class ClasseAffichage extends VBox {
    String nom;
    public ClasseAffichage(String nom){
        this.nom = nom;
    }

    public String getNom(){
        return this.nom;
    }
}
