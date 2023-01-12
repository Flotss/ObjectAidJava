package org.teamtree.objectaid.Etat;

/**
 * Classe qui représente l'état Final
 */
public class Static implements Etat{
    @Override
    public String getEtat() {
        return "static";
    }

    @Override
    public String getUml(){
        return "{static}";
    }

    @Override
    public String toString(){
        return "Static";
    }
}
