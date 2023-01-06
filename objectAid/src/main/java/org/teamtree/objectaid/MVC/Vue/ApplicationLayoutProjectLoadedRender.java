package org.teamtree.objectaid.MVC.Vue;

import javafx.scene.Scene;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import org.teamtree.objectaid.Fabrique.SceneFactory;
import org.teamtree.objectaid.MVC.Model.Model;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public class ApplicationLayoutProjectLoadedRender implements SceneFactory {
    private final Model model;
    private final BorderPane base;

    public ApplicationLayoutProjectLoadedRender(Model model) {
        this.model = model;
        this.base = new BorderPane();

        TreeItem<String> root = new TreeItem<>();
        Arrays.stream(Objects.requireNonNull(model.getCurrentProject().listFiles())).forEach(file -> createTree(file, root));

        final var treeView = new TreeView<>(root);

        treeView.setShowRoot(false);

        base.setLeft(treeView);

        final var label = new Label("Projet chargé : " + model.getCurrentProject().getName());

        base.setCenter(label);
    }

    public static void createTree(File file, TreeItem<String> parent) {
        if (file.isDirectory()) {
            final var treeItem = new CheckBoxTreeItem<>(file.getName());
            parent.getChildren().add(treeItem);

            Arrays.stream(Objects.requireNonNull(file.listFiles())).forEach(f -> createTree(f, treeItem));
        } else {

            if (file.getName().endsWith(".java")) {
                return;
            }

            final var name = file.getName().endsWith(".class")
                    ? file.getName().substring(0, file.getName().length() - 6)
                    : file.getName();

            parent.getChildren().add(new CheckBoxTreeItem<>(name));
        }
    }

    @Override
    public Scene getScene() {
        return new Scene(base, 1920, 1080);
    }
}
