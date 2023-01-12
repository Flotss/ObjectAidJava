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

    public Constructeur (String nom, Accessibilite accesibilite, String parametres) {
        this.nom = nom;


        this.accessibilite = accesibilite;

        this.parametre = new ArrayList<>();
        String[] suiteDeParametre = parametres.split(",");
        for (String parameter : suiteDeParametre) {
            String[] parametreUnique = parameter.split(":");
            this.parametre.add(new Parametre(parametreUnique[0], parametreUnique[1]));
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
        StringBuilder info = new StringBuilder(accessibilite.getAcces());
        info.append(" ").append(nom).append("(");
        if (parametre.size() > 0) {
            for (Parametre param : parametre) {
                info.append(param.toString()).append(", ");
            }
            info = new StringBuilder(info.substring(0, info.length() - 2));
        }
        info.append(")");
        return info + "\n";
    }

    /**
     * Méthode qui retroune la forme UML du constructeur
     * @return Forme UML du constructeur : String
     */
    public String getUml() {
        StringBuilder info = new StringBuilder(accessibilite.getUml());
        info.append(" ").append(nom).append("(");
        if (parametre.size() > 0) {
            for (Parametre param : parametre) {
                info.append(param.getUml()).append(", ");
            }
            info = new StringBuilder(info.substring(0, info.length() - 2));
        }
        info.append(")");
        return info.toString();
    }

}
