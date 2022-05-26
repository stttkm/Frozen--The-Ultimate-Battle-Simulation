module com.example.frozentheultimatebattlesimulation {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.frozentheultimatebattlesimulation to javafx.fxml;
    exports com.example.frozentheultimatebattlesimulation;
}