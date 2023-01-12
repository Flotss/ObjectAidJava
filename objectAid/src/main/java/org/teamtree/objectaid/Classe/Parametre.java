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
        String type = param.getType().toGenericString();
        if (type.contains("<")) {
            String [] typeRetourneSplit = type.split("<");
            String part1 = typeRetourneSplit[0].substring(typeRetourneSplit[0].lastIndexOf(".") + 1);
            String part2 = typeRetourneSplit[1].substring(typeRetourneSplit[1].lastIndexOf(".")+1, typeRetourneSplit[1].length() - 1);
            this.type = part1 + "<" + part2 + ">";
        } else {
            this.type = type.substring(type.lastIndexOf(".") + 1);
        }
        this.nom = param.getName();
    }

    /**
     * Constructeur du paramètre
     * @param param Le paramètre de la méthode/constructeur
     */
    public Parametre(String nom, String type) {
        if (type.contains("<")) {
            String [] typeRetourneSplit = type.split("<");
            String part1 = typeRetourneSplit[0].substring(typeRetourneSplit[0].lastIndexOf(".") + 1);
            String part2 = typeRetourneSplit[1].substring(typeRetourneSplit[1].lastIndexOf(".")+1, typeRetourneSplit[1].length() - 1);
            this.type = part1 + "<" + part2 + ">";
        } else {
            this.type = type.substring(type.lastIndexOf(".") + 1);
        }
        this.nom = nom;
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

    /**
     * Retourne l'uml du paramètre
     * @return Uml du paramètre : String
     */
    public String getUml() {
        return nom + " : " + type;
    }
}
