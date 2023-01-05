package org.teamtree.objectaid.MVC.Model;

import org.teamtree.objectaid.Classe.ClasseAffichage;
import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.MVC.Fleches.Fleche;
import org.teamtree.objectaid.MVC.Vue.Observateur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

/**
 * Classe qui permet de gérer les données du diagramme de classe
 */
public class Model implements Sujet {

    /** Liste des observateurs */
    private final ArrayList<Observateur> observateurs;

    /** Liste des classes avec leurs flèches*/
    private final HashMap<ClasseEntiere, ArrayList<Fleche>> relations;

    /** Classe sélectionnée */
    private ClasseAffichage currentClickedClass;

    /**
     * Constructeur du model
     */
    public Model() {
        this.observateurs = new ArrayList<>();
        this.relations = new HashMap<>();
        this.currentClickedClass = null;
    }

    /**
     * Méthode qui permet d'ajouter un observateur
     * @param o Observateur
     */
    public void ajouterObservateur(Observateur o) {
        observateurs.add(o);
    }

    /**
     * Méthode qui permet de supprimer un observateur
     * @param o Observateur
     */
    public void supprimerObservateur(Observateur o) {
        observateurs.remove(o);
    }

    /**
     * Méthode qui permet de notifier les observateurs
     */
    public void notifierObservateur() {
        for (Observateur o : observateurs) {
            o.actualiser();
        }
    }

    public void notifierObservateur(String selection){
        switch(selection){
            case "selection":
                this.currentClickedClass.actualiserBordure();
            break;
            case "deplacement selection":
                this.currentClickedClass.actualiserPosition();
            break;
        }
    }

    /**
     * Méthode qui permet d'ajouter une relation
     * @param classe Classe
     * @param fleche Flèche
     */
    public void ajouterRelation(ClasseEntiere classe, Fleche fleche) {
        if (relations.containsKey(classe)) {
            relations.get(classe).add(fleche);
        } else {
            relations.put(classe, new ArrayList<>());
            relations.get(classe).add(fleche);
        }
    }

    /**
     * Méthode qui permet de supprimer une relation
     * @param classe Classe
     * @param fleche Flèche
     */
    public void supprimerRelation(ClasseEntiere classe, Fleche fleche) {
        if (relations.containsKey(classe)) {
            relations.get(classe).remove(fleche);
        }
    }

    /**
     * Méthode qui permet d'ajouter une Classe au model
     * @param classe Classe
     */
    public void ajouterClasse(ClasseEntiere classe) {
        if (!relations.containsKey(classe)) {
            int x = getClasses().size() % 6 * 150 + getClasses().size() % 6 * 30 + 30;
            int y = getClasses().size() / 6 * 300 + getClasses().size() / 6 * 30 + 30;
            classe.deplacer(x, y);
            relations.put(classe, new ArrayList<>());
        }
    }

    /**
     * Méthode qui permet de supprimer une Classe du model
     * @param classe Classe
     */
    public void supprimerClasse(ClasseEntiere classe) {
        relations.remove(classe);
    }

    /**
     * Méthode qui permet de récupérer la liste des classes
     * @return Liste des classes
     */
    public ArrayList<ClasseEntiere> getClasses() {
        return new ArrayList<>(relations.keySet());

    }

    /**
     * Retourne la liste des flèches d'une classe
     * @param classe Classe
     * @return Liste des flèches
     */
    public ArrayList<Fleche> getRelations(ClasseEntiere classe) {
        return relations.get(classe);
    }

    /**
     * Méthode qui permet de récupérer la classe sélectionnée
     * @return Classe sélectionnée
     */
    public ClasseAffichage getCurrentClickedClass() {
        return currentClickedClass;
    }

    /**
     * Méthode qui permet de définir la classe sélectionnée
     * @param currentClickedClass Classe sélectionnée
     */
    public void setCurrentClickedClass(ClasseAffichage currentClickedClass) {
        if(this.currentClickedClass != null) {
            this.currentClickedClass.classeDeSelectionnee();
            this.notifierObservateur("selection");
        }
       this.currentClickedClass =  currentClickedClass;
       this.currentClickedClass.classeSelectionnee();
        this.notifierObservateur("selection");
               /*(Objects.equals(currentClickedClass, this.currentClickedClass))
                                    ? ""
                                    : currentClickedClass;*/
    }

    /**
     * Retourne la classe grâce à son nom
     * @param nom Le nom de la classe
     * @return La classe correspondante
     */
    public Optional<ClasseEntiere> getClasse(String nom) {
        return getClasses().stream().filter(classe -> classe.getNom().equals(nom)).findFirst();
    }

    /**
     * Methode qui permet de changer la possibilité d'afficher les attributs
     */
    public void afficherAttributs() {
        getClasses().forEach(c -> {
            c.setAttributEstAffiche(!c.isAttributEstAffiche());
            notifierObservateur();
        });
    }

    /**
     * Methode qui permet de changer la possibilité d'afficher les méthodes
     */
    public void afficherMethodes() {
        getClasses().forEach(c -> {
            c.setMethodsEstAffiche(!c.isMethodsEstAffiche());
            notifierObservateur();
        });
    }

    /**
     * Methode qui permet de changer la possibilité d'afficher les paramètres
     */
    public void afficherConstructeurs() {
        getClasses().forEach(c -> {
            c.setConstructeurEstAffiche(!c.isConstructeurEstAffiche());
            notifierObservateur();
        });
    }

    /**
     * Methode qui permet de deplacer une classe
     */
    public void deplacerClasse(int x, int y) {
        this.getCurrentClickedClass().getClasseEntiere().deplacer(x,y);
        notifierObservateur("deplacement selection");

    }
}
