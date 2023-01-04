package org.teamtree.objectaid.Classe.Relations;

public abstract class Relation {
    private String nom;

    public Relation(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public abstract String toString();
}
