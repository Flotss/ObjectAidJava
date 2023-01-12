package org.teamtree.objectaid.Classe;

import org.teamtree.objectaid.Classe.Relations.Association;
import org.teamtree.objectaid.Classe.Relations.Heritage;
import org.teamtree.objectaid.Classe.Relations.Implementation;
import org.teamtree.objectaid.Classe.Relations.Relation;
import org.teamtree.objectaid.MVC.Vue.VueClasseAffichage;
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
     * Coordonnées de la classe defini par un point
     */
    private final Point coordonnees;

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
     * Constructeur est afficher ou non
     */
    private boolean constructeurEstAffiche;
    /**
     * Attributs sont afficher ou non
     */
    private boolean attributEstAffiche;
    /**
     * Methodes sont afficher ou non
     */
    private boolean methodsEstAffiche;

    /** Relations sont afficher ou non */
    private boolean relationsEstAffiche;

    /** Relation entre classe */
    private final List<Relation> relations;

    /** Classe qui sera affichée correspondant à la ClasseEntiere */
    private VueClasseAffichage classeAffichage;

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
        this.relations = new ArrayList<>();
        this.classeAffichage = null;


        // Interfaces
        for (Class<?> inter : classe.getInterfaces()) {
            this.relations.add(new Implementation(this.definition.getNom(), inter.getSimpleName()));
        }

        // Classe parent
        if (classe.getSuperclass() != null) {
            String nameParent = classe.getSuperclass().getSimpleName();

            // On ne veut pas avoir de relation avec Object puisque c'est la classe mère de toutes les classes
            if (!nameParent.equals("Object")) {
                this.relations.add(new Heritage(this.definition.getNom(),nameParent));
            }
        }



        // Constructeurs
        // Attributs et Relations
        for (Field field : classe.getDeclaredFields()) {
            // On cherche si l'attribut est une association donc s'il est primitif ou non
            Attribut attribut = new Attribut(field);
            String destinationType = attribut.getType();
            boolean isCollection = (destinationType.contains("<") && destinationType.contains(">"));

            // Si c'est une collection, on récupère le type de la collection
            if (isCollection) {
                destinationType = destinationType.substring(destinationType.indexOf("<") + 1, destinationType.indexOf(">"));
            }

            // Si le type n'est pas primitif, c'est une association
            // Sinon, c'est un attribut
            String[] primitives = {"int", "double", "float", "long", "short", "byte", "char", "boolean", "String"};
            boolean isPrimitive = false;
            for (String primitive : primitives) {
                if (destinationType.contains(primitive) && destinationType.length() == primitive.length()) {
                    this.attributs.add(attribut);
                    isPrimitive = true;
                    break;
                }
            }

            // Ajout de la relation si l'attribut n'est pas primitif
            if (!isPrimitive) {
                if (isCollection){
                    this.relations.add(new Association(this.definition.getNom(), destinationType, attribut, "*", "*"));
                }else{
                    this.relations.add(new Association(this.definition.getNom(), destinationType, attribut, "1", "*"));
                }
            }
        }
        for (Constructor<?> constructor : classe.getDeclaredConstructors()) {
            this.contructeurs.add(new Constructeur(constructor));
        }

        // Methodes
        for (Method method : classe.getDeclaredMethods()) {
            this.methods.add(new Methode(method));
        }



        // Partie affichage des attributs, constructeurs, méthodes et relations
        this.attributEstAffiche = true;
        this.methodsEstAffiche = true;
        this.constructeurEstAffiche = true;
        this.relationsEstAffiche = false;

        System.out.println("-----------------\n");
        for (Relation relation : this.relations) {
            System.out.println(relation);
        }
    }

    public ClasseEntiere(Class<?> clazz) {

        if (clazz == null) {
            throw new IllegalArgumentException("La classe ne peut pas être null");
        }


        // Création des types des attributs
        this.attributs = new ArrayList<>();
        this.contructeurs = new ArrayList<>();
        this.methods = new ArrayList<>();
        this.definition = new DefinitionClasse(clazz);
        this.coordonnees = new Point(0, 0);
        this.relations = new ArrayList<>();
        this.classeAffichage = null;


        // Interfaces
        for (Class<?> inter : clazz.getInterfaces()) {
            this.relations.add(new Implementation(this.definition.getNom(), inter.getSimpleName()));
        }

        // Classe parent
        if (clazz.getSuperclass() != null) {
            String nameParent = clazz.getSuperclass().getSimpleName();

            // On ne veut pas avoir de relation avec Object puisque c'est la classe mère de toutes les classes
            if (!nameParent.equals("Object")) {
                this.relations.add(new Heritage(this.definition.getNom(),nameParent));
            }
        }



        // Constructeurs
        // Attributs et Relations
        for (Field field : clazz.getDeclaredFields()) {
            // On cherche si l'attribut est une association donc s'il est primitif ou non
            Attribut attribut = new Attribut(field);
            String destinationType = attribut.getType();
            boolean isCollection = (destinationType.contains("<") && destinationType.contains(">"));

            // Si c'est une collection, on récupère le type de la collection
            if (isCollection) {
                destinationType = destinationType.substring(destinationType.indexOf("<") + 1, destinationType.indexOf(">"));
            }

            // Si le type n'est pas primitif, c'est une association
            // Sinon, c'est un attribut
            String[] primitives = {"int", "double", "float", "long", "short", "byte", "char", "boolean", "String"};
            boolean isPrimitive = false;
            for (String primitive : primitives) {
                if (destinationType.contains(primitive) && destinationType.length() == primitive.length()) {
                    this.attributs.add(attribut);
                    isPrimitive = true;
                    break;
                }
            }

            // Ajout de la relation si l'attribut n'est pas primitif
            if (!isPrimitive) {
                if (isCollection){
                    this.relations.add(new Association(this.definition.getNom(), destinationType, attribut, "*", "*"));
                }else{
                    this.relations.add(new Association(this.definition.getNom(), destinationType, attribut, "1", "*"));
                }
            }
        }
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            this.contructeurs.add(new Constructeur(constructor));
        }

        // Methodes
        for (Method method : clazz.getDeclaredMethods()) {
            this.methods.add(new Methode(method));
        }



        // Partie affichage des attributs, constructeurs, méthodes et relations
        this.attributEstAffiche = true;
        this.methodsEstAffiche = true;
        this.constructeurEstAffiche = true;
        this.relationsEstAffiche = false;

        System.out.println("-----------------\n");
        for (Relation relation : this.relations) {
            System.out.println(relation);
        }
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
        StringBuilder info = new StringBuilder(definition.toString());
        if (attributs.size() > 0) {
            info.append("\tAttributs: \n");
            for (Attribut attribut : attributs) {
                info.append("\t\t").append(attribut.toString());
            }
        }
        if (contructeurs.size() > 0) {
            info.append("\tConstructeurs: \n");
            for (Constructeur constructeur : contructeurs) {
                info.append("\t\t").append(constructeur.toString());
            }
        }

        if (methods.size() > 0) {
            info.append("\tMethodes: \n");
            for (Methode methode : methods) {
                info.append("\t\t").append(methode.toString());
            }
        }

        if (relations.size() > 0) {
            info.append("\tRelations: \n");
            for (Relation relation : relations) {
                info.append("\t\t").append(relation.toString());
            }
        }

        return info.toString();
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

    /**
     * Retourne le nom de la classe
     * @return Le nom de la classe : String
     */
    public String getNom() {
        return definition.getNom();
    }

    /**
     * Retourne la possibilite d'afficher le constructeur
     * @return Vrai si le constructeur est affiché : boolean
     */
    public boolean isConstructeurEstAffiche() {
        return constructeurEstAffiche;
    }

    /**
     * Set la possibilite d'afficher le constructeur
     * @param constructeurEstAffiche Boolean vrai pour afficher le constructeur sinon faux
     */
    public void setConstructeurEstAffiche(boolean constructeurEstAffiche) {
        this.constructeurEstAffiche = constructeurEstAffiche;
    }

    /**
     * Retourne la possibilite d'afficher les attributs
     * @return Vrai si les attributs sont affichés : boolean
     */
    public boolean isAttributEstAffiche() {
        return attributEstAffiche;
    }

    /**
     * Set la possibilite d'afficher les attributs
     * @param attributEstAffiche Boolean vrai pour afficher les attributs sinon faux
     */
    public void setAttributEstAffiche(boolean attributEstAffiche) {
        this.attributEstAffiche = attributEstAffiche;
    }

    /**
     * Retourne la possibilite d'afficher les méthodes
     * @return Vrai si les méthodes sont affichées : boolean
     */
    public boolean isMethodsEstAffiche() {
        return methodsEstAffiche;
    }

    /**
     * Set la possibilite d'afficher les méthodes
     * @param methodsEstAffiche Boolean vrai pour afficher les méthodes sinon faux
     */
    public void setMethodsEstAffiche(boolean methodsEstAffiche) {
        this.methodsEstAffiche = methodsEstAffiche;
    }

    /**
     * Retourne la liste des relations de la classe
     * @return La liste des relations de la classe : List<Relation>
     */
    public VueClasseAffichage getClasseAffichage() {
        return classeAffichage;
    }

    public void setClasseAffichage(VueClasseAffichage classeAffichage) {
        this.classeAffichage = classeAffichage;
    }

    public List<Relation> getRelations(){
        return relations;
    }

    /**
     * Set la possibilite d'afficher les relations
     * @param relationsEstAffiche Boolean vrai pour afficher les relations sinon faux
     */
    public void setRelationsEstAffiche(boolean relationsEstAffiche) {
        this.relationsEstAffiche = relationsEstAffiche;
    }


    /**
     * Retourne la possibilite d'afficher les attributs
     * @return Vrai si les attributs sont affichés : boolean
     */
    public boolean isRelationsEstAffiche() {
        return attributEstAffiche;
    }


    /**
     * Retourne l'uml de la classe entiere
     * @return L'uml de la classe entiere : String
     */
    public String getUml() {
        StringBuilder uml = new StringBuilder();
        uml.append(definition.getUml() + "{\n");
        if (attributs.size() > 0) {
            for (Attribut attribut : attributs) {
                uml.append("\t\t").append(attribut.getUml() + "\n");
            }
        }
        if (contructeurs.size() > 0) {
            for (Constructeur constructeur : contructeurs) {
                uml.append("\t\t").append(constructeur.getUml() + "\n");
            }
        }
        if (methods.size() > 0) {
            for (Methode methode : methods) {
                uml.append("\t\t").append(methode.getUml() + "\n");
            }
        }
        uml.append("}\n\n");
        if (relations.size() > 0) {
            for (Relation relation : relations) {
                uml.append(relation.getUml() + "\n");
            }
        }
        uml.append("\n");
        return uml.toString();
    }
}
