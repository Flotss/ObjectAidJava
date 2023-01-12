package org.teamtree.objectaid.MVC.Fleches;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import org.teamtree.objectaid.Classe.Relations.Relation;
import org.teamtree.objectaid.MVC.Model.Model;

/**
 * Cette classe représente une flèche d'héritage.
 */
public class FlecheHeritage extends Fleche {

    /**
     * Line qui représente la fermeture de la flèche
     */
    private final Line arrowFermeture;

    /**
     * Constructeur de la classe
     * @param model Le model de l'application
     * @param relation La relation entre les deux classes
     */
    public FlecheHeritage(Model model, Relation relation){
        super(model, relation);
        this.arrowFermeture = new Line();
        this.getChildren().add(arrowFermeture);

        // Positionnement de la fermeture de la flèche
        this.arrowFermeture.setStartX(arrow1.getEndX());
        this.arrowFermeture.setStartY(arrow1.getEndY());
        this.arrowFermeture.setEndX(arrow2.getEndX());
        this.arrowFermeture.setEndY(arrow2.getEndY());


        // Couleur de la flèche
        this.line.setStroke(Color.RED);
        this.arrow1.setStroke(Color.RED);
        this.arrow2.setStroke(Color.RED);
        this.arrowFermeture.setStroke(Color.RED);
        this.arrowFermeture.setStrokeWidth(2);
    }

    @Override
    public void actualiser(){
        super.actualiser();
        this.arrowFermeture.setStartX(arrow1.getEndX());
        this.arrowFermeture.setStartY(arrow1.getEndY());
        this.arrowFermeture.setEndX(arrow2.getEndX());
        this.arrowFermeture.setEndY(arrow2.getEndY());
    }

    @Override
    public String getType(){
        return "Heritage";
    }
}
