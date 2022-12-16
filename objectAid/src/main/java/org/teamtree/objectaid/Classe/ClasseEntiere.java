package org.teamtree.objectaid.Classe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ClasseEntiere {

    private List<Attribut> attributes;
    private List<Methode> methods;
    private final List<Constructeur> contructeurs;
    private DefinitionClasse definition;

    private int x;
    private int y;

    public ClasseEntiere(String path) throws ClassNotFoundException {
        Class<?> classe = Class.forName(path);

        this.attributes = new ArrayList<>();
        this.methods = new ArrayList<>();
        this.contructeurs = new ArrayList<>();
        this.definition = new DefinitionClasse(classe);
        this.x = 0;
        this.y = 0;

        // Attributs
        for (Field field : classe.getDeclaredFields()) {
            this.attributes.add(new Attribut(field));
        }

        // Constructeurs
        for (Constructor<?> constructor : classe.getDeclaredConstructors()) {
            this.contructeurs.add(new Constructeur(constructor));
        }

        // Methodes
        for (Method method : classe.getDeclaredMethods()) {
            this.methods.add(new Methode(method));
        }
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

    @Override
    public String toString() {
        String info = definition.toString();
        if (attributes.size() > 0) {
            info += "\tAttributs: \n";
            for (Attribut attribut : attributes) {
                info += "\t\t" + attribut.toString();
            }
        }
        if (contructeurs.size() > 0) {
            info += "\tConstructeurs: \n";
            for (Constructeur constructeur : contructeurs) {
                info += "\t\t" + constructeur.toString();
            }
        }

        if (methods.size() > 0) {
            info += "\tMethodes: \n";
            for (Methode methode : methods) {
                info += methode.toString();
            }
        }

        return info;
    }
}
