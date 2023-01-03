package org.teamtree.objectaid.Classe;

import org.teamtree.objectaid.Point;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui représente une classe
 */
public class ClasseEntiere {

    /**
     * Liste des constructeurs de la classe
     */
    private final List<Constructeur> contructeurs;
    /**
     * Liste des attributs de la classe
     */
    private List<Attribut> attributs;
    /**
     * Liste des méthodes de la classe
     */
    private List<Methode> methods;

    /**
     * Definition de la classe
     */
    private DefinitionClasse definition;

    /**
     * Coordonnées de la classe defini par un point
     */
    private final Point coordonnees;

    private boolean constructeurEstAffiche;
    private boolean attributEstAffiche;
    private boolean methodsEstAffiche;

    /**
     * Constructeur de la classe
     * Génère les attributs, les constructeurs et les méthodes de la classe, ainsi que sa définition
     * Et un point pour ses coordonnées
     *
     * @param path Chemin de la classe
     */
    public ClasseEntiere(String path) throws ClassNotFoundException {
        // Récupération de la classe
        Class<?> classe = Class.forName(path);

        // Création des types des attributs
        this.attributs = new ArrayList<>();
        this.contructeurs = new ArrayList<>();
        this.methods = new ArrayList<>();
        this.definition = new DefinitionClasse(classe);
        this.coordonnees = new Point(0, 0);

        // Attributs
        for (Field field : classe.getDeclaredFields()) {
            this.attributs.add(new Attribut(field));
        }

        // Constructeurs
        for (Constructor<?> constructor : classe.getDeclaredConstructors()) {
            this.contructeurs.add(new Constructeur(constructor));
        }

        // Methodes
        for (Method method : classe.getDeclaredMethods()) {
            this.methods.add(new Methode(method));
        }
        this.attributEstAffiche = true;
        this.methodsEstAffiche = true;
        this.constructeurEstAffiche = true;
    }

    /**
     * Retourne La liste des attributs de la classe
     *
     * @return La liste des attributs de la classe : DefinitionClasse
     */
    public DefinitionClasse getDefinition() {
        return definition;
    }

    /**
     * Set la definition de la classe
     *
     * @param definition Definition de la classe
     */
    public void setDefinition(DefinitionClasse definition) {
        this.definition = definition;
    }

    /**
     * Methode permettant de deplacer la classe
     *
     * @param x Translation de x
     * @param y Translation de y
     */
    public void deplacer(final int x, final int y) {
        setX(x);
        setY(y);
    }

    /**
     * Retourne la liste des attributs de la classe
     *
     * @return La liste des attributs de la classe : List<Attribut>
     */
    public List<Attribut> getAttributs() {
        return attributs;
    }

    /**
     * Set la liste des attributs de la classe
     *
     * @param attributs Liste des attributs de la classe
     */
    public void setAttributs(List<Attribut> attributs) {
        this.attributs = attributs;
    }

    /**
     * Retourne la liste des méthodes de la classe
     *
     * @return La liste des méthodes de la classe : List<Methode>
     */
    public List<Methode> getMethods() {
        return methods;
    }

    /**
     * Set la liste des méthodes de la classe
     *
     * @param methods Liste des méthodes de la classe
     */
    public void setMethods(List<Methode> methods) {
        this.methods = methods;
    }

    public List<Constructeur> getContructeurs(){
        return contructeurs;
    }

    @Override
    public String toString() {
        String info = definition.toString();
        if (attributs.size() > 0) {
            info += "\tAttributs: \n";
            for (Attribut attribut : attributs) {
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

    /**
     * Retourne la position x de la classe
     *
     * @return La position x de la classe : int
     */
    public int getX() {
        return coordonnees.getX();
    }

    /**
     * Set la position x de la classe
     *
     * @param x Position x de la classe
     */
    public void setX(int x) {
        coordonnees.setX(x);
    }

    /**
     * Retourne la position y de la classe
     *
     * @return La position y de la classe : int
     */
    public int getY() {
        return coordonnees.getY();
    }

    /**
     * Set la position y de la classe
     *
     * @param y Position y de la classe
     */
    public void setY(int y) {
        coordonnees.setY(y);
    }

    public String getNom() {
        return definition.getNom();
    }

    public boolean isConstructeurEstAffiche() {
        return constructeurEstAffiche;
    }

    public void setConstructeurEstAffiche(boolean constructeurEstAffiche) {
        this.constructeurEstAffiche = constructeurEstAffiche;
    }

    public boolean isAttributEstAffiche() {
        return attributEstAffiche;
    }

    public void setAttributEstAffiche(boolean attributEstAffiche) {
        this.attributEstAffiche = attributEstAffiche;
    }

    public boolean isMethodsEstAffiche() {
        return methodsEstAffiche;
    }

    public void setMethodsEstAffiche(boolean methodsEstAffiche) {
        this.methodsEstAffiche = methodsEstAffiche;
    }
}
