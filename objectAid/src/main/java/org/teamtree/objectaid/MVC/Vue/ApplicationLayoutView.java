package org.teamtree.objectaid.MVC.Vue;

import org.teamtree.objectaid.MVC.Model.Model;

public class ApplicationLayoutView implements Observateur {

    private final Model model;

    public ApplicationLayoutView(Model model) {
        this.model = model;
    }


    /**
     * Méthode qui permet de mettre à jour l'observateur
     */
    @Override
    public void actualiser() {
        switch (model.getApplicationState()) {
            case BOOTSTRAP -> {
                break;
            }
            case PROJECT_LOADED -> {
                break;
            }

            default -> throw new IllegalStateException("Unexpected value: " + model.getApplicationState());
        }
    }
}
