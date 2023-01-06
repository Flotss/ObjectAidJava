package org.teamtree.objectaid.MVC.Vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.teamtree.objectaid.Classe.*;
import org.teamtree.objectaid.Classe.Relations.Association;
import org.teamtree.objectaid.Classe.Relations.Relation;
import org.teamtree.objectaid.Fabrique.FabriqueAffichage;
import org.teamtree.objectaid.MVC.Model.Model;

import java.util.List;
import java.util.Optional;

/**
 * Classe qui représente l'affichage d'une classe
 */
public class VueClasseAffichage extends VBox implements Observateur {

    /**
     * Nom de cette classe, permettant de la retrouver lorsqu'on clique dessus notamment
     */
    private final String nom;

    /** ClasseEntiere de la classe qui est affichée */
    private final ClasseEntiere classeEntiere;

    /**
     * HBox qui contient la définition de la classe
     */
    private final HBox definition;

    /**
     * VBox qui contient les constructeurs de la classe
     */
    private final VBox constructeur;

    /**
     * VBox qui contient les attributs de la classe
     */
    private final VBox attributs;

    /**
     * VBox qui contient les attributs en lien avec les relations
     */
    private final VBox attributsRelation;

    /**
     * VBox qui contient les méthodes de la classe
     */
    private final VBox methodes;

    /**
     Couleur de la bordure
     */
    private String couleur;

    /**
     Le model
     */
    private final Model model;

    /**
     * Constructeur de la classe
     * @param classeEntiere La classe à afficher
     */
    public VueClasseAffichage(ClasseEntiere classeEntiere, Model model){
        this.nom = classeEntiere.getDefinition().getNom();
        this.classeEntiere = classeEntiere;
        this.definition = new HBox();
        this.constructeur = new VBox();
        this.attributs = new VBox();
        this.attributsRelation = new VBox();
        this.methodes = new VBox();
        this.model = model;

        this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    /**
     * Méthode qui permet de créer la partie définition de la classe
     */

    public void setDefinition() {
        definition.setAlignment(Pos.CENTER);
        definition.setPadding(new Insets(0, 5, 0, 5));
        //partie definition
        String def = classeEntiere.getDefinition().getNom();
        Label definitionLabel = new Label(def);

        //Icon selon le type de la classe
        definition.getChildren().addAll(FabriqueAffichage.fabriqueIcon(classeEntiere).getIcon(), definitionLabel);
    }

    /**
     * Méthode qui permet de créer la partie constructeurs de la classe
     */

    public void setAttributs(){
        attributs.setPadding(new Insets(0, 5, 0, 5));

        //Si il existe des attributs, on crée une bordure sur la catégorie du dessus (pour crée une séparation)
        if (classeEntiere.getAttributs().size() != 0) {
            attributs.setStyle("-fx-border-color: black transparent transparent transparent; -fx-border-width: 1px;");
        }
        //Pour chaque attributs
        for (Attribut attributX: classeEntiere.getAttributs()) {
            String att = FabriqueAffichage.fabriqueAcces(attributX.getAccessibilite()) + " " + attributX.getType() + " " + attributX.getNom();
            Label attributLabel = new Label(att);
            attributs.getChildren().add(attributLabel);
        }
    }

    /**
     * Méthode qui permet de mettre à jour les attributs qui n'ont pas de relations possible
     * exemple : si Point n'est pas dans le diagramme, mais qu'il a une relation d'association avec la classe
     *           alors il doit être en attribut
     */
    public void updateAttributsRelation(){
        this.attributsRelation.getChildren().clear();
        this.attributsRelation.setPadding(new Insets(0, 5, 0, 5));
        boolean bordureAffichee = false;

        List<Relation> relations = this.classeEntiere.getRelations();
        for (Relation relation : relations) {
            Optional<ClasseEntiere> classeEntiereDestination = model.getClasse(relation.getDestination());
            if (! classeEntiereDestination.isPresent() && relation instanceof Association association) {
                if(!bordureAffichee){
                    attributs.setStyle("-fx-border-color: black transparent transparent transparent; -fx-border-width: 1px;");
                    bordureAffichee = true;
                }
                String attRela = FabriqueAffichage.fabriqueAcces(association.getAttribut().getAccessibilite()) + " " + association.getAttribut().getType() + " " + association.getAttribut().getNom();
                Label attributRelationLabel = new Label(attRela);
                attributsRelation.getChildren().add(attributRelationLabel);
            }
        }
    }

    /**
     * Méthode qui permet de créer la partie constructeurs de la classe
     */
    public void setConstructeur(){
        //partie constructeur
        constructeur.setPadding(new Insets(0, 5, 0, 5));
        
        //Si il existe des constructeurs, on crée une bordure sur la catégorie du dessus (pour crée une séparation)
        if (classeEntiere.getContructeurs().size() != 0) {
            constructeur.setStyle("-fx-border-color: black transparent transparent transparent; -fx-border-width: 1px;");
        }
        //Pour chaque Constructeurs
        for (Constructeur constructeurX: classeEntiere.getContructeurs()) {
            //Debut de la ligne (accesibilité, nom,...), du type : + Constructeur(
            String constr = FabriqueAffichage.fabriqueAcces(constructeurX.getAccessibilite()) + " " + constructeurX.getNom() + "(";
            int n = 0;

            //Pour chaque parametre du constructeur, on l'écrit dans l'affichage
            //exemple : + Constructeur(int a, int b)
            for(Parametre parametreX: constructeurX.getParametre()){
                constr += parametreX.getType() + " " + parametreX.getNom();
                if (n != constructeurX.getParametre().size() - 1) {
                    constr += ", ";
                }
                n++;
            }

            //On écrit la fin du constructeur puis on l'ajoute avec les autres constructeurs
            constr += ")";
            Label constrLabel = new Label(constr);
            constructeur.getChildren().add(constrLabel);
        }
    }

    /**
     * Méthode qui permet de créer la partie méthodes de la classe
     */
    public void setMethodes(){
        //partie methodes
        methodes.setPadding(new Insets(0, 5, 0, 5));

        if (classeEntiere.getMethods().size() != 0) {
            methodes.setStyle("-fx-border-color: black transparent transparent transparent; -fx-border-width: 1px;");
        }
        for (int i = 0; i < classeEntiere.getMethods().size(); i++) {
            Methode methodeX = classeEntiere.getMethods().get(i);
            String meth = FabriqueAffichage.fabriqueAcces(methodeX.getAccessibilite()) + " " + methodeX.getNom() + "(";
            for (int j = 0; j < methodeX.getParametre().size(); j++) {
                meth += methodeX.getParametre().get(j).getType() + " " + methodeX.getParametre().get(j).getNom();
                if (j != methodeX.getParametre().size() - 1) {
                    meth += ", ";
                }
            }
            meth += "): " + classeEntiere.getMethods().get(i).getTypeRetourne();
            Label methodeLabel = new Label(meth);
            methodes.getChildren().add(methodeLabel);
        }
    }

    /**
     * Méthode qui permet de créer l'affichage de la classe
     */
    public void actualiserPosition(){
        this.setLayoutX(classeEntiere.getX());
        this.setLayoutY(classeEntiere.getY());
    }

    /**
     * Méthode qui permet de créer l'affichage de la classe
     */
    public void afficherClasse(){
        updateAttributsRelation();

        //On ajoute les différentes parties de la classe
    //TODO: a renomé en actualiser (julien le fera)
    //On ajoute les différentes parties de la classe
        this.getChildren().clear();
        this.getChildren().add(definition);
        if (classeEntiere.isAttributEstAffiche()) {
            this.getChildren().add(attributs);
            this.getChildren().add(attributsRelation);
        }
        if (classeEntiere.isConstructeurEstAffiche()) {
            this.getChildren().add(constructeur);
        }
        if (classeEntiere.isMethodsEstAffiche()) {
            this.getChildren().add(methodes);
        }
    }


    public String getNom(){
        return this.nom;
    }

    public HBox getDefinition() {
        return definition;
    }

    public VBox getConstructeur() {
        return constructeur;
    }

    public VBox getAttributs() {
        return attributs;
    }

    public VBox getMethodes() {
        return methodes;
    }

    public ClasseEntiere getClasseEntiere(){
        return this.classeEntiere;
    }

    public void classeSelectionnee(){
        this.couleur = "blue";
    }

    public void classeDeSelectionnee(){
        this.couleur = "black";
    }

    public void actualiserBordure(){
        super.setStyle("-fx-border-color: "+this.couleur+ " ; -fx-border-width: 1px;");
    }

    @Override
    public void actualiser() {

    }
}
