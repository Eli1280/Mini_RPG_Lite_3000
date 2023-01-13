module com.isep.jfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens algo.jeu to javafx.fxml;
    exports algo.jeu;
}