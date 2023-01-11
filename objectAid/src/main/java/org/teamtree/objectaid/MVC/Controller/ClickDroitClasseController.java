package org.teamtree.objectaid.MVC.Controller;

import javafx.event.EventHandler;
import javafx.scene.input.ContextMenuEvent;
import org.teamtree.objectaid.MVC.Model.Model;
import org.teamtree.objectaid.MVC.Vue.VueContextMenuClasse;
import org.teamtree.objectaid.MVC.Vue.VueClasseAffichage;

/**
 * Classe qui permet de gérer le clic droit sur une classe
 */
public class ClickDroitClasseController implements EventHandler<ContextMenuEvent> {

    /** Modèle */
    private final Model model;

    /**
     * ClasseAffichage qui est concernée par le clic droit
     */
    private final VueClasseAffichage classe;

    /**
     * Constructeur
     * @param model Modèle
     * @param classe Classe concernée par le clic droit
     */
    public ClickDroitClasseController(Model model, VueClasseAffichage classe) {
        this.model = model;
        this.classe = classe;
    }

    /**
     * Méthode qui permet de gérer le clic droit sur une classe
     * @param event ContextMenuEvent
     */
    @Override
    public void handle(ContextMenuEvent event) {
        if (model.getCurrentClickedClass() == null || !model.getCurrentClickedClass().getNom().equals(classe.getNom())) model.setCurrentClickedClass(classe);
        new VueContextMenuClasse(model,classe).show(classe,event.getScreenX(),event.getScreenY());
    }
}
