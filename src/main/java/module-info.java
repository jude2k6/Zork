module org.example.zorkgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.zorkgame to javafx.fxml;
    exports org.example.zorkgame;
}