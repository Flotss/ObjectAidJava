package org.teamtree.objectaid.Fabrique;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import org.teamtree.objectaid.Classe.*;
import org.teamtree.objectaid.MVC.Controller.ClasseEntiereClickedController;
import org.teamtree.objectaid.MVC.Controller.ClickDroitClasseController;
import org.teamtree.objectaid.MVC.Controller.DeplacementClasseDragAndDropController;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.MVC.Vue.VueClasseAffichage;
import org.teamtree.objectaid.MVC.diagramIcons.*;

/**
 * Classe qui permet de créer un affichage pour une classe
 */
public class FabriqueAffichage {

    /** La classe à afficher */
    private final ClasseEntiere classeEntiere;

    /**
     Le model à associer
     */
    private final Model model;

    /**
     * Constructeur de la classe
     * @param classeEntiere La classe à afficher
     */
    public FabriqueAffichage(ClasseEntiere classeEntiere, Model model){
        this.classeEntiere = classeEntiere;
        this.model = model;
    }

    /**
     * Méthode qui permet de créer un affichage pour une classe
     * @return Un affichage pour une classe
     */
    public VueClasseAffichage affichage(){
        //On crée la classeAffichage correspondant a un VBox, servant à recevoir tout le bloc de la classe
        VueClasseAffichage classe = new VueClasseAffichage(classeEntiere, model);

        //on affecte la taille maximale de la classe
        classe.setMaxWidth(150);

        //on s'occupe de la partie definition
        classe.setDefinition();

        //on s'occupe de la partie attributs
        classe.setAttributs();

        //on s'occupe de la partie constructeurs
        classe.setConstructeur();

        //on s'occupe de la partie methodes
        classe.setMethodes();

        //ajout des parties de la classe
        classe.afficherClasse();

        //coordonees de la classe
        classe.actualiserPosition();

        classe.setStyle("-fx-border-color: black; -fx-border-width: 1px;");

        // On ajoute le controller lorsqu'on clique sur la classe
        classe.setOnMouseClicked(new ClasseEntiereClickedController(model));

        //on ajoute le drag and drop de la classe
        classe.setOnMouseDragged(new DeplacementClasseDragAndDropController(model));

        //on ajoute les tooltip aux labels des classes pour voir tout le texte

        //tooltip pour les methodes
        for (Node methode : classe.getMethodes().getChildren()) {
            Node labelNode = ((HBox) methode).getChildren().get(1);
            ((Label)labelNode).setTooltip(new Tooltip(((Label)labelNode).getText()));
        }

        //tooltip pour les attributs
        for (Node attribut : classe.getAttributs().getChildren()) {
            Node labelNode = ((HBox) attribut).getChildren().get(1);
            ((Label)labelNode).setTooltip(new Tooltip(((Label)labelNode).getText()));
        }

        //tooltip pour les attributs avec relation de la classe
        for (Node attributRelation : classe.getAttributsRelation().getChildren()) {
            Node labelNode = ((HBox) attributRelation).getChildren().get(1);
            ((Label)labelNode).setTooltip(new Tooltip(((Label)labelNode).getText()));
        }

        //tooltip pour les construteurs de la classe
        for (Node constructeur : classe.getConstructeur().getChildren()) {
            Node labelNode = ((HBox) constructeur).getChildren().get(1);
            ((Label)labelNode).setTooltip(new Tooltip(((Label)labelNode).getText()));
        }

        //tooltip pour le nom de la classe
        ((Label)classe.getDefinition().getChildren().get(1)).setTooltip(new Tooltip(((Label)classe.getDefinition().getChildren().get(1)).getText()));

        //on ajoute le controller lorsqu'on clique droit sur la classe
        classe.setOnContextMenuRequested(new ClickDroitClasseController(model,classe));

        //ajoute la classe qui est affiché à la classeEntiere
        classeEntiere.setClasseAffichage(classe);

        return classe;
    }

    /**
     * Methode permetant de généré l'icon de la classe
     * @return ClasseEntiereTitleIcon icon
     */
    public static ClasseEntiereTitleIcon fabriqueIcon(ClasseEntiere c){
        return switch (c.getDefinition().getEntite()) {
            case "class" -> new ClassTitleIcon();
            case "interface" -> new InterfaceTitleIcon();
            case "record" -> new RecordClassTitleIcon();
            case "abstract" -> new AbstractClassTitleIcon();
            case "enum" -> new EnumTitleIcon();
            default -> null;
        };

    }
}
