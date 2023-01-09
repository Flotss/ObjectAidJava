package org.teamtree.objectaid.MVC.Controller;

import javafx.event.EventHandler;
import javafx.scene.input.ContextMenuEvent;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.MVC.Vue.VueButtonBarClasse;
import org.teamtree.objectaid.MVC.Vue.VueClasseAffichage;

public class ClickDroitController implements EventHandler<ContextMenuEvent> {

    private Model model;
    private VueClasseAffichage classe;
    public ClickDroitController(Model model,VueClasseAffichage classe) {
        this.model = model;
        this.classe = classe;
    }

    @Override
    public void handle(ContextMenuEvent event) {
        if (model.getCurrentClickedClass() == null || !model.getCurrentClickedClass().getNom().equals(classe.getNom())) model.setCurrentClickedClass(classe);
        new VueButtonBarClasse(model,classe).show(classe,event.getScreenX(),event.getScreenY());
    }
}
