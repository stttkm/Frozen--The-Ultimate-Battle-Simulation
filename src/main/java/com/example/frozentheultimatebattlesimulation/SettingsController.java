package com.example.frozentheultimatebattlesimulation;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    @FXML
    private Slider mapSizeSlider;
    @FXML
    private Spinner<Integer> elsasArmySizeSpinner;
    @FXML
    private Spinner<Integer> hansArmySizeSpinner;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // dodajemy spinerki osobno dla Elsy i Hansa
        SpinnerValueFactory<Integer> elsaValueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(Main.mapSize, (int) (Main.mapSize*Main.mapSize*0.3));

        elsaValueFactory.setValue(Main.mapSize*Main.mapSize/4);

        SpinnerValueFactory<Integer> hansValueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(Main.mapSize, (int) (Main.mapSize*Main.mapSize*0.3));

        hansValueFactory.setValue(Main.mapSize*Main.mapSize/4);

        elsasArmySizeSpinner.setValueFactory(elsaValueFactory);
        hansArmySizeSpinner.setValueFactory(hansValueFactory);

        // oto listnery
        mapSizeSlider.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2){
                    Main.mapSize = (int) mapSizeSlider.getValue();

                SpinnerValueFactory<Integer> elsaValueFactory =
                        new SpinnerValueFactory.IntegerSpinnerValueFactory(Main.mapSize, (int) (Main.mapSize*Main.mapSize*0.3));

                elsaValueFactory.setValue(Main.mapSize*Main.mapSize/4);

                SpinnerValueFactory<Integer> hansValueFactory =
                        new SpinnerValueFactory.IntegerSpinnerValueFactory(Main.mapSize, (int) (Main.mapSize*Main.mapSize*0.3));

                hansValueFactory.setValue(Main.mapSize*Main.mapSize/4);

                elsasArmySizeSpinner.setValueFactory(elsaValueFactory);
                hansArmySizeSpinner.setValueFactory(hansValueFactory);

            }});

        elsasArmySizeSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                Main.elsasArmySize = elsasArmySizeSpinner.getValue();
            }
        });

        hansArmySizeSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                Main.hansArmySize = elsasArmySizeSpinner.getValue();
            }
        });





    }
    // Let it go to nasz guzik do rozpoczęcia animacji. Od teraz wszystko przenosi się do Simulation
    public void letItGo(ActionEvent event) throws IOException
    {
        Simulation.pseudoMain(event);
    }
}