package org.teamtree.objectaid.MVC.Fleches;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import org.teamtree.objectaid.Classe.Relations.Association;
import org.teamtree.objectaid.Classe.Relations.Relation;
import org.teamtree.objectaid.MVC.Model.Model;

public class FlecheAssociation extends Fleche {

    private Label label;
    private Label cardinaliteDepart;
    private Label cardinaliteArrivee;

    public FlecheAssociation(Model model, Relation relation){
        super(model, relation);
        this.line.setStroke(Color.BLUE);
        this.arrow1.setStroke(Color.BLUE);
        this.arrow2.setStroke(Color.BLUE);

        Association association = (Association) relation;
        Label label = new Label(association.getAttribut().getNom());
        this.label = label;
        this.getChildren().add(label);

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

    public void actualiserLeLabel(){
        label.setLayoutX((line.getStartX() + line.getEndX())/2);
        label.setLayoutY((line.getStartY() + line.getEndY())/2);
    }

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
