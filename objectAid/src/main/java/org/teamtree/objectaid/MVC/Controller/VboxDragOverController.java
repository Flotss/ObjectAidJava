package org.teamtree.objectaid.MVC.Controller;

import org.teamtree.objectaid.MVC.Model.Model;

import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;

public class VboxDragOverController extends ControllerBase<DragEvent> {

    private final VBox vbox;

    public VboxDragOverController(final Model model, final VBox vbox) {
        super(model);
        this.vbox = vbox;
    }

    /**
     * Invoked when a specific event of the type for which this handler is
     * registered happens.
     *
     * @param event the event which occurred
     */
    @Override
    public void handle(final DragEvent event) {
        if (event.getGestureSource() != vbox && event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }

        event.consume();
    }
}
