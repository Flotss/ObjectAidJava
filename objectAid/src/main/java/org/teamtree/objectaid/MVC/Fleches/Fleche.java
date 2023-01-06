package org.teamtree.objectaid.MVC.Fleches;

import javafx.scene.shape.Line;
import org.teamtree.objectaid.Classe.Relations.Relation;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.MVC.Vue.Observateur;
import org.teamtree.objectaid.MVC.Vue.VueClasseAffichage;
import org.teamtree.objectaid.Point;

public class Fleche extends Line implements Observateur {
    private final VueClasseAffichage classeDepart;
    private final VueClasseAffichage classeArrivee;
    private final Relation relation;
    private final Model model;

    public Fleche(Model model, Relation relation){
        this.model = model;
        this.relation = relation;
        this.classeDepart = model.getVueClasseAffichage(relation.getDepart());
        this.classeArrivee = model.getVueClasseAffichage(relation.getDestination());

        actualiser();
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
        Point[] points = emplacementFleche(classeDepart, classeArrivee);


        this.setStartX(points[0].getX());
        this.setStartY(points[0].getY());
        this.setEndX(points[1].getX());
        this.setEndY(points[1].getY());
    }
}
