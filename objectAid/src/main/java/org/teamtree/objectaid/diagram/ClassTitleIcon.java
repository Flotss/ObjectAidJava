package org.teamtree.objectaid.diagram;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ClassTitleIcon implements ClasseEntiereTitleIcon {

    @Override
    public Node getIcon() {
        final var parent = new StackPane();
        final var stackCircle = new Circle(8);
        stackCircle.setFill(Color.LIGHTBLUE);
        final var i = new Label("C");

        parent.getChildren().addAll(stackCircle, i);

        return parent;
    }

}
