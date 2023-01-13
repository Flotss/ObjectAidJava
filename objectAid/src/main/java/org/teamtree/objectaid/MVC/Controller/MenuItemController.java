package org.teamtree.objectaid.MVC.Controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.teamtree.objectaid.Accessibilite.*;
import org.teamtree.objectaid.Classe.ClasseEntiere;
import org.teamtree.objectaid.Etat.Abstract;
import org.teamtree.objectaid.Etat.Etat;
import org.teamtree.objectaid.Etat.Final;
import org.teamtree.objectaid.Etat.Static;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.MVC.Vue.VueClasse;
import org.teamtree.objectaid.MVC.Vue.VueListeClasse;
import org.teamtree.objectaid.render.ApplicationLayoutProjectLoadedRender;
import org.teamtree.objectaid.MVC.Vue.VueClasseAffichage;
import org.teamtree.objectaid.Service.SqueletteService;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Classe qui permet de gérer tous les MenuItem de l'application
 */
public class MenuItemController implements EventHandler<ActionEvent> {

    /**
     * Modèle
     */
    private final Model model;

    /**
     * Constructeur
     *
     * @param model Modèle
     */
    public MenuItemController(Model model) {
        this.model = model;
    }

    /**
     * Méthode qui permet de gérer les MenuItem de l'application
     *
     * @param event Evènement
     */
    @Override
    public void handle(ActionEvent event) {
        switch (((MenuItem) event.getSource()).getText()) {
            case "Attributs" -> model.afficherAttributsSelection();
            case "Méthodes" -> model.afficherMethodesSelection();
            case "Constructeurs" -> model.afficherConstructeursSelection();
            case "Cacher" -> {
                model.ajouterClasseCachee(model.getCurrentClickedClass());
            }
            case "Afficher/Cacher" -> {
                VueClasseAffichage classe = model.getClasse(((MenuItem) event.getSource()).getParentMenu().getText()).get().getClasseAffichage();
                if (!model.getHiddenClasses().contains(classe)) {
                    model.setCurrentClickedClass(classe);
                    model.ajouterClasseCachee(classe);
                } else {
                    model.supprimerClasseCachee(classe);
                }
            }
            case "Supprimer" -> {
                VueClasseAffichage classe1 = model.getCurrentClickedClass();
                model.supprimerClasseAffichage(classe1);

            }
            case "Générer le squelette" -> {
                ClasseEntiere classeE = model.getCurrentClickedClass().getClasseEntiere();
                SqueletteService squeletteService = new SqueletteService();
                squeletteService.genererSqueletteUniqueClasse(classeE);
            }
            case "Cacher interface" -> model.afficherInterfaceHeritageSelection("Implementation");
            case "Cacher heritage" -> model.afficherInterfaceHeritageSelection("Heritage");
            case "Supprimer les classes" -> {
                if (model.getClasses().size() > 0) {
                    model.supprimerClassesAffichage();
                    ApplicationLayoutProjectLoadedRender.menubar.getMenus().get(1).getItems().clear();
                }
            }
            case "Exporter en PNG" -> {
                final var vueClasseOptional = model.tryGetObservateur(VueClasse.class);

                if (vueClasseOptional.isEmpty()) {
                    throw new IllegalArgumentException("VueClasse is not present in the model");
                }

                final var vueClasse = (VueClasse) vueClasseOptional.get();

                if (vueClasse.getClasses().isEmpty()) {
                    final var alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText("Aucune classe n'est présente");

                    alert.showAndWait();
                    return;
                }

                final var image = new WritableImage((int) vueClasse.getWidth(), (int) vueClasse.getHeight());
                final var writableImage = vueClasse.snapshot(null, image);
                final var bufferedImage = SwingFXUtils.fromFXImage(writableImage, null);
                final var directoryChooser = new DirectoryChooser();
                final var selectedDirectory = directoryChooser.showDialog(new Stage());

                bufferedImage.flush();

                // Exporte l'image dans le dossier sélectionné
                if (selectedDirectory != null) {
                    final var dateNow = LocalDateTime.now();
                    final var outputFile = new File(selectedDirectory.getAbsolutePath() + "/export_" + dateNow.getYear() + "_" + dateNow.getMonthValue() + "_" + dateNow.getDayOfMonth() + "_" + dateNow.getHour() + "_" + dateNow.getMinute() + "_" + dateNow.getSecond() + ".png");

                    try {
                        ImageIO.write(bufferedImage, "png", outputFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (((MenuItem) event.getSource()).getId() != null) {
            if ("ajouterMethode".equals(((MenuItem) event.getSource()).getId())) {
                ajouterMethode();
            }
        }
    }


    /**
     * Méthode qui permet de gérer la modification du classe
     */
    private void ajouterMethode() {
        Stage stage = new Stage();
        stage.setTitle("Ajouter une methode");

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10));

        ChoiceBox<Accessibilite> accessibiliteChoiceBox = new ChoiceBox<>();
        accessibiliteChoiceBox.getItems().addAll(new Public(), new Private(), new Default(), new Protected());
        accessibiliteChoiceBox.setValue(new Public());

        ChoiceBox<Etat> staticChoiceBox = new ChoiceBox<>();
        staticChoiceBox.getItems().addAll(new Static(), null);
        staticChoiceBox.setValue(null);

        ChoiceBox<Etat> finalChoiceBox = new ChoiceBox<>();
        finalChoiceBox.getItems().addAll(new Final(), null);
        finalChoiceBox.setValue(null);

        ChoiceBox<Etat> abstractChoiceBox = new ChoiceBox<>();
        abstractChoiceBox.getItems().addAll(new Abstract(), null);
        abstractChoiceBox.setValue(null);

        TextField nomTextField = new TextField();
        nomTextField.setPromptText("Nom de la methode");

        TextField typeTextField = new TextField();
        typeTextField.setPromptText("Type de retour");

        TextField parametresTextField = new TextField();
        parametresTextField.setPromptText("Parametres ex  nom : String");

        Button ajouterButton = new Button("Ajouter");
        ajouterButton.setOnAction(event -> {
            ArrayList<Etat> etats = new ArrayList<>();
            if (staticChoiceBox.getValue() != null) {
                etats.add(staticChoiceBox.getValue());
            }
            if (finalChoiceBox.getValue() != null) {
                etats.add(finalChoiceBox.getValue());
            }
            if (abstractChoiceBox.getValue() != null) {
                etats.add(abstractChoiceBox.getValue());
            }


            model.ajouterMethode(accessibiliteChoiceBox.getValue(), etats, nomTextField.getText(), typeTextField.getText(), parametresTextField.getText());
            stage.close();
        });

        hBox.getChildren().addAll(accessibiliteChoiceBox, staticChoiceBox, abstractChoiceBox, finalChoiceBox, typeTextField, nomTextField, parametresTextField, ajouterButton);
        Scene scene = new Scene(hBox);

        stage.setScene(scene);
        stage.show();
    }
}
