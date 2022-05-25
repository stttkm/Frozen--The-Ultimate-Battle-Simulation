module com.example.frozentheultimatebattlesimulation {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens com.example.frozentheultimatebattlesimulation to javafx.fxml;
    exports com.example.frozentheultimatebattlesimulation;
}