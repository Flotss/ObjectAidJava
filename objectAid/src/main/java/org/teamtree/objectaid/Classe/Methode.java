package org.teamtree.objectaid.Classe;

import org.teamtree.objectaid.Etat.Etat;
import org.teamtree.objectaid.Accessibilite.Accessibilite;
import org.teamtree.objectaid.Fabrique.FabriqueAccessibilite;
import org.teamtree.objectaid.Fabrique.FabriqueEtat;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

/**
 * Classe qui représente une méthode d'une classe
 */
public class Methode {

    /** Nom de la méthode */
    private final String nom;

    /** Type de retour de la méthode */
    private final String typeRetourne;

    /** Liste des paramètres de la méthode */
    private final ArrayList<Parametre> parametre;

    /** Liste des états de la méthode */
    private final ArrayList<Etat> etats;

    /** Accessibilité de la méthode */
    private final Accessibilite accessibilite;

    /**
     * Constructeur de la méthode
     * @param method Méthode de la classe
     */
    public Methode(Method method) {
        // Nom de la methode
        this.nom = method.getName();

        // Type de retour
        this.typeRetourne = method.getReturnType().getSimpleName();


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
     * Retourne le type de retour de la méthode
     * @return Type de retour de la méthode : String
     */
    public String getTypeRetourne() {
        return typeRetourne;
    }

    /**
     * Retourne le nom de la méthode
     * @return Nom de la méthode : String
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne la liste des paramètres de la méthode
     * @return Liste des paramètres de la méthode : ArrayList<Parametre>
     */
    public ArrayList<Parametre> getParametre() {
        return parametre;
    }

    /**
     * Retourne la liste des états de la méthode
     * @return Liste des états de la méthode : ArrayList<Etat>
     */
    public ArrayList<Etat> getEtats() {
        return etats;
    }

    /**
     * Retourne l'accessibilité de la méthode
     * @return Accessibilité de la méthode : Accessibilite
     */
    public Accessibilite getAccessibilite() {
        return accessibilite;
    }

    @Override
    public String toString() {
        String info = accessibilite.getAcces();
        if (etats.size() > 0){
            for (Etat etat : etats) {
                info += " " + etat.getEtat();
            }
        }
        info += " " + nom + "(";
        if (parametre.size() > 0) {
            for (Parametre param : parametre) {
                info += param.toString() + ", ";
            }
            info = info.substring(0, info.length() - 2);
        }
        info += ") : " + typeRetourne;

        return info + "\n";
    }
}
