module org.teamtree.objectaid {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens org.teamtree.objectaid to javafx.fxml;
    exports org.teamtree.objectaid;
    exports org.teamtree.objectaid.MVC.Fleches;
    opens org.teamtree.objectaid.MVC.Fleches to javafx.fxml;
}