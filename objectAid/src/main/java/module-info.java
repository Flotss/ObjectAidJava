module org.teamtree.objectaid {
    requires javafx.controls;
    requires javafx.base;
    requires javafx.swing;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens org.teamtree.objectaid to javafx.fxml;
    exports org.teamtree.objectaid;
}