package org.teamtree.objectaid.MVC.Vue;

import javafx.scene.layout.Pane;
import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.Classe.Relations.Relation;
import org.teamtree.objectaid.Fabrique.FabriqueAffichage;
import org.teamtree.objectaid.Fabrique.FabriqueAffichageFleche;
import org.teamtree.objectaid.MVC.Model.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe qui permet de représenter la vue des classes
 */
public class VueClasse extends Pane implements Observateur {

    /** Modèle */
    private final Model model;

    /** HashMap qui represente la liste des classes à afficher, chaque classe possede comme clé le nom de la classe */
    private final Map<String, VueClasseAffichage> classes;

    /** Liste des fleches */
    private final List<Fleche> fleches;



    /**
     * Constructeur de la classe VueClasse
     * @param model Modèle
     */
    public VueClasse(Model model) {
        this.model = model;
        this.classes = new HashMap<>();
        this.fleches = new ArrayList<>();


        // Pour chaque classe
        for (ClasseEntiere classEntiere : model.getClasses()) {
            // On creation de l'affichage de la classe
            ajouterClasse(classEntiere);
        }
        this.model.ajouterObservateur(this);

        // Pour chaque relation
        for (ClasseEntiere classEntiere : model.getClasses()) {
            for (Relation relation : model.getRelations(classEntiere)) {

                // Si la relation n'est pas possible, on ne l'affiche pas
                // Exemple : la classe en relation n'est pas dans le diagramme
                if (model.getVueClasseAffichage(relation.getDestination()) == null) continue;

                // Relation
                Fleche fleche = FabriqueAffichageFleche.creerAffichageFleche(model, relation);
                fleches.add(fleche);
                this.getChildren().add(fleche);
            }
        }
    }

    /**
     * Méthode qui permet de mettre a jour la vue
     */
    @Override
    public void actualiser() {
        for (ClasseEntiere classe : model.getClasses()) {
            classe.getClasseAffichage().actualiser();
        }
        System.out.println(fleches.size());
        actualiserFleche();
        for (Fleche fleche : fleches) {
            fleche.actualiser();
        }
    }

    public void actualiserFleche(){
        // Suppression des fleches
        this.getChildren().removeAll(fleches);
        this.fleches.clear();


        // Pour chaque relation
        for (ClasseEntiere classEntiere : model.getClasses()) {
            for (Relation relation : model.getRelations(classEntiere)) {


                // Si la relation n'est pas possible, on ne l'affiche pas
                // Exemple : la classe en relation n'est pas dans le diagramme
                if (model.getVueClasseAffichage(relation.getDestination()) == null) continue;

                // Relation
                Fleche fleche = FabriqueAffichageFleche.creerAffichageFleche(model, relation);
                fleches.add(fleche);
                this.getChildren().add(fleche);
            }
        }
    }

    /**
     * Méthode qui permet de retourner la classe affichage correspondant au nom de la classe dans la liste des classes
     * @param nom String
     * @return classeAffichage
     */
    public VueClasseAffichage getClasseAffichage(String nom){
        return this.classes.get(nom);
    }

    /**
     * Méthode qui permet de actualiser la vue des fleches
     */
    public void actualiserFleches() {
        for (Fleche fleche : fleches) {
            fleche.actualiser();
        }
    }

    /**
     * Méthode qui permet de actualiser la vue des fleches
     */
    public void actualiserFlechesSpecifique(VueClasseAffichage vueClasseAffichage) {
        for (Fleche fleche : fleches) {
            if(fleche.getVueClasseArrivee().getNom().equals(vueClasseAffichage.getNom()) || fleche.getVueClasseDepart().getNom().equals(vueClasseAffichage.getNom())){
                fleche.actualiser();
            }
        }
    }

    /**
     * Méthode qui permet de actualiser la vue des fleches
     */
    public void actualiserRelationsSpecifique(VueClasseAffichage vueClasseAffichage, String relation) {
        for (Fleche fleche : fleches) {
            if(fleche.getVueClasseDepart().getNom().equals(vueClasseAffichage.getNom()) && fleche.getType().equals(relation)){
                VueClasseAffichage arrivee = fleche.getVueClasseArrivee();
                System.out.println(arrivee.getNom());


                if(model.classeMasquee(arrivee)){
                    model.supprimerClasseCachee(arrivee);
                }else {
                    model.ajouterClasseCachee(arrivee);
                }

            }
        }
    }

    /**
     * Méthode qui permet de actualiser la visibilite des fleches
     */
    public void actualiserFlechesVisibilite() {
        for (Fleche fleche : fleches) {
            VueClasseAffichage depart = fleche.getVueClasseDepart();
            VueClasseAffichage arrivee = fleche.getVueClasseArrivee();
            fleche.definirVisibilite(depart.getClasseAffichee() && arrivee.getClasseAffichee());
            fleche.actualiserVisibilite();
        }
    }

    public void definirVisibiliteFleches(boolean visibilite){
        for (Fleche fleche : fleches) {
            fleche.definirVisibilite(visibilite);
        }
    }

    public List<VueClasseAffichage> getClasses() {
        ArrayList<VueClasseAffichage> classes = new ArrayList<>();
        this.classes.keySet().forEach(key -> classes.add(this.classes.get(key)));
        return classes;
    }

    public void supprimerFleches(){
        this.fleches.clear();
    }

    public void supprimerTout(){
        this.getChildren().clear();
    }

    public ClasseEntiere ajouterClasse(ClasseEntiere classeEntiere){
        FabriqueAffichage f = new FabriqueAffichage(classeEntiere, model);
        VueClasseAffichage classe = f.affichage();
        classes.put(classe.getNom(),classe);
        classeEntiere.setClasseAffichage(classe);
        model.ajouterObservateur(classe);
        this.getChildren().add(classe);
        return classeEntiere;
    }

    public void rechargerMethodes(){
        for(VueClasseAffichage vueClasseAffichage : this.classes.values()){
            vueClasseAffichage.setMethodes();
        }
    }
}
