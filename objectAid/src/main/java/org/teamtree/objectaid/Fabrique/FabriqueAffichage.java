package org.teamtree.objectaid.Fabrique;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.teamtree.objectaid.Classe.ClasseAffichage;
import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.Classe.Constructeur;
import org.teamtree.objectaid.Classe.Methode;
import org.teamtree.objectaid.MVC.diagramIcons.ClassTitleIcon;
import org.teamtree.objectaid.MVC.diagramIcons.ClasseEntiereTitleIcon;
import org.teamtree.objectaid.MVC.diagramIcons.InterfaceTitleIcon;

public class FabriqueAffichage {
    private ClasseEntiere c;

    public FabriqueAffichage(ClasseEntiere classeEntiere){
        this.c = classeEntiere;
    }

    public ClasseAffichage affichage(){
        ClasseAffichage classe = new ClasseAffichage(c.getDefinition().getNom());
        HBox definition = new HBox();
        VBox constructeur = new VBox();
        VBox attributs = new VBox();
        VBox methodes = new VBox();

        //partie definition
        String def = c.getDefinition().getNom();
        Label definitionLabel = new Label(def);

        definition.getChildren().addAll(fabriqueIcon().getIcon(), definitionLabel);

        //partie attributs
        if(c.isAttributEstAffiche()) {
            if (c.getAttributs().size() != 0) {
                definition.setStyle("-fx-border-color: transparent transparent black transparent; -fx-border-width: 1px;");
            }

            for (int i = 0; i < c.getAttributs().size(); i++) {
                String att = fabriqueAcces(c.getAttributs().get(i).getAccessibilite()) + " " + c.getAttributs().get(i).getType() + " " + c.getAttributs().get(i).getNom();
                Label attributLabel = new Label(att);
                attributs.getChildren().add(attributLabel);
            }

        }

        //partie constructeur
        if(c.isConstructeurEstAffiche()) {
            if (c.getContructeurs().size() != 0) {
                attributs.setStyle("-fx-border-color: transparent transparent black transparent; -fx-border-width: 1px;");
            }
            for (int i = 0; i < c.getContructeurs().size(); i++) {
                Constructeur constructeurx = c.getContructeurs().get(i);
                String constr = fabriqueAcces(constructeurx.getAccessibilite()) + " " + constructeurx.getNom() + "(";
                for (int j = 0; j < constructeurx.getParametre().size(); j++) {
                    constr += constructeurx.getParametre().get(j).getType() + " " + constructeurx.getParametre().get(j).getNom();
                    if (j != constructeurx.getParametre().size() - 1) {
                        constr += ", ";
                    }
                }
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

        return classe;
    }

    private ClasseEntiereTitleIcon fabriqueIcon(){
        ClasseEntiereTitleIcon TitleIcon = null;
        switch (c.getDefinition().getEntite()){
            case "class":
                TitleIcon = new ClassTitleIcon();
                break;
            case "interface":
                TitleIcon = new InterfaceTitleIcon();
                break;
        }
        return TitleIcon;

    }

    private String fabriqueAcces(String acces){
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
