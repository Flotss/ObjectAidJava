package org.teamtree.objectaid.MVC.Fleches;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import org.teamtree.objectaid.Classe.Relations.Relation;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.MVC.Vue.Fleche;

/**
 * Cette classe représente une flèche d'implémentation.
 */
public class FlecheImplementation extends Fleche {

    private final Line arrowFermeture;

    /**
     * Constructeur de la classe
     * @param model Le model de l'application
     * @param relation La relation entre les deux classes
     */
    public FlecheImplementation(Model model, Relation relation){
        super(model, relation);
        this.arrowFermeture = new Line();
        this.getChildren().add(arrowFermeture);

        this.arrowFermeture.setStartX(arrow1.getEndX());
        this.arrowFermeture.setStartY(arrow1.getEndY());
        this.arrowFermeture.setEndX(arrow2.getEndX());
        this.arrowFermeture.setEndY(arrow2.getEndY());


        this.line.setStroke(Color.GREEN);
        this.arrow1.setStroke(Color.GREEN);
        this.arrow2.setStroke(Color.GREEN);
        this.arrowFermeture.setStroke(Color.GREEN);
        this.arrowFermeture.setStrokeWidth(2);



        this.line.getStrokeDashArray().addAll(5d, 5d);
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
        return "Implementation";
    }
}
