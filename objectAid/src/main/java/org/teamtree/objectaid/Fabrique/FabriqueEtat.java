package org.teamtree.objectaid.Fabrique;

import org.teamtree.objectaid.Etat.Abstract;
import org.teamtree.objectaid.Etat.Etat;
import org.teamtree.objectaid.Etat.Final;
import org.teamtree.objectaid.Etat.Static;

import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class FabriqueEtat {

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
