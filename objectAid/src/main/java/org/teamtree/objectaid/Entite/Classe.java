package org.teamtree.objectaid.Entite;

/**
 * Classe qui représente l'entité Classe
 */
public class Classe implements Entite {
    /**
     * Le nom de la classe
     */
    @Override
    public String getEntite() {
        return "class";
    }

    /**
     * Le nom de la classe
     */
    @Override
    public String toString() {
        return "Classe";
    }
}
