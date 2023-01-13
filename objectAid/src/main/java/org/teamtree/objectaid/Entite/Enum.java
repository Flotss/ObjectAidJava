package org.teamtree.objectaid.Entite;

/**
 * Classe qui représente l'entité Enumération
 */
public class Enum implements Entite{
    @Override
    public String getEntite() {
        return "enum";
    }

    @Override
    public String toString() {
        return "Enum";
    }
}
