package org.teamtree.objectaid.Fabrique;

import org.teamtree.objectaid.Entite.*;
import org.teamtree.objectaid.Entite.Enum;
import org.teamtree.objectaid.Entite.Record;

/**
 * Classe qui permet de créer une instance d'entité
 */
public class FabriqueEntite {

    /**
     * Méthode qui permet de créer une instance d'entité
     * grâce à la classe
     * @param classe La classe : Class<?>
     * @return Instance d'entité
     */
    public Entite getEntite(Class<?> classe){
        if (classe.isInterface()){
            return new Interface();
        }
        else if (classe.isEnum()){
            return new Enum();
        }
        else if (classe.isRecord()){
            return new Record();
        }
        else {
            return new Classe();
        }
    }
}
