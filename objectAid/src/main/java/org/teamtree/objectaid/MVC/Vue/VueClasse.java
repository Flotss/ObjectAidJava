package org.teamtree.objectaid.MVC.Vue;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.Classe.Relations.Relation;
import org.teamtree.objectaid.Fabrique.FabriqueAffichage;
import org.teamtree.objectaid.Fabrique.FabriqueAffichageFleche;
import org.teamtree.objectaid.MVC.Controller.ClasseEntiereClickedController;
import org.teamtree.objectaid.MVC.Controller.ClickDroitClasseController;
import org.teamtree.objectaid.MVC.Controller.DeplacementClasseDragAndDropController;
import org.teamtree.objectaid.MVC.Fleches.Fleche;
import org.teamtree.objectaid.MVC.Model.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Classe qui permet de représenter la vue des classes
 */
public class VueClasse extends Pane implements Observateur {

    /** Modèle */
    private final Model model;

    /** HashMap qui represente la liste des classes à afficher, chaque classe possede comme clé le nom de la classe */
    private final HashMap<String, VueClasseAffichage> classes;

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
            FabriqueAffichage f = new FabriqueAffichage(classEntiere, model);
            VueClasseAffichage classe = f.affichage();

//            setBorderColor(classe);

            this.getChildren().add(classe);

            // On ajoute le controller lorsqu'on clique sur la classe
            classe.setOnMouseClicked(new ClasseEntiereClickedController(model));

            //on ajoute le drag and drop de la classe
            classe.setOnMouseDragged(new DeplacementClasseDragAndDropController(model));

            //on ajoute les tooltip aux labels des classes pour voir tout le texte

            //tooltip pour les methodes
            for (Node methode : classe.getMethodes().getChildren()) {
                Node labelNode = ((HBox) methode).getChildren().get(1);
                ((Label)labelNode).setTooltip(new Tooltip(((Label)labelNode).getText()));
            }

            //tooltip pour les attributs
            for (Node attribut : classe.getAttributs().getChildren()) {
                Node labelNode = ((HBox) attribut).getChildren().get(1);
                ((Label)labelNode).setTooltip(new Tooltip(((Label)labelNode).getText()));
            }

            //tooltip pour les attributs avec relation de la classe
            for (Node attributRelation : classe.getAttributsRelation().getChildren()) {
                Node labelNode = ((HBox) attributRelation).getChildren().get(1);
                ((Label)labelNode).setTooltip(new Tooltip(((Label)labelNode).getText()));
            }

            //tooltip pour les construteurs de la classe
            for (Node constructeur : classe.getConstructeur().getChildren()) {
                Node labelNode = ((HBox) constructeur).getChildren().get(1);
                ((Label)labelNode).setTooltip(new Tooltip(((Label)labelNode).getText()));
            }

            //tooltip pour le nom de la classe
            ((Label)classe.getDefinition().getChildren().get(1)).setTooltip(new Tooltip(((Label)classe.getDefinition().getChildren().get(1)).getText()));

            //on ajoute le controller lorsqu'on clique droit sur la classe
            classe.setOnContextMenuRequested(new ClickDroitClasseController(model,classe));

            classes.put(classe.getNom(),classe);
        }
        model.ajouterObservateur(this);

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
        for (VueClasseAffichage classe : classes.values()) {
            classe.afficherClasse();
        }

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
            if(fleche.getVueClasseArrivee().getNom() == vueClasseAffichage.getNom() || fleche.getVueClasseDepart().getNom() == vueClasseAffichage.getNom()){
                fleche.actualiser();
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
}
