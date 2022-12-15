package org.teamtree.objectaid.Fabrique;

import org.teamtree.objectaid.Accessibilite.*;

import java.lang.reflect.Modifier;

public class FabriqueAccessibilite {

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
}
