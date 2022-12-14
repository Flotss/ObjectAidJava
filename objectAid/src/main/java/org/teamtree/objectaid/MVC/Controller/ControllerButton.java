package org.teamtree.objectaid.MVC.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.teamtree.objectaid.MVC.Model.Model;

public class ControllerButton implements EventHandler<ActionEvent> {

    private final Model model;

    public ControllerButton(Model model) {
        this.model = model;
    }


    @Override
    public void handle(ActionEvent event) {
        throw new Error("Not yet implemented");
    }
}