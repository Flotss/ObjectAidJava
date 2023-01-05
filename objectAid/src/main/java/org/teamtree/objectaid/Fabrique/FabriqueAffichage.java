package org.teamtree.objectaid.Fabrique;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.teamtree.objectaid.Classe.*;
import org.teamtree.objectaid.MVC.diagramIcons.*;

/**
 * Classe qui permet de créer un affichage pour une classe
 */
public class FabriqueAffichage {

    /** La classe à afficher */
    private ClasseEntiere c;

    /**
     * Constructeur de la classe
     * @param classeEntiere La classe à afficher
     */
    public FabriqueAffichage(ClasseEntiere classeEntiere){
        this.c = classeEntiere;
    }

    /**
     * Méthode qui permet de créer un affichage pour une classe
     * @return Un affichage pour une classe
     */
    public ClasseAffichage affichage(){
        //On crée la classeAffichage correspondant a un VBox, servant à recevoir tout le bloc de la classe
        ClasseAffichage classe = new ClasseAffichage(c);

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

        //ajoute la classe qui est affiché à la classeEntiere
        c.setClasseAffichage(classe);

        return classe;
    }

    /**
     * Methode permetant de généré l'icon de la classe
     * @return ClasseEntiereTitleIcon icon
     */
    public static ClasseEntiereTitleIcon fabriqueIcon(ClasseEntiere c){
        ClasseEntiereTitleIcon TitleIcon = null;
        switch (c.getDefinition().getEntite()){
            case "class":
                TitleIcon = new ClassTitleIcon();
                break;
            case "interface":
                TitleIcon = new InterfaceTitleIcon();
                break;
            case "record":
                TitleIcon = new RecordClassTitleIcon();
                break;
            case "abstract":
                TitleIcon = new AbstractClassTitleIcon();
                break;
        }
        return TitleIcon;

    }

    /**
     * Methode permetant de retourner le symbole de l'accesibitilé
     * @param acces String correspondant à l'accesibilité
     * @return symbole de cette accesibilité
     */
    public static String fabriqueAcces(String acces){
        switch (acces){
            case "public":
                return "+";
            case "private":
                return "-";
            case "protected":
                return "#";
            default:
                return "";
        }
    }
}
