package org.teamtree.objectaid.Etat;

/**
 * Classe qui représente l'état Abstrait
 */
public class Abstract implements Etat {
    /**
     * Méthode qui retourne l'état
     *
     * @return L'état : String
     */
    @Override
    public String getEtat() {
        return "abstract";
    }

    /**
     * Méthode qui retourne l'état sous forme UML
     *
     * @return L'état : String
     */
    @Override
    public String getUml() {
        return "{abstract}";
    }

    /**
     * Méthode qui retourne l'état
     *
     * @return L'état : String
     */
    @Override
    public String toString() {
        return "Abstract";
    }
}
