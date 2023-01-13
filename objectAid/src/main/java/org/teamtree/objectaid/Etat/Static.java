package org.teamtree.objectaid.Etat;

/**
 * Classe qui représente l'état Final
 */
public class Static implements Etat {
    /**
     * Méthode qui retourne l'état
     *
     * @return L'état : String
     */
    @Override
    public String getEtat() {
        return "static";
    }

    /**
     * Méthode qui retourne l'état sous forme UML
     *
     * @return L'état : String
     */
    @Override
    public String getUml() {
        return "{static}";
    }

    /**
     * Méthode qui retourne l'état
     *
     * @return L'état : String
     */
    @Override
    public String toString() {
        return "Static";
    }
}
