package org.teamtree.objectaid.Classe;

import java.util.ArrayList;
import java.util.List;

public class ClasseEntiere {

    private List<Attribut> attributes;
    private List<Methode> methods;
    private final List<Constructeur> contructeurs;
    private DefinitionClasse definition;

    private int x;
    private int y;

    public ClasseEntiere() {
        this.attributes = new ArrayList<>();
        this.methods = new ArrayList<>();
        this.contructeurs = new ArrayList<>();
        this.x = 0;
        this.y = 0;
    }

    public DefinitionClasse getDefinition() {
        return definition;
    }

    public void setDefinition(DefinitionClasse definition) {
        this.definition = definition;
    }

    public void deplacer(final int x, final int y) {
        this.x += x;
        this.y += y;
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
