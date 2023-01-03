package org.teamtree.objectaid.Classe;

import java.lang.reflect.Parameter;

public class Parametre {

    /** Nom du paramètre */
    private final String nom;

    /** Type du paramètre */
    private final String type;

    /**
     * Constructeur du paramètre
     * @param param Le paramètre de la méthode/constructeur
     */
    public Parametre(Parameter param) {
        this.type = param.getType().getSimpleName().trim();
        this.nom = param.getName();
    }

    /**
     * Retourne le type du paramètre
     * @return Type du paramètre : String
     */
    public String getType() {
        return type;
    }

    /**
     * Retourne le nom du paramètre
     * @return Nom du paramètre : String
     */
    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return type + " " + nom;
    }
}
