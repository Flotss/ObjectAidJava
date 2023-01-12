package org.teamtree.objectaid.Etat;

/**
 * Classe qui représente l'état Final
 */
public class Final implements Etat{
    @Override
    public String getEtat() {
        return "final";
    }

    @Override
    public String getUml(){
        return "{final}";
    }

    @Override
    public String toString(){
        return "Final";
    }
}
