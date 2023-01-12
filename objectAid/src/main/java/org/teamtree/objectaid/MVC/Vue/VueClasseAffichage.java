package org.teamtree.objectaid.MVC.Vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import org.teamtree.objectaid.Classe.*;
import org.teamtree.objectaid.Classe.Relations.Association;
import org.teamtree.objectaid.Classe.Relations.Relation;
import org.teamtree.objectaid.Etat.Abstract;
import org.teamtree.objectaid.Etat.Etat;
import org.teamtree.objectaid.Etat.Static;
import org.teamtree.objectaid.Fabrique.FabriqueAffichage;
import org.teamtree.objectaid.MVC.Controller.ListenerModificationTailleClasse;
import org.teamtree.objectaid.MVC.Model.Model;

import java.util.List;
import java.util.Objects;
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
    private VBox methodes;

    /**
     Couleur de la bordure
     */
    private String couleur;

    /**
     Le model
     */
    private final Model model;

    private boolean classeAffichee;

    /**
     * Constructeur de la classe
     * @param classeEntiere La classe à afficher
     */
    public VueClasseAffichage(ClasseEntiere classeEntiere, Model model){
        this.nom = classeEntiere.getNom();
        this.classeEntiere = classeEntiere;
        this.definition = new HBox();
        this.constructeur = new VBox();
        this.attributs = new VBox();
        this.attributsRelation = new VBox();
        this.methodes = new VBox();
        this.model = model;
        this.classeAffichee = true;
        this.heightProperty().addListener(new ListenerModificationTailleClasse(model, this));
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
            HBox line = new HBox();
            Shape icon = attributX.getAccessibilite().getShape();
            String att = attributX.getType() + " " + attributX.getNom();
            Label attributLabel = new Label(att);

            for (Etat etat : attributX.getEtats()) {
                if (etat instanceof Static) {
                    // Souligne le nom de la méthode
                    attributLabel.setUnderline(true);
                }
            }

            line.getChildren().addAll(icon, attributLabel);
            attributs.getChildren().add(line);
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

                HBox line = new HBox();
                Shape icon = association.getAttribut().getAccessibilite().getShape();
                String attRela = association.getAttribut().getType() + " " + association.getAttribut().getNom();
                Label attributRelationLabel = new Label(attRela);

                for (Etat etat : ((Association) relation).getAttribut().getEtats()) {
                    if (etat instanceof Static) {
                        // Souligne le nom de la méthode
                        attributRelationLabel.setUnderline(true);
                    }
                }

                line.getChildren().addAll(icon, attributRelationLabel);
                attributsRelation.getChildren().add(line);
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
            HBox line = new HBox();

            // Icon selon l'accessibilité
            Shape icon = constructeurX.getAccessibilite().getShape();

            //Debut de la ligne (accesibilité, nom,...), du type : + Constructeur(
            String constr = constructeurX.getNom() + "(";
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

            line.getChildren().addAll(icon, constrLabel);
            constructeur.getChildren().add(line);
        }
    }

    /**
     * Méthode qui permet de créer la partie méthodes de la classe
     */
    public void setMethodes(){
        //partie methodes
        this.methodes = new VBox();
        methodes.setPadding(new Insets(0, 5, 0, 5));
        if (classeEntiere.getMethods().size() != 0) {
            methodes.setStyle("-fx-border-color: black transparent transparent transparent; -fx-border-width: 1px;");
        }
        for (int i = 0; i < classeEntiere.getMethods().size(); i++) {
            Methode methodeX = classeEntiere.getMethods().get(i);

            HBox line = new HBox();
            Shape icon = methodeX.getAccessibilite().getShape();

            String meth = methodeX.getNom() + "(";
            for (int j = 0; j < methodeX.getParametre().size(); j++) {
                meth += methodeX.getParametre().get(j).getType() + " " + methodeX.getParametre().get(j).getNom();
                if (j != methodeX.getParametre().size() - 1) {
                    meth += ", ";
                }
            }
            meth += "): " + classeEntiere.getMethods().get(i).getTypeRetourne();
            Label methodeLabel = new Label(meth);

            for (Etat etat : methodeX.getEtats()) {
                if (etat instanceof Abstract) {
                    methodeLabel.setStyle("-fx-font-style: italic;");
                }

                if (etat instanceof Static) {
                    // Souligne le nom de la méthode
                    methodeLabel.setUnderline(true);
                }
            }

            line.getChildren().addAll(icon, methodeLabel);
            methodes.getChildren().add(line);
        }
    }



    /**
     * Méthode qui permet de créer l'affichage de la classe
     */
    public void afficherClasse(){
        this.getChildren().clear();

        updateAttributsRelation();

        //On ajoute les différentes parties de la classe
        //TODO: a renomé en actualiser (julien le fera)
        //On ajoute les différentes parties de la classe
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

    /**
     * Retourne le nom de la classe affichée
     * @return le nom de la classe affichée : String
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * Retourne la partie definition de la classe affichée
     * @return la partie definition de la classe affichée : HBox
     */
    public HBox getDefinition() {
        return definition;
    }

    /**
     * Retourne la partie attributs de la classe affichée
     * @return la partie attributs de la classe affichée : VBox
     */
    public VBox getConstructeur() {
        return constructeur;
    }

    /**
     * Retourne la partie constructeurs de la classe affichée
     * @return la partie constructeurs de la classe affichée : VBox
     */
    public VBox getAttributs() {
        return attributs;
    }

    /**
     * Retourne la partie méthodes de la classe affichée
     * @return la partie méthodes de la classe affichée : VBox
     */
    public VBox getMethodes() {
        return methodes;
    }

    /**
     * Retourne la classe entière de la classe affichée
     * @return la classe entière de la classe affichée : Classe
     */
    public ClasseEntiere getClasseEntiere(){
        return this.classeEntiere;
    }

    /**
     * Retourne les attributs avec une relation de la classe affichée
     * @return les attributs avec une relation de la classe affichée : VBox
     */
    public VBox getAttributsRelation() {
        return attributsRelation;
    }

    /**
     * Methode qui affecte la couleur bleu à la bordure de la classe affichée lorsqu'elle est sélectionnée
     */
    public void classeSelectionnee(){
        this.couleur = "blue";
    }

    /**
     * Methode qui affecte la couleur noire à la bordure de la classe affichée lorsqu'elle n'est pas sélectionnée
     */
    public void classeDeSelectionnee(){
        this.couleur = "black";
    }

    /**
     * Methode qui permet de mettre à jour la bordure de la classe affichée
     */
    public void actualiserBordure(){
        super.setStyle("-fx-border-color: "+this.couleur+ " ; -fx-border-width: 1px;");
    }

    /**
     * Méthode qui permet de mettre a jour la position de la classe affiché
     */
    public void actualiserPosition(){
        this.setLayoutX(classeEntiere.getX());
        this.setLayoutY(classeEntiere.getY());
    }


    @Override
    public void actualiser() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VueClasseAffichage that = (VueClasseAffichage) o;
        return Objects.equals(nom, that.nom);
    }

    public void setClasseAffichee(){
        this.classeAffichee = !this.classeAffichee;
    }

    public void setClasseAffichee(boolean b){
        this.classeAffichee = b;
    }


    public void actualiserVisibilite(){
        this.setVisible(classeAffichee);
    }

    public boolean getClasseAffichee(){
        return this.classeAffichee;
    }
}
