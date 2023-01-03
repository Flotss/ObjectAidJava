package org.teamtree.objectaid.Fabrique;

import org.teamtree.objectaid.Etat.Abstract;
import org.teamtree.objectaid.Etat.Etat;
import org.teamtree.objectaid.Etat.Final;
import org.teamtree.objectaid.Etat.Static;

import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * Classe qui permet de créer une instance d'état
 */
public class FabriqueEtat {

    /**
     * Méthode qui permet de créer une instance d'état
     * grâce à l'entier qui représente le modifier
     * @param modificateur Entier qui représente le modifier
     * @return Instance d'état
     */
    public ArrayList<Etat> getEtat(int modificateur) {
        ArrayList<Etat> etat = new ArrayList<>();
        if (Modifier.isFinal(modificateur)) {
            etat.add(new Final());
        }
        if (Modifier.isStatic(modificateur)) {
            etat.add(new Static());
        }
        if (Modifier.isAbstract(modificateur)) {
            etat.add(new Abstract());
        }
        return etat;
    }
}
