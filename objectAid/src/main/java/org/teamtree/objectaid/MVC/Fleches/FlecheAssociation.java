package org.teamtree.objectaid.MVC.Fleches;

import javafx.scene.layout.HBox;
import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.Classe.Relations.Association;
import org.teamtree.objectaid.Classe.Relations.Relation;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.MVC.Vue.Observateur;
import org.teamtree.objectaid.Point;

public class FlecheAssociation extends HBox implements Fleche, Observateur {

    private String nom;
    private ClasseEntiere classeDepart;
    private ClasseEntiere classeArrivee;

    private final Model model;

    public FlecheAssociation(Model model, Relation relation){
        this.model = model;
        Association association =  (Association) relation;
    }


    @Override
    public String getClasseDepart() {
        return null;
    }

    @Override
    public String getClasseArrivee() {
        return null;
    }

    @Override
    public String getTypeFleche() {
        return null;
    }

    @Override
    public void actualiser() {

    }
}
