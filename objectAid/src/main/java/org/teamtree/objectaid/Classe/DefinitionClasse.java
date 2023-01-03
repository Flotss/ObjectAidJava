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
        String info = accessibilite.getAcces();
        if (etats.size() > 0) {
            for (Etat etat : etats) {
                info += " " + etat.getEtat();
            }
        }
        info += " " + entite.getEntite() + " " + nomClasse;
        return info + "\n";
    }


}
