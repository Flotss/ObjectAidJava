package org.teamtree.objectaid.Classe;

import org.teamtree.objectaid.Accessibilite.Accessibilite;
import org.teamtree.objectaid.Fabrique.FabriqueAccessibilite;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

public class Constructeur {

    private final String nom;
    private final Accessibilite accessibilite;
    private final ArrayList<Parametre> parametre;

    public Constructeur (Constructor<?> construct) {
        this.nom = construct.getName();

        FabriqueAccessibilite fabriqueAccess = new FabriqueAccessibilite();
        this.accessibilite = fabriqueAccess.getAccessibilite(construct.getModifiers());

        this.parametre = new ArrayList<>();
        for (Parameter parameter : construct.getParameters()) {
            this.parametre.add(new Parametre(parameter));
        }
    }

    public String getNom() {
        return nom;
    }

    public String getAccessibilite() {
        return accessibilite.getAcces();
    }

    public ArrayList<Parametre> getParametre() {
        return parametre;
    }

}