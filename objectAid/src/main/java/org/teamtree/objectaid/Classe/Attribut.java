package org.teamtree.objectaid.Classe;

import org.teamtree.objectaid.Accessibilite.Accessibilite;
import org.teamtree.objectaid.Etat.Etat;
import org.teamtree.objectaid.Fabrique.FabriqueAccessibilite;
import org.teamtree.objectaid.Fabrique.FabriqueEtat;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Attribut {
    private final String type;
    private final String nom;
    private final ArrayList<Etat> etat;
    private final Accessibilite accessibilite;

    public Attribut(Field field) {
        this.type = field.getType().getSimpleName().trim();
        this.nom = field.getName();

        FabriqueEtat fabriqueEtat = new FabriqueEtat();
        this.etat = fabriqueEtat.getEtat(field.getModifiers());

        FabriqueAccessibilite fabriqueAccessibilite = new FabriqueAccessibilite();
        this.accessibilite = fabriqueAccessibilite.getAccessibilite(field.getModifiers());
    }

    public String getType() {
        return type;
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<Etat> getEtat() {
        return etat;
    }

    public String getAccessibilite() {
        return accessibilite.getAcces();
    }

    @Override
    public String toString() {
        String info = accessibilite.toString();
        if (etat.size() > 0) {
            for (Etat etat : etat) {
                info += " " + etat.getEtat();
            }
        }
        info += " " + type + " " + nom;
        return info;
    }
}
