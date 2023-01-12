package org.teamtree.objectaid.MVC.diagramIcons;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Classe qui permet de créer une icône pour une classe abstraite
 */
public class AbstractClassTitleIcon implements ClasseEntiereTitleIcon {

    @Override
    public Node getIcon() {
        final var parent = new StackPane();
        final var stackCircle = new Circle(8);
        stackCircle.setFill(Color.DARKRED);
        final var a = new Label("A");

        parent.getChildren().addAll(stackCircle, a);
        
        return parent;
    }
}
