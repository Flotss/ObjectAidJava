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
        ClasseAffichage classe = new ClasseAffichage(c.getDefinition().getNom());
        //On crée les différentes parties de la classe
        HBox definition = classe.getDefinition();
        VBox constructeur = classe.getConstructeur();
        VBox attributs = classe.getAttributs();
        VBox methodes = classe.getMethodes();

        //partie definition: On génère l'affichage de la définition
        //Nom

        classe.setMaxSize(150,300);






        //partie definition
        String def = c.getDefinition().getNom();
        Label definitionLabel = new Label(def);

        //Icon selon le type de la classe
        definition.getChildren().addAll(fabriqueIcon().getIcon(), definitionLabel);
        classe.setDefinition(definition);

        classe.setAttributs(c);
        /*
        //partie attributs
        //On vérifie que les attributs doivent être affichés
        if(c.isAttributEstAffiche()) {
            //Si il existe des attributs, on crée une bordure sur la catégorie du dessus (pour crée une séparation)
            if (c.getAttributs().size() != 0) {
                definition.setStyle("-fx-border-color: transparent transparent black transparent; -fx-border-width: 1px;");
            }
            //Pour chaque attributs
            for (Attribut attributX: c.getAttributs()) {
                String att = fabriqueAcces(attributX.getAccessibilite()) + " " + attributX.getType() + " " + attributX.getNom();
                Label attributLabel = new Label(att);
                attributs.getChildren().add(attributLabel);
            }
        }
         */

        //partie constructeur
        //On vérifie que les constructeurs doivent être affichés
        if(c.isConstructeurEstAffiche()) {
            //Si il existe des constructeurs, on crée une bordure sur la catégorie du dessus (pour crée une séparation)
            if (c.getContructeurs().size() != 0) {
                attributs.setStyle("-fx-border-color: transparent transparent black transparent; -fx-border-width: 1px;");
            }
            //Pour chaque Constructeurs
            for (Constructeur constructeurX:c.getContructeurs()) {
                //Debut de la ligne (accesibilité, nom,...)
                String constr = fabriqueAcces(constructeurX.getAccessibilite()) + " " + constructeurX.getNom() + "(";
                int n = 0;
                //Pour chaque parametre du constructeur, on l'écrit dans l'affichage
                for(Parametre parametreX: constructeurX.getParametre()){
                    constr += parametreX.getType() + " " + parametreX.getNom();
                    if (n != constructeurX.getParametre().size() - 1) {
                        constr += ", ";
                    }
                    n++;
                }
                //On écrit la fin du constructeur puis on l'ajoute avec les autres constructeurs
                constr += ")";
                Label constrLabel = new Label(constr);
                constructeur.getChildren().add(constrLabel);
            }
        }

        //partie methodes
        if(c.isMethodsEstAffiche()) {
            if (c.getMethods().size() != 0) {
                constructeur.setStyle("-fx-border-color: transparent transparent black transparent; -fx-border-width: 1px;");
            }
            for (int i = 0; i < c.getMethods().size(); i++) {
                Methode methodeX = c.getMethods().get(i);
                String meth = fabriqueAcces(methodeX.getAccessibilite()) + " " + methodeX.getNom() + "(";
                for (int j = 0; j < methodeX.getParametre().size(); j++) {
                    meth += methodeX.getParametre().get(j).getType() + " " + methodeX.getParametre().get(j).getNom();
                    if (j != methodeX.getParametre().size() - 1) {
                        meth += ", ";
                    }
                }
                meth += "): " + c.getMethods().get(i).getTypeRetourne();
                Label methodeLabel = new Label(meth);
                methodes.getChildren().add(methodeLabel);
            }
        }


        //ajout des parties de la classe
        classe.getChildren().addAll(definition, attributs,constructeur, methodes);

        //coordonees de la classe
        classe.setLayoutX(c.getX());
        classe.setLayoutY(c.getY());

        classe.setStyle("-fx-border-color: black; -fx-border-width: 1px;");

        classe.setConstructeur(constructeur);
        classe.setMethodes(methodes);
        return classe;
    }

    /**
     * Methode permetant de généré l'icon de la classe
     * @return ClasseEntiereTitleIcon icon
     */
    private ClasseEntiereTitleIcon fabriqueIcon(){
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
