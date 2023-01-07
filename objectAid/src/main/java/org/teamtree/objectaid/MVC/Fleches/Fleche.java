package org.teamtree.objectaid.MVC.Fleches;

import javafx.scene.Group;
import javafx.scene.shape.Line;
import org.teamtree.objectaid.Classe.Relations.Relation;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.MVC.Vue.Observateur;
import org.teamtree.objectaid.MVC.Vue.VueClasseAffichage;
import org.teamtree.objectaid.Point;

public class Fleche extends Group implements Observateur {
    private final VueClasseAffichage classeDepart;
    private final VueClasseAffichage classeArrivee;
    private final Relation relation;
    private final Model model;

    protected final Line line;
    protected final Line arrow1;
    protected final Line arrow2;

    public Fleche(Model model, Relation relation){
        this.model = model;
        this.relation = relation;
        this.classeDepart = model.getVueClasseAffichage(relation.getDepart());
        this.classeArrivee = model.getVueClasseAffichage(relation.getDestination());

        line = new Line();
        arrow1 = new Line();
        arrow2 = new Line();

        this.getChildren().addAll(line, arrow1, arrow2);

        actualiserLesPoints();
    }

    public Point[] emplacementFleche(VueClasseAffichage classeDepart, VueClasseAffichage classeArrivee){
        Point start = new Point((int) (classeDepart.getLayoutX() + classeDepart.getWidth()/2), (int) (classeDepart.getLayoutY() + classeDepart.getHeight()/2));
        Point end = new Point((int) (classeArrivee.getLayoutX() + classeArrivee.getWidth()/2), (int) (classeArrivee.getLayoutY() + classeArrivee.getHeight()/2));

        // Si le départ est a gauche de la fin : point vers la droite
        if (start.getX() < end.getX()){
            start.setX((int) (start.getX() + classeDepart.getWidth()/2));
            end.setX((int) (end.getX() - classeArrivee.getWidth()/2));
        }else{
            // alors le départ est droite
            start.setX((int) (start.getX() - classeDepart.getWidth()/2));
            end.setX((int) (end.getX() + classeArrivee.getWidth()/2));
        }

        // Si le départ est en haut de la fin : point vers le bas
        if (start.getY() < end.getY()){
            start.setY((int) (start.getY() + classeDepart.getHeight()/2));
            end.setY((int) (end.getY() - classeArrivee.getHeight()/2));
        }else{
            // alors le départ est en bas
            start.setY((int) (start.getY() - classeDepart.getHeight()/2));
            end.setY((int) (end.getY() + classeArrivee.getHeight()/2));
        }

        return new Point[]{start, end};
    }

    @Override
    public void actualiser() {
        actualiserLesPoints();
    }


    protected void actualiserLesPoints(){
        Point[] points = emplacementFleche(classeDepart, classeArrivee);
        Point start = points[0];
        Point end = points[1];


        this.line.setStartX(start.getX());
        this.line.setStartY(start.getY());
        this.line.setEndX(end.getX());
        this.line.setEndY(end.getY());

        if (start.getX() == end.getX() || start.getY() == end.getY()) {
            return;
        }

        double slope = (start.getY() - end.getY()) / (start.getX() - end.getX());
        double lineAngle = Math.atan(slope);


        // Les flèches sont dessinées à 45° par rapport à la ligne
        double arrowAngle = start.getX() >= end.getX() ? Math.toRadians(45) : -Math.toRadians(225);

        // Première flèche ligne
        this.arrow1.setStartX(end.getX());
        this.arrow1.setStartY(end.getY());
        this.arrow1.setEndX(end.getX() + 10 * Math.cos(lineAngle - arrowAngle));
        this.arrow1.setEndY(end.getY() + 10 * Math.sin(lineAngle - arrowAngle));

        // Deuxième flèche ligne
        this.arrow2.setStartX(end.getX());
        this.arrow2.setStartY(end.getY());
        this.arrow2.setEndX(end.getX() + 10 * Math.cos(lineAngle + arrowAngle));
        this.arrow2.setEndY(end.getY() + 10 * Math.sin(lineAngle + arrowAngle));
    }
}
