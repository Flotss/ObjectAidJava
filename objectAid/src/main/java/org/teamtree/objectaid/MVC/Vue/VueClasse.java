package org.teamtree.objectaid.MVC.Vue;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.teamtree.objectaid.Classe.ClasseAffichage;
import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.Fabrique.FabriqueAffichage;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.MVC.diagramIcons.ClassTitleIcon;
import org.teamtree.objectaid.MVC.diagramIcons.InterfaceTitleIcon;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class VueClasse extends Pane implements Observateur {

    private Model model;
    private ArrayList<VBox> classes;

    public VueClasse(Model mod) {
        this.model = mod;
        this.classes = new ArrayList<>();

        for (ClasseEntiere c:mod.getClasses()) {

            FabriqueAffichage f = new FabriqueAffichage(c);
            ClasseAffichage classe = f.affichage();


            //ajout de la classe au pane
            this.getChildren().add(classe);
        }

    }

    @Override
    public void actualiser() {
        throw new Error("Not yet implemented");
    }
}
