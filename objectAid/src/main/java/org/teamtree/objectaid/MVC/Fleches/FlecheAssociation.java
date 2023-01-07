package org.teamtree.objectaid.MVC.Fleches;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
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
    private Label label;

    /**
     * La cardinalité de la classe de départ
     */
    private Label cardinaliteDepart;

    /**
     * La cardinalité de la classe d'arrivée
     */
    private Label cardinaliteArrivee;

    /**
     * Constructeur de la classe
     * @param model Le model de l'application
     * @param relation La relation entre les deux classes
     */
    public FlecheAssociation(Model model, Relation relation){
        super(model, relation);

        // Changement de la couleur de la flèche pour l'association : bleu
        this.line.setStroke(Color.BLUE);
        this.arrow1.setStroke(Color.BLUE);
        this.arrow2.setStroke(Color.BLUE);

        // Création du label pour le nom de l'association
        Association association = (Association) relation;
        Label label = new Label(association.getAttribut().getNom());
        this.label = label;
        this.getChildren().add(label);

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
        actualiserLeLabel();
        actualiserCardinalite();
    }

    /**
     * Méthode qui permet d'actualiser la position du label
     */
    public void actualiserLeLabel(){
        label.setLayoutX((line.getStartX() + line.getEndX())/2);
        label.setLayoutY((line.getStartY() + line.getEndY())/2);
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


}
