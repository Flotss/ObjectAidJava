package org.teamtree.objectaid.render;


import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.Fabrique.SceneFactory;
import org.teamtree.objectaid.MVC.Controller.ControllerButtonGeneral;
import org.teamtree.objectaid.MVC.Controller.MenuItemController;
import org.teamtree.objectaid.MVC.Controller.TreeViewDragDetectedController;
import org.teamtree.objectaid.MVC.Controller.VboxDragDroppedController;
import org.teamtree.objectaid.MVC.Controller.VboxDragOverController;
import org.teamtree.objectaid.MVC.Model.Model;

import javafx.scene.Scene;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import org.teamtree.objectaid.MVC.Vue.VueClasse;
import org.teamtree.objectaid.MVC.Vue.VueContextMenuClasse;
import org.teamtree.objectaid.Service.JavaProjectClassLoaderService;
import org.teamtree.objectaid.util.FileExtension;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public class ApplicationLayoutProjectLoadedRender implements SceneFactory {
    private final Model model;
    private final BorderPane base;

    public static MenuBar menubar;

    public ApplicationLayoutProjectLoadedRender(Model model) {
        this.model = model;
        this.base = new BorderPane();

        final var scrollPane = new ScrollPane();

        TreeItem<String> root = new TreeItem<>();

        final var classLoader = new JavaProjectClassLoaderService(model.getCurrentProject(), model);
        classLoader.loadClasses(model.getCurrentProject().toFile());

        Arrays.stream(Objects.requireNonNull(model.getCurrentProject().toFile().listFiles())).forEach(file -> createTree(file, root));


        final var treeView = new TreeView<>(root);

        treeView.setOnDragDetected(new TreeViewDragDetectedController(model, treeView));

        treeView.setShowRoot(false);
        scrollPane.setContent(treeView);

        scrollPane.setFitToHeight(true);

        base.setLeft(scrollPane);

        final var vbox = new VBox();

        final var vueClasse = new VueClasse(model);

        vbox.setOnDragOver(new VboxDragOverController(model, vbox));

        vbox.setOnDragDropped(new VboxDragDroppedController(model));

        ApplicationLayoutProjectLoadedRender.menubar = new MenuBar();

        Menu menuItem = new Menu("Afficher/cacher");
        Menu listeClasse = new Menu("Liste des classes");
        MenuItem afficher = new MenuItem("Afficher/Cacher");
        MenuItem supprimer = new MenuItem("Supprimer");
        MenuItem menuItem2 = new MenuItem("Constructeurs");
       
        final var controllerBtnGeneral = new ControllerButtonGeneral(model);
        
        menuItem2.setOnAction(controllerBtnGeneral);
        MenuItem menuItem3 = new MenuItem("Attributs");
        menuItem3.setOnAction(new ControllerButtonGeneral(model));
        MenuItem menuItem4 = new MenuItem("Methodes");
        menuItem4.setOnAction(controllerBtnGeneral);
        MenuItem menuItem5 = new MenuItem("Relations");
        menuItem5.setOnAction(controllerBtnGeneral);

        Menu menuItem1 = new Menu("Supprimer");
        menuItem.getItems().addAll(menuItem2, menuItem3, menuItem4, menuItem5);

        for (ClasseEntiere ce: model.getClasses()) {
            Menu nomClasseMenu = new Menu(ce.getClasseAffichage().getNom());
            MenuItem afficherCacherClasse = new MenuItem("Afficher/Cacher");
            afficherCacherClasse.setOnAction(new MenuItemController(model));
            MenuItem supprimerClasse = new MenuItem("Supprimer");
            supprimerClasse.setOnAction(new ControllerButtonGeneral(model));
            nomClasseMenu.getItems().addAll(afficherCacherClasse, supprimerClasse);
            listeClasse.getItems().add(nomClasseMenu);
        }

        menubar.getMenus().addAll(menuItem, listeClasse);

        vbox.getChildren().addAll(menubar, vueClasse);

        VueContextMenuClasse contextMenu = new VueContextMenuClasse(model);
        model.ajouterObservateur(contextMenu);

        base.setCenter(vbox);
    }


    /**
     * Charge le TreeView récursivement
     * @param file le fichier à charger
     * @param parent le parent du fichier
     */
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


