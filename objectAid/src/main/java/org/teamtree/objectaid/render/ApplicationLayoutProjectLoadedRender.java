package org.teamtree.objectaid.render;


import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.Fabrique.SceneFactory;
import org.teamtree.objectaid.MVC.Controller.*;
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
import org.teamtree.objectaid.MVC.Vue.VueListeClasse;
import org.teamtree.objectaid.Service.JavaProjectClassLoaderService;
import org.teamtree.objectaid.util.FileExtension;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

/**
 * Représente le rendu de l'application au stade post-chargement de projet.
 *
 * @see org.teamtree.objectaid.MVC.Model.ApplicationState
 */
public class ApplicationLayoutProjectLoadedRender implements SceneFactory {
    private final BorderPane base;

    public static MenuBar menubar;

    public ApplicationLayoutProjectLoadedRender(Model model) {
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
        final var listeClasse = new VueListeClasse("Liste des classes", model);
        MenuItem menuItem2 = new MenuItem("Constructeurs");
       
        final var controllerBtnGeneral = new ControllerButtonGeneral(model);
        
        menuItem2.setOnAction(controllerBtnGeneral);
        MenuItem menuItem3 = new MenuItem("Attributs");
        menuItem3.setOnAction(new ControllerButtonGeneral(model));
        MenuItem menuItem4 = new MenuItem("Methodes");
        menuItem4.setOnAction(controllerBtnGeneral);
        MenuItem menuItem5 = new MenuItem("Relations");
        menuItem5.setOnAction(controllerBtnGeneral);

        MenuItem menuItem6 = new MenuItem("set is get");
        menuItem6.setOnAction(controllerBtnGeneral);

        Menu supprimerClasseMenu = new Menu("Supprimer les classes");
        MenuItem supprimerClasseMenuitem = new MenuItem("Supprimer les classes");
        supprimerClasseMenuitem.setOnAction(new MenuItemController(model));

        supprimerClasseMenu.getItems().add(supprimerClasseMenuitem);

        menuItem.getItems().addAll(menuItem2, menuItem3, menuItem4, menuItem5, menuItem6);

        for (ClasseEntiere ce: model.getClasses()) {
            Menu nomClasseMenu = new Menu(ce.getClasseAffichage().getNom());
            MenuItem afficherCacherClasse = new MenuItem("Afficher/Cacher");
            afficherCacherClasse.setOnAction(new MenuItemController(model));
            MenuItem supprimerClasse = new MenuItem("Supprimer");
            supprimerClasse.setOnAction(new ControllerButtonGeneral(model));
            nomClasseMenu.getItems().addAll(afficherCacherClasse, supprimerClasse);
            listeClasse.getItems().add(nomClasseMenu);
        }

        // Ajouter une classe
        MenuItem ajouterClasse = new MenuItem("Ajouter une classe");
        ajouterClasse.setId("ajouterClasse");
        ajouterClasse.setOnAction(new MenuItemController(model));
        listeClasse.getItems().add(ajouterClasse);

        Menu menuGeneration = new Menu("Générer");
        MenuItem itemGenererUml = new MenuItem("Générer UML");
        itemGenererUml.setOnAction(new GenererButtonController(model));
        MenuItem itemGenererSquelette = new MenuItem("Générer squelette");
        itemGenererSquelette.setOnAction(new GenererButtonController(model));
        menuGeneration.getItems().addAll(itemGenererUml, itemGenererSquelette);


        menubar.getMenus().addAll(menuItem, listeClasse, supprimerClasseMenu, menuGeneration);

        vbox.getChildren().addAll(menubar, vueClasse);

        VueContextMenuClasse contextMenu = new VueContextMenuClasse(model);
        model.ajouterObservateur(contextMenu);
        model.ajouterObservateur(listeClasse);

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


