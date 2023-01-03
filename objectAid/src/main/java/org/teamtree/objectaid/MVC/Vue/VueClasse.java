package org.teamtree.objectaid.MVC.Vue;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.teamtree.objectaid.Classe.ClasseAffichage;
import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.Fabrique.FabriqueAffichage;
import org.teamtree.objectaid.MVC.Controller.ClasseEntiereClickedController;
import org.teamtree.objectaid.MVC.Model.Model;

import java.util.ArrayList;

public class VueClasse extends Pane implements Observateur {

    private final Model model;
    private final ArrayList<VBox> classes;

    public VueClasse(Model mod) {
        this.model = mod;
        this.classes = new ArrayList<>();

        actualiser();
    }

    @Override
    public void actualiser() {
        this.getChildren().clear();

        for (ClasseEntiere c : model.getClasses()) {
            FabriqueAffichage f = new FabriqueAffichage(c);
            ClasseAffichage classe = f.affichage();

            setBorderColor(classe);

            this.getChildren().add(classe);
            classe.setOnMouseClicked(new ClasseEntiereClickedController(model));
        }
    }

    public void setBorderColor(final ClasseAffichage node) {
        final String color = model.getCurrentClickedClass().equals(node.getNom()) ? "blue" : "black";
        final var builderStyle = new StringBuilder();
        builderStyle.append(node.getStyle());
        builderStyle.append("-fx-border-color: ");
        builderStyle.append(color);
        builderStyle.append("; -fx-border-width: 1px;");

        node.setStyle(builderStyle.toString());
    }

}
