package org.teamtree.objectaid.MVC.Model;

import org.teamtree.objectaid.MVC.Vue.*;
import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.Classe.Relations.Relation;

import java.util.*;

/**
 * Classe qui permet de gérer les données du diagramme de classe
 */
public class Model implements Sujet {

    /** Liste des observateurs */
    private final List<Observateur> observateurs;

    /** Liste des classes avec leurs flèches*/
    private final Map<ClasseEntiere, ArrayList<Relation>> relations;

    /** Classe sélectionnée */
    private VueClasseAffichage currentClickedClass;

    /** Les classes cachées */
    private final List<VueClasseAffichage> hiddenClasses;

    /**
     * Constructeur du model
     */
    public Model() {
        this.observateurs = new ArrayList<>();
        this.relations = new HashMap<>();
        this.currentClickedClass = null;
        this.hiddenClasses = new ArrayList<>();
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
                notifierObservateur("actualisation fleches");
                break;
            case "actualisation fleches":
                for(Observateur observateur: this.observateurs){
                    if(observateur instanceof VueClasse){
                        ((VueClasse) observateur).actualiserFleches();
                        return;
                    }
                }
                break;
            case "totalite des classes":
                for(Observateur observateur: this.observateurs){
                    if(observateur instanceof VueClasse){
                        observateur.actualiser();
                        break;
                    }
                }
                break;
            case "update hidden classes":
                //TODO: A modifier --> ne s'occuper que de la visibilitée
                for(Observateur observateur: this.observateurs){
                    if(observateur instanceof VueClasse){
                        observateur.actualiser();
                        break;
                    }
                }
                break;
            case "update visibilite fleche" :
                for(Observateur observateur: this.observateurs){
                    if(observateur instanceof VueClasse){
                        ((VueClasse) observateur).actualiserFlechesVisibilite();
                        return;
                    }
                }
            case "update visibilite classe selection":
                this.currentClickedClass.actualiserVisibilite();
                break;
            case "click droit":
                for(Observateur observateur: this.observateurs){
                    if(observateur instanceof VueContextMenuClasse){
                        observateur.actualiser();
                        return;
                    }
                }
        }
    }

    public void notifierObservateurFlecheSpecifique(VueClasseAffichage vueClasseAffichage){
        for(Observateur observateur: this.observateurs){
            if(observateur instanceof VueClasse){
                //TODO: ne modifier que les points
                ((VueClasse) observateur).actualiserFlechesSpecifique(vueClasseAffichage);
                return;
            }
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
        this.notifierObservateur();
    }

    /**
     * Méthode qui permet de récupérer la liste des classes
     * @return Liste des classes
     */
    public List<ClasseEntiere> getClasses() {
        return new ArrayList<>(relations.keySet());
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
     * Retourne la liste des flèches d'une classe
     * @param classe Classe
     * @return Liste des flèches
     */
    public List<Relation> getRelations(ClasseEntiere classe) {
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
            if (Objects.equals(currentClickedClass.getNom(), this.currentClickedClass.getNom())) {
                this.currentClickedClass = null;
            } else {
                this.currentClickedClass = currentClickedClass;
                this.currentClickedClass.classeSelectionnee();
                this.notifierObservateur("selection");
            }
        }
    }

    /**
     * Methode qui permet de changer la possibilité d'afficher les attributs
     */
    public void afficherAttributs(boolean affiche) {
        getClasses().forEach(classeEntiere -> classeEntiere.setAttributEstAffiche(affiche));
        notifierObservateur("totalite des classes");
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
        getClasses().forEach(classeEntiere -> {
            classeEntiere.setMethodsEstAffiche(affiche);
        });
        notifierObservateur("totalite des classes");
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
        getClasses().forEach(classeEntiere -> {
            classeEntiere.setConstructeurEstAffiche(affiche);
        });
        notifierObservateur("totalite des classes");
    }

    /**
     * Methode qui permet de changer la possibilité d'afficher les constructeurs d'une classe spécifique
     */
    public void afficherConstructeursSelection() {
        this.currentClickedClass.getClasseEntiere().setConstructeurEstAffiche(!this.currentClickedClass.getClasseEntiere().isConstructeurEstAffiche());
        notifierObservateur("classe selection complete");
    }

    /**
     * Methode qui permet de changer la possibilité d'afficher les relations
     */
    public void afficherRelations(boolean affiche) {
        for(Observateur observateur: this.observateurs){
            if(observateur instanceof VueClasse){
                ((VueClasse) observateur).definirVisibiliteFleches(affiche);
                break;
            }
        }
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
     * Methode qui permet d'ajouter une classeCachée
     * @param classe La classeCachée à ajouter
     */
    public void ajouterClasseCachee(VueClasseAffichage classe){
        if (!this.hiddenClasses.contains(classe)){
            System.out.println("La classe " + classe.getNom() + " a ete ajoutee à la liste des classes cachees");
            this.hiddenClasses.add(classe);
            this.currentClickedClass.setClasseAffichee();
            this.notifierObservateur("update visibilite classe selection");
            //TODO: A modifier: ne pas mettre à jour toutes les fleches
            this.notifierObservateur("update visibilite fleche");
            this.currentClickedClass = null;

        }
    }

    public void supprimerClasseCachee(VueClasseAffichage classe) {
        if (this.hiddenClasses.contains(classe)) {
            System.out.println("La classe " + classe.getNom() + " a ete supprimee de la liste des classes cachees");
            this.hiddenClasses.remove(classe);
            classe.setClasseAffichee();
            classe.actualiserVisibilite();
            this.notifierObservateur("update visibilite fleche");
        }
    }

    public void supprimerClasseAffichage(VueClasseAffichage classe){
        this.currentClickedClass = classe;
        this.currentClickedClass.setClasseAffichee(false);
        this.notifierObservateur("update visibilite classe selection");
        this.notifierObservateur("update visibilite fleche");
        this.currentClickedClass = null;
        supprimerObservateur(classe);
    }

    public List<VueClasseAffichage> getHiddenClasses() {
        return hiddenClasses;
    }

    public Observateur getObservateur(String nom){
        for(Observateur observateur: this.observateurs){
            if(observateur.getClass().getSimpleName().equals(nom)){
                return observateur;
            }
        }
        return null;
    }
}
