module org.henrique {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fazecast.jSerialComm;

    opens org.henrique to javafx.fxml;
    exports org.henrique;
}