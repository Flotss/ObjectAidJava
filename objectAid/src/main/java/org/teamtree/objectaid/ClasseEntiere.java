package org.teamtree.objectaid;

import java.util.ArrayList;
import java.util.List;

public class ClasseEntiere {
    private final String name;
    private List<Attribut> attributes;
    private List<Methode> methods;
    private DefinitionClasse definition;

    public ClasseEntiere(final String name) {
        this.name = name;
        this.attributes = new ArrayList<>();
        this.methods = new ArrayList<>();
    }

    public DefinitionClasse getDefinition() {
        return definition;
    }

    public void setDefinition(DefinitionClasse definition) {
        this.definition = definition;
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

    public void setAttributes(List<Attribut> attributes) {
        this.attributes = attributes;
    }

    public List<Methode> getMethods() {
        return methods;
    }

    public void setMethods(List<Methode> methods) {
        this.methods = methods;
    }
}
