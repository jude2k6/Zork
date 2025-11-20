module zorkrip {
    requires javafx.controls;
    requires javafx.fxml;

    exports org.zorkrip.model;
    exports org.zorkrip.engine;
    exports org.zorkrip.persistence;

    opens org.zorkrip.ui.fx.controller to javafx.fxml;
}
