package org.teamtree.objectaid.MVC.Fleches;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.*;
import org.teamtree.objectaid.Classe.Relations.Association;
import org.teamtree.objectaid.Classe.Relations.Relation;
import org.teamtree.objectaid.MVC.Model.Model;

/**
 * Classe qui permet de créer une flèche pour une association
 */
public class FlecheAssociation extends Fleche {

    /**
     * Le label qui représente le nom de l'association
     */
    private final HBox nomFleche;

    /**
     * La cardinalité de la classe de départ
     */
    private final Label cardinaliteDepart;

    /**
     * La cardinalité de la classe d'arrivée
     */
    private final Label cardinaliteArrivee;

    /**
     * Constructeur de la classe
     * @param model Le model de l'application
     * @param relation La relation entre les deux classes
     */
    public FlecheAssociation(Model model, Relation relation){
        super(model, relation);

        // Création du label pour le nom de l'association
        Association association = (Association) relation;
        this.nomFleche = new HBox();

        Shape logoAccess = ((Association) relation).getAttribut().getAccessibilite().getShape();
        this.nomFleche.getChildren().add(logoAccess);

        Label label = new Label(association.getAttribut().getNom());
        this.nomFleche.getChildren().add(label);
        this.getChildren().add(nomFleche);

        // Création des labels pour les cardinalités
        Label cardinaliteDepart = new Label(association.getCardinalite1());
        this.cardinaliteDepart = cardinaliteDepart;
        this.getChildren().add(cardinaliteDepart);

        Label cardinaliteArrivee = new Label(association.getCardinalite2());
        this.cardinaliteArrivee = cardinaliteArrivee;
        this.getChildren().add(cardinaliteArrivee);
    }

    @Override
    public void actualiser(){
        super.actualiser();
        actualiserNom();
        actualiserCardinalite();
    }

    /**
     * Méthode qui permet d'actualiser la position du label
     */
    public void actualiserNom(){
        nomFleche.setLayoutX((line.getStartX() + line.getEndX())/2);
        nomFleche.setLayoutY((line.getStartY() + line.getEndY())/2);
    }

    /**
     * Méthode qui permet d'actualiser la position des cardinalités
     */
    public void actualiserCardinalite(){
        if (line.getStartX() < line.getEndX()){
            cardinaliteDepart.setLayoutX(line.getStartX()+10);
            cardinaliteArrivee.setLayoutX(line.getEndX()-10);
        } else {
            cardinaliteDepart.setLayoutX(line.getStartX()-10);
            cardinaliteArrivee.setLayoutX(line.getEndX()+10);
        }

        if (line.getStartY() < line.getEndY()){
            cardinaliteDepart.setLayoutY(line.getStartY()+10);
            cardinaliteArrivee.setLayoutY(line.getEndY()-10);
        } else {
            cardinaliteDepart.setLayoutY(line.getStartY()-10);
            cardinaliteArrivee.setLayoutY(line.getEndY()+10);
        }
    }

    public String getType(){
        return "Association";
    }
}
