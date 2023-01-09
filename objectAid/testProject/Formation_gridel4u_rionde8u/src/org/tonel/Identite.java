package org.tonel;

import java.util.Objects;

public class Identite {
    private String nip;
    private String nom;
    private String prenom;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Identite identite = (Identite) o;
        return Objects.equals(nip, identite.nip) && Objects.equals(nom,
            identite.nom) && Objects.equals(prenom, identite.prenom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nip, nom, prenom);
    }

    public Identite(String nip, String nom, String prenom) {
        this.nip = nip;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getNip() {
        return nip;
    }
}
