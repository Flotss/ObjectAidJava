package org.teamtree.objectaid.MVC.Vue;

import javafx.scene.control.TreeView;
import org.teamtree.objectaid.MVC.Model.Model;

public class VueArborescente extends TreeView implements Observateur {

    private final Model model;

    public VueArborescente(final Model mod) {
        model = mod;
    }

    @Override
    public void actualiser() {
        throw new Error("Not yet implemented");
    }
}
