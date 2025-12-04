module zorkrip {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;
    requires javafx.graphics;
    requires com.google.gson;
    requires javafx.base;

    exports org.zorkrip.model;
    exports org.zorkrip.engine;
    exports org.zorkrip.persistence;

    // Allow FXML to access controller classes
    opens org.zorkrip.ui.fx to javafx.fxml;

    // Allow JavaFX runtime to construct your Application subclass
    exports org.zorkrip.ui.fx;
}
