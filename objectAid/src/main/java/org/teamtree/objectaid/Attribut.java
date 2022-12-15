package org.teamtree.objectaid;

import org.teamtree.objectaid.Accessibilite.Accessibilite;
import org.teamtree.objectaid.Etat.Etat;

public class Attribut {
    private final String type;
    private final String nom;
    private final Etat etat;
    private final Accessibilite accessibilite;

    public Attribut(String type, String nom, Etat etat, Accessibilite accessibilite) {
        this.type = type;
        this.nom = nom;
        this.etat = etat;
        this.accessibilite = accessibilite;
    }

    public String getType() {
        return type;
    }

    public String getNom() {
        return nom;
    }

    public String getEtat() {
        return etat.getEtat();
    }

    public String getAccessibilite() {
        return accessibilite.getAcces();
    }
}
