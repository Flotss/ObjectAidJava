package org.teamtree.objectaid.MVC.Fleches;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import org.teamtree.objectaid.Classe.Relations.Relation;
import org.teamtree.objectaid.MVC.Model.Model;

/**
 * Classe qui permet de créer une flèche de composition
 */
public class FlecheComposition extends FlecheAssociation {

    /**
     * Le losange qui représente la composition
     */
    private final Polygon losange;

    /**
     * Constructeur de la flèche de composition
     *
     * @param model    Le model de l'application
     * @param relation La relation entre les deux classes
     */
    public FlecheComposition(Model model, Relation relation) {
        super(model, relation);

        this.losange = new Polygon();
        losange.getPoints().addAll(0.0, 10.0, 10.0, 0.0, 20.0, 10.0, 10., 20.0);
        losange.setFill(Color.BLACK);

        this.getChildren().addAll(losange);

        actualiser();
    }

    @Override
    public void actualiser() {
        super.actualiser();
        actualiserLosange();
    }

    /**
     * Méthode d'actualisation de la position du losange et de sa rotation
     */
    private void actualiserLosange() {
        // L'angle de la fleche
        double angle = Math.atan2(line.getEndY() - line.getStartY(), line.getEndX() - line.getStartX());

        // Rotation du losange
        losange.setRotate(Math.toDegrees(angle) - 90);

        // Position du losange
        losange.setTranslateX(line.getStartX() - 10);
        losange.setTranslateY(line.getStartY() - 10);
    }

    @Override
    public String getType() {
        return "Composition";
    }

}
