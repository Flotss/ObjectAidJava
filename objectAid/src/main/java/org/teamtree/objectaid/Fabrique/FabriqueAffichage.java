package org.teamtree.objectaid.Fabrique;

import org.teamtree.objectaid.Classe.*;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.MVC.Vue.VueClasseAffichage;
import org.teamtree.objectaid.MVC.diagramIcons.*;

/**
 * Classe qui permet de créer un affichage pour une classe
 */
public class FabriqueAffichage {

    /** La classe à afficher */
    private ClasseEntiere classeEntiere;

    /**
     Le model à associer
     */
    private Model model;

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

        //ajoute la classe qui est affiché à la classeEntiere
        classeEntiere.setClasseAffichage(classe);

        return classe;
    }

    /**
     * Methode permetant de généré l'icon de la classe
     * @return ClasseEntiereTitleIcon icon
     */
    public static ClasseEntiereTitleIcon fabriqueIcon(ClasseEntiere c){
        ClasseEntiereTitleIcon TitleIcon = switch (c.getDefinition().getEntite()) {
            case "class" -> new ClassTitleIcon();
            case "interface" -> new InterfaceTitleIcon();
            case "record" -> new RecordClassTitleIcon();
            case "abstract" -> new AbstractClassTitleIcon();
            default -> null;
        };
        return TitleIcon;

    }

    /**
     * Methode permetant de retourner le symbole de l'accesibitilé
     * @param acces String correspondant à l'accesibilité
     * @return symbole de cette accesibilité
     */
    public static String fabriqueAcces(String acces){
        return switch (acces) {
            case "public" -> "+";
            case "private" -> "-";
            case "protected" -> "#";
            default -> "";
        };
    }
}
