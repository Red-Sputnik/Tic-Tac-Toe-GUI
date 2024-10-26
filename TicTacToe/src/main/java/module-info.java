module org.example.game {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.game to javafx.fxml;
    exports org.example.game;
}