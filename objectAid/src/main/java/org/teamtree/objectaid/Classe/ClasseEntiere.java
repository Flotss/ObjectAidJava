package org.teamtree.objectaid.Classe;

import org.teamtree.objectaid.Point;

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

    private Point coordonnees;

    public ClasseEntiere(String path) throws ClassNotFoundException {
        Class<?> classe = Class.forName(path);

        this.attributes = new ArrayList<>();
        this.methods = new ArrayList<>();
        this.contructeurs = new ArrayList<>();
        this.definition = new DefinitionClasse(classe);
        this.coordonnees = new Point(0, 0);

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
        setX(x);
        setY(y);
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
                info += "\t\t" + methode.toString();
            }
        }

        return info;
    }

    public int getX() {
        return coordonnees.getX();
    }

    public int getY() {
        return coordonnees.getY();
    }

    public void setX(int x) {
        coordonnees.setX(x);
    }

    public void setY(int y) {
        coordonnees.setY(y);
    }
}
