package org.teamtree.objectaid.Classe;

import org.teamtree.objectaid.Accessibilite.Accessibilite;
import org.teamtree.objectaid.Etat.Etat;
import org.teamtree.objectaid.Entite.Entite;
import org.teamtree.objectaid.Fabrique.FabriqueAccessibilite;
import org.teamtree.objectaid.Fabrique.FabriqueEntite;
import org.teamtree.objectaid.Fabrique.FabriqueEtat;

import java.util.ArrayList;

/**
 * Classe qui représente la définition d'une classe
 */
public class DefinitionClasse {

    /** Nom de la classe */
    private final String nomClasse;

    /** L'accessibilité de la classe */
    private final Accessibilite accessibilite;

    /** Liste des états de la classe */
    private final ArrayList<Etat> etats;

    /** Entité de la classe */
    private final Entite entite;

    /**
     * Constructeur de la définition de la classe
     * @param classe Classe
     */
    public DefinitionClasse(Class<?> classe) {
        // Recupération du nom de la classe
        this.nomClasse = classe.getSimpleName();

        // Accessibilite
        FabriqueAccessibilite fabriqueAccessibilite = new FabriqueAccessibilite();
        this.accessibilite = fabriqueAccessibilite.getAccessibilite(classe.getModifiers());

        // Etats
        FabriqueEtat fabriqueEtat = new FabriqueEtat();
        this.etats = fabriqueEtat.getEtat(classe.getModifiers());

        // Entite
        FabriqueEntite fabriqueEntite = new FabriqueEntite();
        this.entite = fabriqueEntite.getEntite(classe);
    }

    /**
     * Constructeur permettant de crée une définition de classe à partir de l'application
     * @param nomClasse nom de la classe
     * @param accessibilite accesibilite de la classe
     * @param etats etats de la classe
     * @param entite entite de la classe
     */
    public DefinitionClasse(String nomClasse, Accessibilite accessibilite, ArrayList<Etat> etats, Entite entite){

        this.nomClasse = nomClasse;
        this.accessibilite = accessibilite;
        this.etats = etats;
        this.entite = entite;
    }

    /**
     * Retourne le nom de la classe
     * @return Nom de la classe : String
     */
    public String getNom() {
        return nomClasse;
    }

    /**
     * Retourne l'accessibilité de la classe
     * @return Accessibilité de la classe : Accessibilite
     */
    public String getAccessibilite() {
        return accessibilite.getAcces();
    }

    /**
     * Retourne la liste des états de la classe
     * @return Liste des états de la classe : ArrayList<Etat>
     */
    public ArrayList<Etat> getEtats() {
        return etats;
    }

    /**
     * Retourne l'entité de la classe
     * @return Entité de la classe : Entite
     */
    public String getEntite() {
        return entite.getEntite();
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        if (etats.size() > 0) {
            for (Etat etat : etats) {
                info.append(" ").append(etat.getEtat().replace("{", "").replace("}", ""));
            }
        }
        info.append(" ").append(entite.getEntite()).append(" ").append(nomClasse);
        return info + "\n";
    }


    /**
     * Retourne l'uml de la classe
     * @return L'uml de la classe
     */
    public String getUml() {
        StringBuilder uml = new StringBuilder();
        if (etats.size() > 0 && ! entite.getEntite().equals("interface")) {
            for (Etat etat : etats) {
                uml.append(etat.getUml().replace("{", "").replace("}", "")).append(" ");
            }
        }
        uml.append(entite.getEntite()).append(" ").append(nomClasse);
        return uml.toString();
     }
}
