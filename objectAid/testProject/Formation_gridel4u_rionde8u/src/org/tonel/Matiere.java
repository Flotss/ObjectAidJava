package org.tonel;

import java.util.Objects;

public class Matiere {
    private final String nom;

    public Matiere(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matiere matiere = (Matiere) o;
        return Objects.equals(nom, matiere.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

    public String getNom() {
        return nom;
    }
}
