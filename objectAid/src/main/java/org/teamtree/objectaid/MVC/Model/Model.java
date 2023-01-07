package org.teamtree.objectaid.MVC.Model;

import org.teamtree.objectaid.MVC.Vue.*;
import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.Classe.Relations.Relation;
import org.teamtree.objectaid.MVC.Fleches.Fleche;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

/**
 * Classe qui permet de gérer les données du diagramme de classe
 */
public class Model implements Sujet {

    /** Liste des observateurs */
    private final ArrayList<Observateur> observateurs;

    /** Liste des classes avec leurs flèches*/
    private final HashMap<ClasseEntiere, ArrayList<Relation>> relations;

    /** Classe sélectionnée */
    private VueClasseAffichage currentClickedClass;

    /** Barre de boutons spécifique */
    private boolean barreBoutonsSpecifique;

    /**
     * Constructeur du model
     */
    public Model() {
        this.observateurs = new ArrayList<>();
        this.relations = new HashMap<>();
        this.currentClickedClass = null;
        this.barreBoutonsSpecifique= false;
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

    /**
     * Méthode qui permet de notifier les observateurs correspondant à une selection donnée en paramètre
     * @param selection String
     */

    public void notifierObservateur(String selection){
        switch(selection){
            case "selection":
                this.currentClickedClass.actualiserBordure();
            break;
            case "deplacement selection":
                this.currentClickedClass.actualiserPosition();
                for(Observateur observateur: this.observateurs){
                    if(observateur instanceof VueClasse){
                        ((VueClasse) observateur).actualiserFleches();
                        return;
                    }
                }
            break;
            case "classe selection complete":
                this.currentClickedClass.afficherClasse();
                break;
            case "barre":
                for(Observateur observateur: this.observateurs){
                    if(observateur instanceof VueButtonBarClasse){
                        this.setBarreBoutonsSpecifique();
                        observateur.actualiser();
                        return;
                    }
                }
                break;
            case "actualisation fleches":
                for(Observateur observateur: this.observateurs){
                    if(observateur instanceof VueClasse){
                        System.out.println("actualisation fleches");
                        ((VueClasse) observateur).actualiserFleches();
                        return;
                    }
                }
                break;
        }
    }

    /**
     * Méthode qui permet d'ajouter une relation
     * @param classe Classe
     * @param relation Relation
     */
    public void ajouterRelation(ClasseEntiere classe, Relation relation) {
        if (relations.containsKey(classe)) {
            relations.get(classe).add(relation);
        } else {
            relations.put(classe, new ArrayList<>());
            relations.get(classe).add(relation);
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
            relations.put(classe, new ArrayList<>(classe.getRelations()));
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
    public ArrayList<Relation> getRelations(ClasseEntiere classe) {
        return relations.get(classe);
    }

    /**
     * Méthode qui permet de récupérer la classe sélectionnée
     * @return Classe sélectionnée
     */
    public VueClasseAffichage getCurrentClickedClass() {
        return currentClickedClass;
    }

    /**
     * Méthode qui permet de définir la classe sélectionnée
     * @param currentClickedClass Classe sélectionnée
     */
    public void setCurrentClickedClass(VueClasseAffichage currentClickedClass) {
        if(this.currentClickedClass == null) {
            this.currentClickedClass = currentClickedClass;
            this.currentClickedClass.classeSelectionnee();
            this.notifierObservateur("selection");
        }else {
            this.currentClickedClass.classeDeSelectionnee();
            this.notifierObservateur("selection");
            if (currentClickedClass.getNom() == this.currentClickedClass.getNom()) {
                this.currentClickedClass = null;
            } else {
                this.currentClickedClass = currentClickedClass;
                this.currentClickedClass.classeSelectionnee();
                this.notifierObservateur("selection");
            }
        }
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
    public void afficherAttributs(boolean affiche) {
        getClasses().forEach(c -> {
            c.setAttributEstAffiche(affiche);
        });
        notifierObservateur();
    }

    /**
     * Methode qui permet de changer la possibilité d'afficher les attributs d'une classe spécifique
     */
    public void afficherAttributsSelection() {
        this.currentClickedClass.getClasseEntiere().setAttributEstAffiche(!this.currentClickedClass.getClasseEntiere().isAttributEstAffiche());
        notifierObservateur("classe selection complete");
    }

    /**
     * Methode qui permet de changer la possibilité d'afficher les méthodes
     */
    public void afficherMethodes(boolean affiche) {
        getClasses().forEach(c -> {
            c.setMethodsEstAffiche(affiche);
        });
        notifierObservateur();
    }

    /**
     * Methode qui permet de changer la possibilité d'afficher les méthodes d'une classe spécifique
     */
    public void afficherMethodesSelection() {
        this.currentClickedClass.getClasseEntiere().setMethodsEstAffiche(!this.currentClickedClass.getClasseEntiere().isMethodsEstAffiche());
        notifierObservateur("classe selection complete");
    }

    /**
     * Methode qui permet de changer la possibilité d'afficher les constructeurs
     */
    public void afficherConstructeurs(boolean affiche) {
        getClasses().forEach(c -> {
            c.setConstructeurEstAffiche(affiche);
        });
        notifierObservateur();
        for(Observateur observateur: this.observateurs){
            if(observateur instanceof VueClasse){
                System.out.println("vgyftfgytbh");
                ((VueClasse) observateur).actualiserFleches();
                return;
            }
        }

    }

    /**
     * Methode qui permet de changer la possibilité d'afficher les constructeurs d'une classe spécifique
     */
    public void afficherConstructeursSelection() {
        this.currentClickedClass.getClasseEntiere().setConstructeurEstAffiche(!this.currentClickedClass.getClasseEntiere().isConstructeurEstAffiche());
        notifierObservateur("classe selection complete");
    }

    /**
     * Methode qui permet de deplacer une classe
     */
    public void deplacerClasse(int x, int y) {
        this.getCurrentClickedClass().getClasseEntiere().deplacer(x,y);
        notifierObservateur("deplacement selection");
    }

    /**
     * Methode qui permet de retourner la VueClasseAffichage d'une classe en donnant le nom en paramètre
     * @param nom Le nom de la classe
     */

    public VueClasseAffichage getVueClasseAffichage(String nom){
        for(Observateur observateur: this.observateurs){
            if(observateur.getClass().getSimpleName().equals("VueClasse")){
                return ((VueClasse) observateur).getClasseAffichage(nom);
            }
        }
        return null;
    }

    /**
     * Methode qui permet de changer le booleen permettant de savoir si les boutons d'affichage d'une classe doit etre affichés
     */

    public void setBarreBoutonsSpecifique(){
        this.barreBoutonsSpecifique = this.currentClickedClass!=null;
    }

    /**
     * Methode qui permet de retourner le booleen permettant de savoir si les boutons d'affichage d'une classe doit etre affichés
     * @return Le booleen permettant de savoir si les boutons d'affichage d'une classe doit etre affichés
     */

    public boolean getBarreBoutonsSpecifique(){
        return this.barreBoutonsSpecifique;
    }
}
