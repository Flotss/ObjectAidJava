package org.teamtree.objectaid.Fabrique;

import org.teamtree.objectaid.Entite.*;
import org.teamtree.objectaid.Entite.Enum;
import org.teamtree.objectaid.Entite.Record;

public class FabriqueEntite {

    public Entite creerEntite(Class<?> classe){
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
