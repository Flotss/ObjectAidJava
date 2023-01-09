package org.teamtree.objectaid.Classe;

import org.teamtree.objectaid.Accessibilite.Accessibilite;
import org.teamtree.objectaid.Fabrique.FabriqueAccessibilite;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

/**
 * Classe qui représente un constructeur d'une classe
 */
public class Constructeur {

    /** Nom du constructeur */
    private final String nom;

    /** L'accessibilité du constructeur */
    private final Accessibilite accessibilite;

    /** Liste des paramètres du constructeur */
    private final ArrayList<Parametre> parametre;

    /**
     * Constructeur du constructeur
     * @param constructor Constructeur de la classe
     */
    public Constructeur (Constructor<?> constructor) {
        this.nom = constructor.getName().substring(constructor.getName().lastIndexOf(".") + 1);

        FabriqueAccessibilite fabriqueAccess = new FabriqueAccessibilite();
        this.accessibilite = fabriqueAccess.getAccessibilite(constructor.getModifiers());

        this.parametre = new ArrayList<>();
        for (Parameter parameter : constructor.getParameters()) {
            this.parametre.add(new Parametre(parameter));
        }
    }

    /**
     * Retourne le nom du constructeur
     * @return Nom du constructeur : String
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne l'accessibilité du constructeur
     * @return Accessibilité du constructeur : Accessibilite
     */
    public Accessibilite getAccessibilite() {
        return accessibilite;
    }

    /**
     * Retourne la liste des paramètres du constructeur
     * @return Liste des paramètres du constructeur : ArrayList<Parametre>
     */
    public ArrayList<Parametre> getParametre() {
        return parametre;
    }

    @Override
    public String toString() {
        String info = accessibilite.getAcces();
        info += " " + nom + "(";
        if (parametre.size() > 0) {
            for (Parametre param : parametre) {
                info += param.toString() + ", ";
            }
            info = info.substring(0, info.length() - 2);
        }
        info += ")";
        return info + "\n";
    }

}
