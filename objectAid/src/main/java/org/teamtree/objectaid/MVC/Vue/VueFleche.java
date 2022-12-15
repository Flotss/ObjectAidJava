package org.teamtree.objectaid.MVC.Vue;

import javafx.scene.shape.Line;
import org.teamtree.objectaid.MVC.Model.Model;

public class VueFleche extends Line implements Observateur {

    private final Model model;

    public VueFleche(final Model mod) {
        model = mod;
    }

    @Override
    public void actualiser() {
        throw new Error("Not yet implemented");
    }
}
