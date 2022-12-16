package org.teamtree.objectaid.MVC.Vue;

import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.util.LineSeparator;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VueClasse extends VBox implements Observateur {

    private final Model model;
    private final ClasseEntiere classeEntiere;

    public VueClasse(final Model mod, final ClasseEntiere dto) {

        model = mod;
        classeEntiere = dto;

        final var title = new Label(dto.getName());
        final var separator = new LineSeparator();
        final var attributes = new Label(dto.getAttributes().toString());
        final var methodSeparator = new LineSeparator();
        final var methods = new Label(dto.getMethods().toString());

        getChildren().addAll(title, separator, attributes, methodSeparator, methods);

        setStyle("-fx-border-color: black; -fx-border-width: 1px;");
    }

    @Override
    public void actualiser() {
        throw new Error("Not yet implemented");
    }
}
