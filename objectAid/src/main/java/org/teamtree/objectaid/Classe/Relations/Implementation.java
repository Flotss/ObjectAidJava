package org.teamtree.objectaid.Classe.Relations;

public class Implementation extends Relation {
    private String nom;

    public Implementation(String nom) {
        super(nom);
    }

    @Override
    public String toString() {
        return "Implementation: " + getNom();
    }
}
