package org.teamtree.objectaid.Service;

/**
 * Service qui s'occupe de créer une alerte prédéfinie et d'en afficher le contenu.
 */
public class Alert {

    public static void afficheAlert(String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
            javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
