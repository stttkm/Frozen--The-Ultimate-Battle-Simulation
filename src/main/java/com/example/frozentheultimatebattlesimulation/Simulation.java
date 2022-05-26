package com.example.frozentheultimatebattlesimulation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.lang.reflect.Array;
import java.util.Arrays;

import static javafx.scene.layout.HBox.setMargin;

// klasa służy głównie przechowywaniu elementów wizualnych
public class Simulation {
    static HBox simulationBody;
    static public void pseudoMain(ActionEvent event) {
        Main.turns.add(new Turn()); //dodajemy pierwszą turę
        ((Turn)Main.turns.get(0)).generateCharacters(); //generujemy postacie
        for(int i =0; i< 400; i++){ //potem zamienimy na while'a, by akcja toczyła się do końca gry
            try {
                Main.turns.add(new Turn((Turn)Main.turns.get(i))); // tworzymy nową turę
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }

            ((Turn)Main.turns.get(Main.turns.size()-1)).anna.act();
            if(((Turn)Main.turns.get(Main.turns.size()-1)).kristoff != null) {((Turn) Main.turns.get(Main.turns.size() - 1)).kristoff.act();}
            ((Turn)Main.turns.get(Main.turns.size()-1)).hans.act();

            ((Turn)Main.turns.get(Main.turns.size()-1)).iceBreakers.forEach((og) -> {
                ((IceBreaker)og).act();
            });



        }
        // jak chcesz kazać postaciom coś robić, to właśnie tutaj jest odpowiednie miejsce.

         simulationBody = simulationRoot();
        Simulation.loadMap(simulationBody, (Turn) Main.turns.get(0)); // dodaliśmy mapę z polami
        Scene simulationScene = new Scene(simulationBody);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(simulationScene);
        stage.setFullScreen(true);
        stage.show();
    }

    //służy dodaniu do sceny niezmiennych elementów
    static public HBox simulationRoot(){
    HBox  body = new HBox();
    body.setAlignment(Pos.CENTER);

    GridPane mapGrid = new GridPane();
    mapGrid.setAlignment(Pos.CENTER);

    for(int i =0; i< Main.mapSize; i++) for(int j = 0; j < Main.mapSize;j++) mapGrid.add(new StackPane(),  j, i);

    VBox rightMenu = new VBox();
    Image logo  = new Image("file:src/main/resources/com/example/frozentheultimatebattlesimulation/img/Frozen.png");
    ImageView logoView = new ImageView(logo);

    HBox turnChanger = new HBox();
        turnChanger.setAlignment(Pos.CENTER);
    Text setTurnText = new Text("Turn   ");
    setTurnText.setStyle("-fx-font-size: 30; ");
    Spinner<Integer> turnSpinner = new Spinner<>(0, Main.turns.size(), 0);
    turnSpinner.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
    Text turnDurationText = new Text("       Turn Duration[ms]   ");
    turnDurationText.setStyle("-fx-font-size: 30; ");
    Spinner<Integer> durationSpinner = new Spinner<>(100, 10000, 500, 100);
    Button autoplay = new Button("Autoplay ▶");

    autoplay.setOnAction((e) -> {

                final IntegerProperty i = new SimpleIntegerProperty(0);
                Timeline timeline = new Timeline(
                        new KeyFrame(
                                Duration.millis(durationSpinner.getValue()),
                                inventini -> {
                                    i.set(i.get() + 1);
                                    turnSpinner.increment();
                                }
                        )
                );
                timeline.setCycleCount(Main.turns.size()-turnSpinner.getValue()-1);
                timeline.play();
            });
    turnChanger.getChildren().addAll(setTurnText,turnSpinner, turnDurationText,durationSpinner, autoplay );

    // event handler dla spinnerka
        turnSpinner.valueProperty().addListener((obs, oldValue, newValue) ->
        {
                    if (newValue == Main.turns.size()){
                        ; //napisy końcowe z Taylor Swift <3 lub Poot Lovato :(
                    } else {
                        loadMap(simulationBody, (Turn) Main.turns.get(newValue));
                    }
        });






        rightMenu.getChildren().addAll(logoView, turnChanger);



    body.getChildren().addAll(mapGrid, rightMenu);
    setMargin(rightMenu, new Insets(0,0,0,30));
    return body;
    }

    static public void loadMap(HBox simulationRoot, Turn turn){
        for(int i =0; i< Main.mapSize; i++) for(int j = 0; j < Main.mapSize;j++){
            ((StackPane)((GridPane)simulationRoot.getChildren().get(0))
                    .getChildren().get(i*Main.mapSize+j))
                    .getChildren().add(new ImageView(Field.fieldImages[Arrays.asList(Field.fieldTypes).indexOf(turn.map[i][j].type)]));

        };
        for(int i = 0; i < turn.iceBreakers.size(); i++){ // ładujemy łamaczy lodu
            ((StackPane)((GridPane)simulationRoot.getChildren().get(0))
                    .getChildren().get(((IceBreaker)turn.iceBreakers.get(i)).y*Main.mapSize+((IceBreaker)turn.iceBreakers.get(i)).x))
                    .getChildren().add(new ImageView(((IceBreaker) turn.iceBreakers.get(i)).characterImage));
        }

        for(int i = 0; i < turn.wolves.size(); i++){ // ładujemy wilki
            ((StackPane)((GridPane)simulationRoot.getChildren().get(0))
                    .getChildren().get(((Wolf)turn.wolves.get(i)).y*Main.mapSize+((Wolf)turn.wolves.get(i)).x))
                    .getChildren().add(new ImageView(((Wolf) turn.wolves.get(i)).characterImage));
        }

        for(int i = 0; i < turn.snowmen.size(); i++){ // ładujemy armię Elsy
            ((StackPane)((GridPane)simulationRoot.getChildren().get(0))
                    .getChildren().get(((Snowman)turn.snowmen.get(i)).y*Main.mapSize+((Snowman)turn.snowmen.get(i)).x))
                    .getChildren().add(new ImageView(((Snowman) turn.snowmen.get(i)).characterImage));
        }

        for(int i = 0; i < turn.soldiers.size(); i++){ // ładujemy armię Hansa
            ((StackPane)((GridPane)simulationRoot.getChildren().get(0))
                    .getChildren().get(((Soldier)turn.soldiers.get(i)).y*Main.mapSize+((Soldier)turn.soldiers.get(i)).x))
                    .getChildren().add(new ImageView(((Soldier) turn.soldiers.get(i)).characterImage));
        }

        ((StackPane)((GridPane)simulationRoot.getChildren().get(0))
                .getChildren().get(turn.elsa.y*Main.mapSize+turn.elsa.x))
                .getChildren().add(new ImageView(turn.elsa.characterImage));

        ((StackPane)((GridPane)simulationRoot.getChildren().get(0))
                .getChildren().get(turn.anna.y*Main.mapSize+turn.anna.x))
                .getChildren().add(new ImageView(turn.anna.characterImage));

        ((StackPane)((GridPane)simulationRoot.getChildren().get(0))
                .getChildren().get(turn.kristoff.y*Main.mapSize+turn.kristoff.x))
                .getChildren().add(new ImageView(turn.kristoff.characterImage));

        ((StackPane)((GridPane)simulationRoot.getChildren().get(0))
                .getChildren().get(turn.hans.y*Main.mapSize+turn.hans.x))
                .getChildren().add(new ImageView(turn.hans.characterImage));

    }




    };
