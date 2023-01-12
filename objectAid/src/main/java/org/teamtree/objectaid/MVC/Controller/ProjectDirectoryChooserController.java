package org.teamtree.objectaid.MVC.Controller;

import org.teamtree.objectaid.MVC.Model.Model;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;

import javafx.event.ActionEvent;
import javafx.stage.DirectoryChooser;

/**
 * Contrôleur chargé de gérer l'événement de sélection d'un répertoire de projet.
 * Lors de l'état initial de l'application (BOOTSTRAP), l'utilisateur doit
 * sélectionner un répertoire de projet. Ce contrôleur est chargé de diriger cet
 * événement.
 */
public class ProjectDirectoryChooserController extends ControllerBase<ActionEvent> {

    private static final Logger LOGGER = System.getLogger(
        ProjectDirectoryChooserController.class.getName());

    public ProjectDirectoryChooserController(final Model model) {
        super(model);
    }

    /**
     * Invoked when a specific event of the type for which this handler is
     * registered happens.
     *
     * @param event the event which occurred
     */
    @Override
    public void handle(final ActionEvent event) {
        final var fileChooser = new DirectoryChooser();
        final var file = fileChooser.showDialog(null);

        if (file == null) {
            LOGGER.log(Level.ERROR, "Aucun répertoire n'a été sélectionné !");
            return;
        }

        model.setCurrentProject(file.toPath());

        LOGGER.log(Level.INFO, "Current project set to " + model.getCurrentProject());
    }
}
