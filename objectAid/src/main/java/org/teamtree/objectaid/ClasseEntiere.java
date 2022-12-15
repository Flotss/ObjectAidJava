package org.teamtree.objectaid;

import java.util.ArrayList;
import java.util.List;

public class ClasseEntiere {
    private final String name;
    private final List<Attribut> attributes;
    private final List<Methode> methods;

    public ClasseEntiere(final String name) {
        this.name = name;
        this.attributes = new ArrayList<>();
        this.methods = new ArrayList<>();
    }

    public void deplacer(final int x, final int y) {
        // TODO
    }

    public String getName() {
        return name;
    }

    public List<Attribut> getAttributes() {
        return attributes;
    }

    public List<Methode> getMethods() {
        return methods;
    }
}
