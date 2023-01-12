package org.teamtree.objectaid.MVC.Composant;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.teamtree.objectaid.Accessibilite.*;
import org.teamtree.objectaid.Entite.Classe;
import org.teamtree.objectaid.Entite.Entite;
import org.teamtree.objectaid.Entite.Enum;
import org.teamtree.objectaid.Entite.Interface;
import org.teamtree.objectaid.Entite.Record;
import org.teamtree.objectaid.Etat.Abstract;
import org.teamtree.objectaid.Etat.Etat;
import org.teamtree.objectaid.Etat.Final;
import org.teamtree.objectaid.Etat.Static;
import org.teamtree.objectaid.MVC.Model.Model;

import java.util.ArrayList;

public class ComponentAddClass {

    private Model model;

    public ComponentAddClass(Model model) {
        this.model = model;
    }

    /**
     * Méthode qui permet d'ajouter une méthode à une classe
     */
    public void ajouterMethode() {
        Stage stage = new Stage();
        stage.setTitle("Ajouter une methode");

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10));

        ChoiceBox<Accessibilite> accessibiliteChoiceBox = new ChoiceBox<>();
        accessibiliteChoiceBox.getItems().addAll(new Public(), new Private(), new Default(), new Protected());
        accessibiliteChoiceBox.setValue(new Public());

        ChoiceBox<Etat> staticChoiceBox = new ChoiceBox<>();
        staticChoiceBox.getItems().addAll( new Static(),  null);
        staticChoiceBox.setValue(null);

        ChoiceBox<Etat> finalChoiceBox = new ChoiceBox<>();
        finalChoiceBox.getItems().addAll( new Final(),  null);
        finalChoiceBox.setValue(null);

        ChoiceBox<Etat> abstractChoiceBox = new ChoiceBox<>();
        abstractChoiceBox.getItems().addAll( new Abstract(),  null);
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


    public void ajouterAttribut(){
        Stage stage = new Stage();
        stage.setTitle("Ajouter un attribut");

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10));

        ChoiceBox<Accessibilite> accessibiliteChoiceBox = new ChoiceBox<>();
        accessibiliteChoiceBox.getItems().addAll(new Public(), new Private(), new Default(), new Protected());
        accessibiliteChoiceBox.setValue(new Public());

        ChoiceBox<Etat> staticChoiceBox = new ChoiceBox<>();
        staticChoiceBox.getItems().addAll( new Static(),  null);
        staticChoiceBox.setValue(null);

        ChoiceBox<Etat> finalChoiceBox = new ChoiceBox<>();
        finalChoiceBox.getItems().addAll( new Final(),  null);
        finalChoiceBox.setValue(null);

        TextField nomTextField = new TextField();
        nomTextField.setPromptText("Nom de l'attribut");

        TextField typeTextField = new TextField();
        typeTextField.setPromptText("Type de l'attribut");

        Button ajouterButton = new Button("Ajouter");
        ajouterButton.setOnAction(event -> {
            ArrayList<Etat> etats = new ArrayList<>();
            if (staticChoiceBox.getValue() != null) {
                etats.add(staticChoiceBox.getValue());
            }
            if (finalChoiceBox.getValue() != null) {
                etats.add(finalChoiceBox.getValue());
            }

            model.ajouterAttribut(accessibiliteChoiceBox.getValue(), etats, nomTextField.getText(), typeTextField.getText());
            stage.close();
        });

        hBox.getChildren().addAll(accessibiliteChoiceBox, staticChoiceBox, finalChoiceBox, typeTextField, nomTextField, ajouterButton);
        Scene scene = new Scene(hBox);

        stage.setScene(scene);
        stage.show();
    }

    public void ajouterContructeur(){
        Stage stage = new Stage();
        stage.setTitle("Ajouter un constructeur");

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10));

        ChoiceBox<Accessibilite> accessibiliteChoiceBox = new ChoiceBox<>();
        accessibiliteChoiceBox.getItems().addAll(new Public(), new Private(), new Default(), new Protected());
        accessibiliteChoiceBox.setValue(new Public());

        TextField nomTextField = new TextField();
        nomTextField.setPromptText("Nom du constructeur");

        TextField parametresTextField = new TextField();
        parametresTextField.setPromptText("Parametres ex  nom : String");

        Button ajouterButton = new Button("Ajouter");
        ajouterButton.setOnAction(event -> {
            model.ajouterConstructeur(accessibiliteChoiceBox.getValue(), nomTextField.getText(), parametresTextField.getText());
            stage.close();
        });

        hBox.getChildren().addAll(accessibiliteChoiceBox, nomTextField, parametresTextField, ajouterButton);
        Scene scene = new Scene(hBox);

        stage.setScene(scene);
        stage.show();
    }


    public void ajouterClasse(){
        Stage stage = new Stage();
        stage.setTitle("Ajouter une classe");

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10));

        ChoiceBox<Accessibilite> accessibiliteChoiceBox = new ChoiceBox<>();
        accessibiliteChoiceBox.getItems().addAll(new Public(), new Private(), new Default(), new Protected());
        accessibiliteChoiceBox.setValue(new Public());

        ChoiceBox<Entite> entiteChoiceBox = new ChoiceBox<>();
        entiteChoiceBox.getItems().addAll(new Classe(), new Interface(), new Enum(), new Record());
        entiteChoiceBox.setValue(new Classe());

        ChoiceBox<Etat> staticChoiceBox = new ChoiceBox<>();
        staticChoiceBox.getItems().addAll( new Static(),  null);
        staticChoiceBox.setValue(null);

        ChoiceBox<Etat> finalChoiceBox = new ChoiceBox<>();
        finalChoiceBox.getItems().addAll( new Final(),  null);
        finalChoiceBox.setValue(null);

        ChoiceBox<Etat> abstractChoiceBox = new ChoiceBox<>();
        abstractChoiceBox.getItems().addAll( new Abstract(),  null);
        abstractChoiceBox.setValue(null);

        TextField nomTextField = new TextField();
        nomTextField.setPromptText("Nom de la classe");

        TextField nomParentTextField = new TextField();
        nomParentTextField.setPromptText("Nom de la classe parent");

        TextField interfacesTextField = new TextField();
        interfacesTextField.setPromptText("Nom des interfaces ex : Interface1, Interface2");

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

            model.ajouterClasse(nomTextField.getText(), nomParentTextField.getText(), interfacesTextField.getText(), accessibiliteChoiceBox.getValue(), etats, entiteChoiceBox.getValue());
            stage.close();
        });

        hBox.getChildren().addAll(entiteChoiceBox, accessibiliteChoiceBox, staticChoiceBox, abstractChoiceBox, finalChoiceBox, nomTextField, nomParentTextField, interfacesTextField, ajouterButton);
        Scene scene = new Scene(hBox);

        stage.setScene(scene);
        stage.show();
    }



}
