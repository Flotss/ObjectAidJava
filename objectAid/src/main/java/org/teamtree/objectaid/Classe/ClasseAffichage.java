package org.teamtree.objectaid.Classe;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.teamtree.objectaid.Fabrique.FabriqueAffichage;
import org.teamtree.objectaid.MVC.diagramIcons.*;

/**
 * Classe qui représente l'affichage d'une classe
 */
public class ClasseAffichage extends VBox {

    /**
     * Nom de cette classe, permettant de la retrouver lorsqu'on clique dessus notamment
     */
    private String nom;
    private HBox definition;
    private VBox constructeur;
    private VBox attributs;
    private VBox methodes;

    public ClasseAffichage(String nom){
        this.nom = nom;
        this.definition = new HBox();
        this.constructeur = new VBox();
        this.attributs = new VBox();
        this.methodes = new VBox();
    }

    public void setConstructeur(ClasseEntiere c){

    }

    public void setAttributs(ClasseEntiere c){
        if(c.isAttributEstAffiche()) {
            //Si il existe des attributs, on crée une bordure sur la catégorie du dessus (pour crée une séparation)
            if (c.getAttributs().size() != 0) {
                definition.setStyle("-fx-border-color: transparent transparent black transparent; -fx-border-width: 1px;");
            }
            //Pour chaque attributs
            for (Attribut attributX: c.getAttributs()) {
                String att = FabriqueAffichage.fabriqueAcces(attributX.getAccessibilite()) + " " + attributX.getType() + " " + attributX.getNom();
                Label attributLabel = new Label(att);
                attributs.getChildren().add(attributLabel);
            }
        }
    }

    public String getNom(){
        return this.nom;
    }

    public HBox getDefinition() {
        return definition;
    }

    public void setDefinition(HBox definition) {
        this.definition = definition;
    }

    public VBox getConstructeur() {
        return constructeur;
    }

    public void setConstructeur(VBox constructeur) {
        this.constructeur = constructeur;
    }

    public VBox getAttributs() {
        return attributs;
    }

    public void setAttributs(VBox attributs) {
        this.attributs = attributs;
    }

    public VBox getMethodes() {
        return methodes;
    }

    public void setMethodes(VBox methodes) {
        this.methodes = methodes;
    }
}
