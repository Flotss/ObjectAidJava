package org.teamtree.objectaid.diagram;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

public class AbstractClassTitleIcon implements ClasseEntiereTitleIcon {

    @Override
    public Node getIcon() {
        final var parent = new StackPane();
        final var stackCircle = new Circle(5);
        final var a = new Label("A");

        parent.getChildren().addAll(stackCircle, a);
        
        return parent;
    }
}