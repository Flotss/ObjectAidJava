package org.teamtree.objectaid.Etat;

/**
 * Classe qui représente l'état Final
 */
public class Final implements Etat {
    /**
     * Méthode qui retourne l'état
     *
     * @return L'état : String
     */
    @Override
    public String getEtat() {
        return "final";
    }

    /**
     * Méthode qui retourne l'état sous forme UML
     *
     * @return L'état : String
     */
    @Override
    public String getUml() {
        return "{final}";
    }

    /**
     * Méthode qui retourne l'état
     *
     * @return L'état : String
     */
    @Override
    public String toString() {
        return "Final";
    }
}
