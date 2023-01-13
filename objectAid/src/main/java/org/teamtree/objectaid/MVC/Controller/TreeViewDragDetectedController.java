package org.teamtree.objectaid.MVC.Controller;

import javafx.scene.control.TreeView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import org.teamtree.objectaid.MVC.Model.Model;

/**
 * Représente le contrôleur qui gère le drag and drop sur l'arbre.
 */
public class TreeViewDragDetectedController extends ControllerBase<MouseEvent> {

    private final TreeView<String> treeView;

    public TreeViewDragDetectedController(final Model model, final TreeView<String> treeView) {
        super(model);

        this.treeView = treeView;
    }

    /**
     * Méthode qui permet de gérer le drag and drop sur l'arbre
     *
     * @param event the event which occurred
     */
    @Override
    public void handle(final MouseEvent event) {
        final var dragBoard = treeView.startDragAndDrop(TransferMode.ANY);
        final var content = new ClipboardContent();
        final var selectedItem = treeView.getSelectionModel().getSelectedItem();
        final var itemContent = selectedItem.getValue();

        content.putString(itemContent);
        dragBoard.setContent(content);

        event.consume();
    }
}
