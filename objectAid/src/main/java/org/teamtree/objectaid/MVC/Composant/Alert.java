package org.teamtree.objectaid.MVC.Composant;

/**
 * Service qui s'occupe de créer une alerte prédéfinie et d'en afficher le contenu.
 */
public class Alert {
    private Alert() {
        throw new IllegalStateException("Utility class");
    }

    public static void afficheAlert(String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
