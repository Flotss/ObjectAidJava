package org.teamtree.objectaid.Classe;

import javafx.scene.layout.VBox;

/**
 * Classe qui représente l'affichage d'une classe
 */
public class ClasseAffichage extends VBox {

    /**
     * Nom de cette classe, permettant de la retrouver lorsqu'on clique dessus notamment
     */
    private String nom;

    public ClasseAffichage(String nom){
        this.nom = nom;
    }

    public String getNom(){
        return this.nom;
    }

}
