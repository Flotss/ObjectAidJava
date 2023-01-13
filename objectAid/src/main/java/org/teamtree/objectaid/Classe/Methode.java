package org.teamtree.objectaid.Classe;

import org.teamtree.objectaid.Accessibilite.Accessibilite;
import org.teamtree.objectaid.Etat.Etat;
import org.teamtree.objectaid.Fabrique.FabriqueAccessibilite;
import org.teamtree.objectaid.Fabrique.FabriqueEtat;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

/**
 * Classe qui représente une méthode d'une classe
 */
public class Methode {

    /**
     * Nom de la méthode
     */
    private final String nom;

    /**
     * Type de retour de la méthode
     */
    private final String typeRetourne;

    /**
     * Liste des paramètres de la méthode
     */
    private final ArrayList<Parametre> parametre;

    /**
     * Liste des états de la méthode
     */
    private final ArrayList<Etat> etats;

    /**
     * Accessibilité de la méthode
     */
    private final Accessibilite accessibilite;

    /**
     * Constructeur de la méthode
     *
     * @param method Méthode de la classe
     */
    public Methode(Method method) {
        // Nom de la methode
        this.nom = method.getName();

        // Type de retour
        String typeRetourne = method.getGenericReturnType().getTypeName();
        if (typeRetourne.contains("<")) {
            String[] typeRetourneSplit = typeRetourne.split("<");
            String part1 = typeRetourneSplit[0].substring(typeRetourneSplit[0].lastIndexOf(".") + 1);
            String part2 = typeRetourneSplit[1].substring(typeRetourneSplit[1].lastIndexOf(".") + 1, typeRetourneSplit[1].length() - 1);
            this.typeRetourne = part1 + "<" + part2 + ">";
        } else {
            this.typeRetourne = typeRetourne.substring(typeRetourne.lastIndexOf(".") + 1);
        }


        // Accessibilite : public, private, protected, default
        FabriqueAccessibilite fabriqueAccessibilite = new FabriqueAccessibilite();
        this.accessibilite = fabriqueAccessibilite.getAccessibilite(method.getModifiers());


        // Parametres de la methode
        this.parametre = new ArrayList<>();
        for (Parameter parameter : method.getParameters()) {
            this.parametre.add(new Parametre(parameter));
        }


        // Etats de la methode : static, final, abstract
        FabriqueEtat fabriqueEtat = new FabriqueEtat();
        this.etats = fabriqueEtat.getEtat(method.getModifiers());
    }

    /**
     * Constructeur de la méthode
     *
     * @param nom           Nom de la méthode
     * @param typeRetourne  Type de retour de la méthode
     * @param accessibilite Accessibilité de la méthode
     * @param parametres    Les paramètres de la méthode (sous forme de String)
     */
    public Methode(String nom, String typeRetourne, Accessibilite accessibilite, String parametres, ArrayList<Etat> modifiers) {
        // Nom de la methode
        this.nom = nom;

        // Type de retour
        if (typeRetourne.contains("<")) {
            String[] typeRetourneSplit = typeRetourne.split("<");
            String part1 = typeRetourneSplit[0].substring(typeRetourneSplit[0].lastIndexOf(".") + 1);
            String part2 = typeRetourneSplit[1].substring(typeRetourneSplit[1].lastIndexOf(".") + 1, typeRetourneSplit[1].length() - 1);
            this.typeRetourne = part1 + "<" + part2 + ">";
        } else {
            this.typeRetourne = typeRetourne.substring(typeRetourne.lastIndexOf(".") + 1);
        }
        System.out.println("Type de retour : " + this.typeRetourne);

        // Accessibilite : public, private, protected, default
        this.accessibilite = accessibilite;

        // Parametres de la methode
        this.parametre = new ArrayList<>();
        String[] suiteDeParametre = parametres.split(",");
        for (String parameter : suiteDeParametre) {
            String[] parametreUnique = parameter.split(":");
            if (parametreUnique.length == 2) {
                this.parametre.add(new Parametre(parametreUnique[0], parametreUnique[1]));
            }
        }


        // Etats de la methode : static, final, abstract
        this.etats = modifiers;
    }

    /**
     * Retourne le type de retour de la méthode
     *
     * @return Type de retour de la méthode : String
     */
    public String getTypeRetourne() {
        return typeRetourne;
    }

    /**
     * Retourne le nom de la méthode
     *
     * @return Nom de la méthode : String
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne la liste des paramètres de la méthode
     *
     * @return Liste des paramètres de la méthode : ArrayList<Parametre>
     */
    public ArrayList<Parametre> getParametre() {
        return parametre;
    }

    /**
     * Retourne la liste des états de la méthode
     *
     * @return Liste des états de la méthode : ArrayList<Etat>
     */
    public ArrayList<Etat> getEtats() {
        return etats;
    }

    /**
     * Retourne l'accessibilité de la méthode
     *
     * @return Accessibilité de la méthode : Accessibilite
     */
    public Accessibilite getAccessibilite() {
        return accessibilite;
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder(accessibilite.getAcces());
        if (etats.size() > 0) {
            for (Etat etat : etats) {
                info.append(" ").append(etat.getEtat());
            }
        }
        info.append(" ").append(nom).append("(");
        if (parametre.size() > 0) {
            for (Parametre param : parametre) {
                info.append(param.toString()).append(", ");
            }
            info = new StringBuilder(info.substring(0, info.length() - 2));
        }
        info.append(") : ").append(typeRetourne);

        return info + "\n";
    }

    /**
     * Retourne l'uml de la méthode
     *
     * @return Uml de la méthode : String
     */
    public String getUml() {
        StringBuilder info = new StringBuilder(accessibilite.getUml());
        if (etats.size() > 0) {
            for (Etat etat : etats) {
                info.append(" ").append(etat.getUml());
            }
        }
        info.append(" ").append(nom).append("(");
        if (parametre.size() > 0) {
            for (Parametre param : parametre) {
                info.append(param.getUml()).append(", ");
            }
            info = new StringBuilder(info.substring(0, info.length() - 2));
        }
        info.append(") : ").append(typeRetourne);

        return info.toString();
    }
}
