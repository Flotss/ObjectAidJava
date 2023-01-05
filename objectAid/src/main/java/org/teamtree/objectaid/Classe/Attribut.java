package org.teamtree.objectaid.Classe;

import org.teamtree.objectaid.Accessibilite.Accessibilite;
import org.teamtree.objectaid.Etat.Etat;
import org.teamtree.objectaid.Fabrique.FabriqueAccessibilite;
import org.teamtree.objectaid.Fabrique.FabriqueEtat;
import org.teamtree.objectaid.Point;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

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
        // Est-ce que le type hérite de Collection
        if (Collection.class.isAssignableFrom(field.getType())) {
            // Si oui, on récupère le type de la collection
            String nomField= field.getGenericType().getTypeName().trim();

            // Ici l'on a un problème de nom, puisqu'il est du type "java.util.List<org.teamtree.objectaid.Classe.ClasseEntiere>"
            // Alors on ne garde que le nom de la classe
            String[] nomFieldSplit = nomField.split("<");
            String nomFieldPart1 = nomFieldSplit[0].substring(nomFieldSplit[0].lastIndexOf(".") + 1);
            String nomFieldPart2 = nomFieldSplit[1].substring(nomFieldSplit[1].lastIndexOf(".") + 1, nomFieldSplit[1].length() - 1);

            // On a alors le part1 = List, part2 = ClasseEntiere
            // On doit ajouter <> pour que le nom soit complet
            this.type = nomFieldPart1 + "<" + nomFieldPart2 + ">";
        }else{
            this.type = field.getType().getSimpleName().trim();
        }

        // Affecte le nom de l'attribut
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
