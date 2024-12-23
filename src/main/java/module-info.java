module org.example.uap_wulan {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.uap_wulan to javafx.fxml;
    exports org.example.uap_wulan;
}