package org.teamtree.objectaid.Fabrique;

import org.teamtree.objectaid.Accessibilite.*;

import java.lang.reflect.Modifier;
/**
 * Classe qui permet de créer une instance d'accessibilité
 */
public class FabriqueAccessibilite {

    /**
     * Méthode qui permet de créer une instance d'accessibilité
     * grâce à l'entier qui représente le modifier
     * @param modificateur Entier qui représente le modifier
     * @return Instance d'accessibilité
     */
    public Accessibilite getAccessibilite(int modificateur) {
        if (Modifier.isPublic(modificateur)) {
            return new Public();
        } else if (Modifier.isProtected(modificateur)) {
            return new Protected();
        } else if (Modifier.isPrivate(modificateur)) {
            return new Private();
        } else {
            return new Default();
        }
    }

    public Accessibilite getAccessibilite(String modificateur) {
        switch (modificateur){
            case "Public":
                return new Public();
            case "Protected":
                return new Protected();
            case "Private":
                return new Private();
            default:
                return new Default();
        }
    }
}
