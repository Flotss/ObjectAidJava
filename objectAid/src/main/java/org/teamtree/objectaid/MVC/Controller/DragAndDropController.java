package org.teamtree.objectaid.MVC.Controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import org.teamtree.objectaid.Classe.ClasseAffichage;
import org.teamtree.objectaid.MVC.Model.Model;


public class DragAndDropController implements EventHandler<MouseEvent> {

    private Model model;

    public DragAndDropController(Model model) {
        this.model = model;
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.isPrimaryButtonDown()){
            ClasseAffichage classe = (ClasseAffichage) event.getSource();
            model.setCurrentClickedClass(classe.getNom());
            model.getClasse(model.getCurrentClickedClass()).get().setX((int) event.getX());
            model.getClasse(model.getCurrentClickedClass()).get().setY((int) event.getY());
            model.notifierObservateur();
        }
    }

}
