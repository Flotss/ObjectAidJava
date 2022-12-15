package main.java.org.teamtree.objectaid;

import org.teamtree.objectaid.Etat;

public class Methode {
    private final String typeRetourne;
    private final String nom;

    private final Parametre parametre;
    private final Etat etat;
    private final Accessibilite accessibilite;

    public Methode(String typeRetourne, String nom, Parametre parametre, Etat etat, Accessibilite accessibilite) {
        this.typeRetourne = typeRetourne;
        this.nom = nom;
        this.parametre = parametre;
        this.etat = etat;
        this.accessibilite = accessibilite;
    }

    public String getTypeRetourne() {
        return typeRetourne;
    }

    public String getNom() {
        return nom;
    }

    public String getParametre() {
        return parametre.getType();
    }

    public String getEtat() {
        return etat.getEtat();
    }

    public String getAccessibilite() {
        return accessibilite.getAcces();
    }
}
