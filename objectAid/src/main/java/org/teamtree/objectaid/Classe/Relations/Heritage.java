package org.teamtree.objectaid.Classe.Relations;

public class Heritage extends Relation {
    private String nom;

    public Heritage(String nom) {
        super(nom);
    }

    @Override
    public String toString() {
        return "Heritage: " + getNom();
    }
}
