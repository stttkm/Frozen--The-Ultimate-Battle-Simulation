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
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.Objects;

import static com.example.frozentheultimatebattlesimulation.Main.mapSize;
import static com.example.frozentheultimatebattlesimulation.Main.turns;
import static javafx.scene.layout.HBox.setMargin;

/**
 * Obsługa graficzna i koncepcyjna symulacji
 */
public class Simulation {
    static String endCredits;
    static HBox simulationBody;
    static boolean showIds = false;
    static public void pseudoMain(ActionEvent event) {
        Main.turns.add(new Turn()); //dodajemy pierwszą turę
        ((Turn)Main.turns.get(0)).generateCharacters(); //generujemy postacie
        int lastTurnIndex=0;
        while(!(((Turn) turns.get(turns.size() - 1)).isGameOver)){ //potem zamienimy na while'a, by akcja toczyła się do końca gry
            try {
                Main.turns.add(new Turn((Turn)Main.turns.get(lastTurnIndex))); // tworzymy nową turę
                lastTurnIndex++;
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }

            if(((Turn)Main.turns.get(Main.turns.size()-1)).anna != null) ((Turn)Main.turns.get(Main.turns.size()-1)).anna.act();
            if(((Turn)Main.turns.get(Main.turns.size()-1)).kristoff != null) {((Turn) Main.turns.get(Main.turns.size() - 1)).kristoff.act();}
            hasKristoffReachedAnna();
            if(((Turn)Main.turns.get(Main.turns.size()-1)).hans != null) ((Turn)Main.turns.get(Main.turns.size()-1)).hans.act();
            ((Turn)Main.turns.get(Main.turns.size()-1)).elsa.act();

            ((Turn) turns.get(turns.size()-1)).snowmen.forEach((og) -> {
                ((Snowman)og).act();
            });
            ((Turn) turns.get(turns.size()-1)).soldiers.forEach((og) -> {
                ((Soldier)og).act();
            });
            ((Turn)Main.turns.get(Main.turns.size()-1)).wolves.forEach((og) -> {
                ((Wolf)og).act();
            });
            ((Turn)Main.turns.get(Main.turns.size()-1)).iceBreakers.forEach((og) -> {
                ((IceBreaker)og).act();
            });
            for(int i = 0; i< mapSize;i++)for(int j = 0; j< mapSize;j++){
                ((Turn) turns.get(turns.size()-1)).map[j][i].fieldImpact();
            }




        }
        // jak chcesz kazać postaciom coś robić, to właśnie tutaj jest odpowiednie miejsce.

         simulationBody = simulationRoot();
        Simulation.loadMap(simulationBody, (Turn) Main.turns.get(0)); // dodaliśmy mapę z polami
        Scene simulationScene = new Scene(simulationBody);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(simulationScene);
        stage.setFullScreen(true);
        stage.show(); //show yourself!
    }

    //służy dodaniu do sceny niezmiennych elementów
    static public HBox simulationRoot(){
    HBox body = new HBox();
    body.setAlignment(Pos.CENTER);

    GridPane mapGrid = new GridPane();
    mapGrid.setAlignment(Pos.CENTER);

    for(int i =0; i< Main.mapSize; i++) for(int j = 0; j < Main.mapSize;j++) mapGrid.add(new StackPane(),  j, i);

    VBox rightMenu = new VBox();
    Image logo  = new Image("file:src/main/resources/com/example/frozentheultimatebattlesimulation/img/Frozen.png");
    ImageView logoView = new ImageView(logo);

        HBox turnChanger = new HBox();
        turnChanger.setAlignment(Pos.CENTER);
    Text setTurnText = new Text("Turn");
    setTurnText.setStyle("-fx-font-size: 30; ");
    Spinner<Integer> turnSpinner = new Spinner<>(0, Main.turns.size()-1, 0);
    turnSpinner.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
    Text turnDurationText = new Text("Turn Duration[ms]");
    turnDurationText.setStyle("-fx-font-size: 30; ");
    Spinner<Integer> durationSpinner = new Spinner<>(100, 10000, 500, 100);
    Button autoplay = new Button("Autoplay ▶");
    Button IdsOnOff = new Button("ON/OFF IDs");

    ScrollPane notifications = new ScrollPane();


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


    // event handler dla spinnerka
        turnSpinner.valueProperty().addListener((obs, oldValue, newValue) ->
        {
                        loadMap(simulationBody, (Turn) Main.turns.get(newValue));
        });

        IdsOnOff.setOnAction((e) -> {
            if(showIds) showIds=false;
            else showIds=true;

            loadMap(simulationBody, (Turn) Main.turns.get(turnSpinner.getValue()));
        });
        turnChanger.getChildren().addAll(setTurnText,turnSpinner, turnDurationText,durationSpinner, autoplay, IdsOnOff);

        GridPane stats = new GridPane();
        stats.setVgap(5.0);
        stats.setHgap(20.0);
        stats.add(new Text("Elsa's army"), 2, 0);
        stats.add(new Text("Hans' army"), 3, 0);
        stats.add(new Text("Ice breakers"), 4, 0);
        stats.add(new Text("Wolves"), 5, 0);
        stats.add(new Text("Total"), 1, 1);
        stats.add(new Text("Alive"), 1, 2);
        stats.add(new Text("Upgraded & alive"), 1, 3);
        stats.add(new Text(Integer.toString(Main.elsasArmySize)), 2, 1);
        stats.add(new Text(Integer.toString(Main.elsasArmySize)), 2, 2);
        stats.add(new Text(Integer.toString(0)), 2, 3);
        stats.add(new Text(Integer.toString(Main.hansArmySize)), 3, 1);
        stats.add(new Text(Integer.toString(Main.hansArmySize)), 3, 2);
        stats.add(new Text(Integer.toString(0)), 3, 3);
        stats.add(new Text(Integer.toString((int) Math.floor(0.04*Math.pow(mapSize, 2)))), 4, 1);
        stats.add(new Text(Integer.toString((int) Math.floor(0.04*Math.pow(mapSize, 2)))), 4, 2);
        stats.add(new Text(Integer.toString((int) Math.floor(0.04*Math.pow(mapSize, 2)))), 5, 1);
        stats.add(new Text(Integer.toString((int) Math.floor(0.04*Math.pow(mapSize, 2)))), 5, 2);







        rightMenu.getChildren().addAll(logoView, turnChanger, stats, notifications);



    body.getChildren().addAll(mapGrid, rightMenu);
    setMargin(rightMenu, new Insets(0,0,0,30));
    return body;
    }

    static public void loadMap(HBox simulationRoot, Turn turn){
        Soldier.generateIdImages();
        Snowman.generateIdImages();
        for(int i =0; i< Main.mapSize; i++) for(int j = 0; j < Main.mapSize;j++){
            if(((StackPane)((GridPane)simulationRoot.getChildren().get(0))
                    .getChildren().get(i*Main.mapSize+j))
                    .getChildren().size()>0) {
                ((StackPane) ((GridPane) simulationRoot.getChildren().get(0))
                        .getChildren().get(i * Main.mapSize + j))
                        .getChildren().removeAll();
            }

            ((StackPane)((GridPane)simulationRoot.getChildren().get(0))
                    .getChildren().get(i*Main.mapSize+j))
                    .getChildren().add(new ImageView(Field.fieldImages[Arrays.asList(Field.fieldTypes).indexOf(turn.map[i][j].type)]));

        };
        for(int i = 0; i < turn.iceBreakers.size(); i++){ // ładujemy łamaczy lodu
                     ((StackPane)((GridPane)simulationRoot.getChildren().get(0))
                    .getChildren().get(((IceBreaker)turn.iceBreakers.get(i)).y*Main.mapSize+((IceBreaker)turn.iceBreakers.get(i)).x))
                    .getChildren().add(new ImageView(((IceBreaker) turn.iceBreakers.get(i)).characterImage));

                     if(showIds){
                         Text id = new Text(Integer.toString(((IceBreaker) turn.iceBreakers.get(i)).id));
                         int fontSize = (int) ((Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize) *0.65);
                         id.setStyle("-fx-font: "+fontSize+" impact; -fx-stroke: white; -fx-stroke-width: 1; -fx-fill: #9900ff;");
                         ((StackPane)((GridPane)simulationRoot.getChildren().get(0))
                                 .getChildren().get(((IceBreaker)turn.iceBreakers.get(i)).y*Main.mapSize+((IceBreaker)turn.iceBreakers.get(i)).x))
                                 .getChildren().add(id);
                     }
        }


        for(int i = 0; i < turn.wolves.size(); i++){ // ładujemy wilki
            ((StackPane)((GridPane)simulationRoot.getChildren().get(0))
                    .getChildren().get(((Wolf)turn.wolves.get(i)).y*Main.mapSize+((Wolf)turn.wolves.get(i)).x))
                    .getChildren().add(new ImageView(((Wolf) turn.wolves.get(i)).characterImage));

            if(showIds){
                Text id = new Text(Integer.toString(((Wolf) turn.wolves.get(i)).id));
                int fontSize = (int) ((Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize) *0.65);
                id.setStyle("-fx-font: "+fontSize+" impact; -fx-stroke: white; -fx-stroke-width: 1; -fx-fill: #0066ff;");
                ((StackPane)((GridPane)simulationRoot.getChildren().get(0))
                        .getChildren().get(((Wolf)turn.wolves.get(i)).y*Main.mapSize+((Wolf)turn.wolves.get(i)).x))
                        .getChildren().add(id);
            }
        }

        for(int i = 0; i < turn.snowmen.size(); i++){ // ładujemy armię Elsy
            ((StackPane)((GridPane)simulationRoot.getChildren().get(0))
                    .getChildren().get(((Snowman)turn.snowmen.get(i)).y*Main.mapSize+((Snowman)turn.snowmen.get(i)).x))
                    .getChildren().add(new ImageView(((Snowman) turn.snowmen.get(i)).characterImage));

            if(showIds){
                ((StackPane)((GridPane)simulationRoot.getChildren().get(0))
                        .getChildren().get(((Snowman)turn.snowmen.get(i)).y*Main.mapSize+((Snowman)turn.snowmen.get(i)).x))
                        .getChildren().add(Snowman.idImages[((Snowman)turn.snowmen.get(i)).id]);
            }

        }

        for(int i = 0; i < turn.soldiers.size(); i++){ // ładujemy armię Hansa
            ((StackPane)((GridPane)simulationRoot.getChildren().get(0))
                    .getChildren().get(((Soldier)turn.soldiers.get(i)).y*Main.mapSize+((Soldier)turn.soldiers.get(i)).x))
                    .getChildren().add(new ImageView(((Soldier) turn.soldiers.get(i)).characterImage));

            if(showIds){

                ((StackPane)((GridPane)simulationRoot.getChildren().get(0))
                        .getChildren().get(((Soldier)turn.soldiers.get(i)).y*Main.mapSize+((Soldier)turn.soldiers.get(i)).x))
                        .getChildren().add(Soldier.idImages[((Soldier)turn.soldiers.get(i)).id]);
            }
        }

        ((StackPane)((GridPane)simulationRoot.getChildren().get(0))
                .getChildren().get(turn.elsa.y*Main.mapSize+turn.elsa.x))
                .getChildren().add(new ImageView(turn.elsa.characterImage));

        if(turn.anna!=null) {
            ((StackPane) ((GridPane) simulationRoot.getChildren().get(0))
                    .getChildren().get(turn.anna.y * Main.mapSize + turn.anna.x))
                    .getChildren().add(new ImageView(turn.anna.characterImage));
        }

        if(turn.kristoff!=null) {
            ((StackPane) ((GridPane) simulationRoot.getChildren().get(0))
                    .getChildren().get(turn.kristoff.y * Main.mapSize + turn.kristoff.x))
                    .getChildren().add(new ImageView(turn.kristoff.characterImage));
        }

        if(turn.hans!=null) {
            ((StackPane) ((GridPane) simulationRoot.getChildren().get(0))
                    .getChildren().get(turn.hans.y * Main.mapSize + turn.hans.x))
                    .getChildren().add(new ImageView(turn.hans.characterImage));
        }

        int upgradedAndAliveSnowmen = turn.snowmen.stream()
                .filter(object -> ((Snowman)object).upgraded).toArray().length;
        int upgradedAndAliveSoldiers = turn.soldiers.stream()
                .filter(object -> ((Soldier)object).upgraded).toArray().length;

        ((Text) ((GridPane) ((VBox) simulationRoot.getChildren().get(1)).getChildren().get(2)).getChildren().get(8)).setText(Integer.toString(turn.snowmen.size()));; //wielkość żywej armii Elsy
        ((Text) ((GridPane) ((VBox) simulationRoot.getChildren().get(1)).getChildren().get(2)).getChildren().get(11)).setText(Integer.toString(turn.soldiers.size()));; //wielkość żywej armii Hansa
        ((Text) ((GridPane) ((VBox) simulationRoot.getChildren().get(1)).getChildren().get(2)).getChildren().get(14)).setText(Integer.toString(turn.iceBreakers.size()));; //wielkość żywej armii icebreakerów
        ((Text) ((GridPane) ((VBox) simulationRoot.getChildren().get(1)).getChildren().get(2)).getChildren().get(16)).setText(Integer.toString(turn.wolves.size()));; //wielkość żywej armii wilków
        ((Text) ((GridPane) ((VBox) simulationRoot.getChildren().get(1)).getChildren().get(2)).getChildren().get(9)).setText(Integer.toString(upgradedAndAliveSnowmen));; //wielkość zapgrejdowanej armii elsy
        ((Text) ((GridPane) ((VBox) simulationRoot.getChildren().get(1)).getChildren().get(2)).getChildren().get(12)).setText(Integer.toString(upgradedAndAliveSoldiers));; //wielkość zapgrejdowanej armii hansa



        ((ScrollPane)((VBox)simulationRoot.getChildren().get(1)).getChildren().get(3)).setContent(turn.notificationsGrid);
    }

static void hasKristoffReachedAnna(){
        if(((Turn) turns.get(turns.size()-1)).anna != null){

        int annaX = ((Turn) Main.turns.get(Main.turns.size()-1)).anna.x;
        int annaY = ((Turn) Main.turns.get(Main.turns.size()-1)).anna.y;

    for(int i = -1; i<=1;i++) for(int j=-1;j<=1; j++){
        if(Objects.equals(((Turn) turns.get(turns.size() - 1)).map[(annaY + i + mapSize) % mapSize][(annaX + j + mapSize) % mapSize].occupiedBy, "Kristoff")){
            ((Turn) turns.get(turns.size()-1)).kill(((Turn) turns.get(turns.size()-1)).kristoff);

            ((Turn) turns.get(turns.size()-1)).anna.hasHorse =true;
            ((Turn) turns.get(turns.size()-1)).anna.MoveRange =2;
            ((Turn) turns.get(turns.size()-1)).anna.characterImage = new Image("file:src/main/resources/com/example/frozentheultimatebattlesimulation/img/KristoffAndAnna.png", Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, Screen.getPrimary().getVisualBounds().getHeight()/Main.mapSize, true, true);
            Turn.notify("Kristoff stumbled upon Anna [" +annaX+","+annaY+"] and took her with him");
        }
}}


    }}
