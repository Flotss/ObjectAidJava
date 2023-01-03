package org.teamtree.objectaid.Classe;

import org.teamtree.objectaid.Accessibilite.Accessibilite;
import org.teamtree.objectaid.Etat.Etat;
import org.teamtree.objectaid.Fabrique.FabriqueAccessibilite;
import org.teamtree.objectaid.Fabrique.FabriqueEtat;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Classe qui représente un attribut d'une classe
 */
public class Attribut {

    /** Nom de l'attribut */
    private final String nom;

    /** Type de l'attribut */
    private final String type;

    /** Liste des états de l'attribut */
    private final ArrayList<Etat> etat;

    /** Accessibilité de l'attribut */
    private final Accessibilite accessibilite;

    /**
     * Constructeur de l'attribut
     * @param field Attribut de la classe
     */
    public Attribut(Field field) {
        this.type = field.getType().getSimpleName().trim();
        this.nom = field.getName();

        FabriqueEtat fabriqueEtat = new FabriqueEtat();
        this.etat = fabriqueEtat.getEtat(field.getModifiers());

        FabriqueAccessibilite fabriqueAccessibilite = new FabriqueAccessibilite();
        this.accessibilite = fabriqueAccessibilite.getAccessibilite(field.getModifiers());
    }

    /**
     * Retourne le type de l'attribut
     * @return Type de l'attribut : String
     */
    public String getType() {
        return type;
    }

    /**
     * Retourne le nom de l'attribut
     * @return Nom de l'attribut : String
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne la liste des états de l'attribut
     * @return Liste des états de l'attribut : ArrayList<Etat>
     */
    public ArrayList<Etat> getEtat() {
        return etat;
    }

    /**
     * Retourne l'accessibilité de l'attribut
     * @return Accessibilité de l'attribut : Accessibilite
     */
    public String getAccessibilite() {
        return accessibilite.getAcces();
    }

    @Override
    public String toString() {
        String info = accessibilite.getAcces();
        if (etat.size() > 0) {
            for (Etat etat : etat) {
                info += " " + etat.getEtat();
            }
        }
        info += " " + type + " " + nom;
        return info + "\n";
    }
}
