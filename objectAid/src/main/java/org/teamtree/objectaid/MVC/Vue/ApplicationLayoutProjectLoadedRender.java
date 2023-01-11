package org.teamtree.objectaid.MVC.Vue;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.Fabrique.SceneFactory;
import org.teamtree.objectaid.MVC.Controller.MenuItemController;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.service.JavaProjectClassLoader;
import org.teamtree.objectaid.util.FileExtension;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class ApplicationLayoutProjectLoadedRender implements SceneFactory {
    private final Model model;
    private final BorderPane base;

    public ApplicationLayoutProjectLoadedRender(Model model) {
        this.model = model;
        this.base = new BorderPane();

        final var scrollPane = new ScrollPane();

        TreeItem<String> root = new TreeItem<>();

        final var classLoader = new JavaProjectClassLoader(model.getCurrentProject(), model);
        classLoader.loadClasses(model.getCurrentProject().toFile());

        Arrays.stream(Objects.requireNonNull(model.getCurrentProject().toFile().listFiles())).forEach(file -> createTree(file, root));


        final var treeView = new TreeView<>(root);

        treeView.setOnDragDetected(event -> {
            final var dragBoard = treeView.startDragAndDrop(TransferMode.ANY);
            final var content = new ClipboardContent();
            final var selectedItem = treeView.getSelectionModel().getSelectedItem();
            final var itemContent = selectedItem.getValue();

            content.putString(itemContent);
            dragBoard.setContent(content);

            System.out.println("Drag detected, content: " + itemContent);

            event.consume();
        });

        treeView.setShowRoot(false);
        scrollPane.setContent(treeView);

        scrollPane.setFitToHeight(true);

        base.setLeft(scrollPane);

        final var vbox = new VBox();

        final var vueClasse = new VueClasse(model);
        vbox.getChildren().addAll(vueClasse);

        vbox.setOnDragOver(event -> {
            if (event.getGestureSource() != vbox && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }

            event.consume();
        });

        vbox.setOnDragDropped(event -> {
            final var dragBoard = event.getDragboard();
            var success = false;

            System.out.println("dragBoard.getString() = " + dragBoard.getString());

            if (dragBoard.hasString()) {
                final var itemContent = dragBoard.getString();
                success = true;

                final var entrySearch = this.model.getClassesPath().entrySet().stream().filter(entry -> entry.getKey().contains(itemContent)).findFirst();

                if (entrySearch.isPresent()) {
                    final var classeEntiere = new ClasseEntiere(entrySearch.get().getValue());
                    this.model.ajouterClasse(classeEntiere);

                    model.notifierObservateur();
                } else {
                    System.out.println("Content of classPath: " + this.model.getClassesPath());
                }


            }

            event.setDropCompleted(success);

            event.consume();
        });

        base.setCenter(vbox);
    }


    public void createTree(File file, TreeItem<String> parent) {
        if (file.isDirectory()) {
            final var treeItem = new CheckBoxTreeItem<>(file.getName());
            parent.getChildren().add(treeItem);

            Arrays.stream(Objects.requireNonNull(file.listFiles())).forEach(f -> createTree(f, treeItem));
        } else {

            final var name = FileExtension.isClassFile(file.getName()) ? file.getName().substring(0, file.getName().length() - 6) : file.getName();
            if (FileExtension.isJavaFile(file.getName())) {
                return;
            }

            final var checkBox = new CheckBoxTreeItem<>(name);

            parent.getChildren().add(checkBox);
        }
    }




    @Override
    public Scene getScene() {
        return new Scene(base, 800, 600);
    }
}


