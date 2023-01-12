package org.teamtree.objectaid.MVC.Fleches;

import javafx.scene.Group;
import javafx.scene.shape.Line;
import org.teamtree.objectaid.Classe.Relations.Relation;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.MVC.Vue.Observateur;
import org.teamtree.objectaid.MVC.Vue.VueClasseAffichage;
import org.teamtree.objectaid.Point;


/**
 * Classe qui permet de créer une flèche pour une relation
 */
public class Fleche extends Group implements Observateur {

    /**
     * Vue de la classe de départ
     */
    private final VueClasseAffichage classeDepart;

    /**
     * Vue de la classe d'arrivée
     */
    private final VueClasseAffichage classeArrivee;

    /**
     * Line qui représente le trait de la flèche
     */
    protected final Line line;

    /**
     * Line qui représente une partie de la pointe de la flèche
     */
    protected final Line arrow1;

    /**
     * Line qui représente l'autre partie de la pointe de la flèche
     */
    protected final Line arrow2;

    private boolean estAffiche;


    /**
     * Constructeur de la classe
     * @param model Le model de l'application
     * @param relation La relation entre les deux classes
     */
    public Fleche(Model model, Relation relation){
        // Recherche des vues des classes de départ et d'arrivée
        this.classeDepart = model.getVueClasseAffichage(relation.getDepart());
        this.classeArrivee = model.getVueClasseAffichage(relation.getDestination());

        // Création des lignes
        line = new Line();
        arrow1 = new Line();
        arrow2 = new Line();
        arrow1.setStrokeWidth(2);
        arrow2.setStrokeWidth(2);

        this.getChildren().addAll(line, arrow1, arrow2);

        this.estAffiche=true;

        // Actualisation des positions pour former la ligne
        actualiserLesPoints();
    }

    /**
     * Méthode qui permet de mettre à jour les points de la flèche
     */
    private Point[] emplacementFleche(VueClasseAffichage classeDepart, VueClasseAffichage classeArrivee){
        Point start = new Point((int) (classeDepart.getLayoutX() + classeDepart.getWidth()/2), (int) (classeDepart.getLayoutY() + classeDepart.getHeight()/2));
        Point end = new Point((int) (classeArrivee.getLayoutX() + classeArrivee.getWidth()/2), (int) (classeArrivee.getLayoutY() + classeArrivee.getHeight()/2));


        // Si le centre des deux classes est à la hauteur des deux classes
        if (end.getY() <= start.getY() + classeDepart.getHeight()/2 && end.getY() >= start.getY() - classeDepart.getHeight()/2){
            // Si la classe de départ est à gauche de la classe d'arrivée
            if (end.getX() > start.getX()){
                start.setX((int) (start.getX() + classeDepart.getWidth()/2));
                end.setX((int) (end.getX() - classeArrivee.getWidth()/2));
            }
            // Si la classe de départ est à droite de la classe d'arrivée
            else{
                start.setX((int) (start.getX() - classeDepart.getWidth()/2));
                end.setX((int) (end.getX() + classeArrivee.getWidth()/2));
            }
            return new Point[]{start, end};
        }

        // Si le centre des deux classes est à la largeur des deux classes
        if (end.getX() <= start.getX() + classeDepart.getWidth()/2 && end.getX() >= start.getX() - classeDepart.getWidth()/2){
            // Si la classe de départ est en haut de la classe d'arrivée
            if (end.getY() > start.getY()){
                start.setY((int) (start.getY() + classeDepart.getHeight()/2));
                end.setY((int) (end.getY() - classeArrivee.getHeight()/2));
            }
            // Si la classe de départ est en bas de la classe d'arrivée
            else{
                start.setY((int) (start.getY() - classeDepart.getHeight()/2));
                end.setY((int) (end.getY() + classeArrivee.getHeight()/2));
            }
            return new Point[]{start, end};
        }



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
        //TODO: a séparé en LesPoints et en Visibilite
        actualiserLesPoints();
        actualiserVisibilite();
    }


    private void actualiserLesPoints(){
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


        // Les flèches sont dessinées à 30° par rapport à la ligne
        double arrowAngle = start.getX() >= end.getX() ? Math.toRadians(45) : Math.toRadians(225);

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

    /**
     * Méthode qui permet de definir la visibilite de la fleche
     * @param visible true si la fleche doit etre visible, false sinon
     */
    public void definirVisibilite(boolean visible){

        this.estAffiche = (this.classeDepart.getClasseAffichee() && this.classeArrivee.getClasseAffichee()) & visible;

        actualiserVisibilite();
    }

    /**
     * Méthode qui permet d'actualiser la visibilite de la fleche
     */
    public void actualiserVisibilite(){
        this.setVisible(estAffiche);
        this.arrow1.setVisible(estAffiche);
        this.arrow2.setVisible(estAffiche);
        this.line.setVisible(estAffiche);
    }

    /**
     * Méthode qui permet de récupérer la classe de départ
     * @return la classe de départ
     */
    public VueClasseAffichage getVueClasseDepart(){
        return this.classeDepart;
    }

    /**
     * Méthode qui permet de récupérer la classe d'arrivée
     * @return la classe d'arrivée
     */
    public VueClasseAffichage getVueClasseArrivee(){
        return this.classeArrivee;
    }

    /**
     * Méthode qui permet de récupérer le type de la flèche
     * @return le type de la flèche
     */
    public String getType(){
        return "fleche";
    }
}
