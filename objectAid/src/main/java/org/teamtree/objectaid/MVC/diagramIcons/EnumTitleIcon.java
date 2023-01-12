package org.teamtree.objectaid.MVC.diagramIcons;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Classe qui permet de créer une icône pour un record
 */
public class EnumTitleIcon implements ClasseEntiereTitleIcon {

    @Override
    public Node getIcon() {
        final var parent = new StackPane();
        final var stackCircle = new Circle(8);
        stackCircle.setFill(Color.ORANGE);
        final var r = new Label("E");

        parent.getChildren().addAll(stackCircle, r);

        return parent;
    }
}
