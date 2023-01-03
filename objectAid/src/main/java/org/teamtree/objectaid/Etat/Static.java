package org.teamtree.objectaid.Etat;

/**
 * Classe qui représente l'état Final
 */
public class Static implements Etat{
    @Override
    public String getEtat() {
        return "static";
    }
}
