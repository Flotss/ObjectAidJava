package org.teamtree.objectaid.Classe;

import javafx.scene.layout.VBox;

public class ClasseAffichage extends VBox {
    String nom;
    public ClasseAffichage(String nom){
        this.nom = nom;
    }

    public String getNom(){
        return this.nom;
    }
}
