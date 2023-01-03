package org.teamtree.objectaid.Fabrique;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.MVC.diagramIcons.ClassTitleIcon;
import org.teamtree.objectaid.MVC.diagramIcons.InterfaceTitleIcon;

public class FabriqueAffichage {
    private ClasseEntiere c;

    public FabriqueAffichage(ClasseEntiere classeEntiere){
        this.c = classeEntiere;
    }

    public VBox affichage(){
        VBox classe = new VBox();
        HBox definition = new HBox();
        VBox attributs = new VBox();
        VBox methodes = new VBox();

        //partie definition
        String def = c.getDefinition().getNom();
        Label definitionLabel = new Label(def);
        switch (c.getDefinition().getEntite()){
            case "class":
                ClassTitleIcon classTitleIcon = new ClassTitleIcon();
                definition.getChildren().addAll(classTitleIcon.getIcon(), definitionLabel);
                break;
            case "interface":
                InterfaceTitleIcon interfaceTitleIcon = new InterfaceTitleIcon();
                definition.getChildren().addAll(interfaceTitleIcon.getIcon(), definitionLabel);
                break;
        }
/*
            //partie attributs
            for (int i = 0; i < c.getAttributes().size(); i++) {
                String att = c.getAttributes().get(i).getAccessibilite() + " " + c.getAttributes().get(i).getType() + " " + c.getAttributes().get(i).getNom();
                Label attributLabel = new Label(att);
                attributs.getChildren().add(attributLabel);
            }

            //partie methodes
            for (int i = 0; i < c.getMethods().size(); i++) {
                String meth = c.getMethods().get(i).getAccessibilite() + " " + c.getMethods().get(i).getType() + " " + c.getMethods().get(i).getNom() + "(";
                for (int j = 0; j < c.getMethods().get(i).getArguments().size(); j++) {
                    meth += c.getMethods().get(i).getArguments().get(j).getType() + " " + c.getMethods().get(i).getArguments().get(j).getNom();
                    if (j != c.getMethods().get(i).getArguments().size() - 1) {
                        meth += ", ";
                    }
                }
                meth += ")";
                Label methodeLabel = new Label(meth);
                methodes.getChildren().add(methodeLabel);
            }
            */

        //ajout des parties de la classe
        classe.getChildren().addAll(definition, attributs, methodes);

        //coordonees de la classe
        classe.setLayoutX(c.getX());
        classe.setLayoutY(c.getY());

        classe.setStyle("-fx-border-color: black; -fx-border-width: 1px;");

        return classe;
    }
}
