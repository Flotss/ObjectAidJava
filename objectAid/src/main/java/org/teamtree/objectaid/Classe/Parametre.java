package org.teamtree.objectaid.Classe;

import java.lang.reflect.Parameter;

public class Parametre {
    private final String type;
    private final String nom;

    public Parametre(Parameter param) {
        this.type = param.getType().getSimpleName().trim();
        this.nom = param.getName();
    }

    public String getType() {
        return type;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return type + " " + nom;
    }
}
