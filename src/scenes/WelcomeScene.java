package scenes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import core.Emulator;

public class WelcomeScene {

    private Font labelFont;
    private Font label2Font;
    private Font buttonFont;
    private Scene scene1;
    private Stage stage;

    private Emulator emulator;

    public WelcomeScene(Emulator emulator) {
        this.emulator = emulator;
        labelFont = Font.loadFont(getClass().getResource("/fonts/PressStart2P-Regular.ttf").toExternalForm(),
                30);
        label2Font = Font.loadFont(getClass().getResource("/fonts/PressStart2P-Regular.ttf").toExternalForm(),
                40);
        buttonFont = Font.loadFont(getClass().getResource("/fonts/PressStart2P-Regular.ttf").toExternalForm(),
                14);
    }

    private Label buildlabel() {

        Label welcome = new Label("Welcome to ");
        welcome.setFont(labelFont);
        welcome.setTextFill(Color.web("#24e804"));
        welcome.setLayoutX(360);
        welcome.setLayoutY(200);
        welcome.setPrefSize(600, 50);

        return welcome;
    }

    private Label buildlabel2() {

        Label jif = new Label("JIF-8 ");
        jif.setFont(label2Font);
        jif.setTextFill(Color.web("#24e804"));
        jif.setLayoutX(400);
        jif.setLayoutY(270);
        jif.setPrefSize(700, 100);

        return jif;
    }

    private Button buildbutton() {

        Button load = new Button("LOAD GAME");
        load.setFont(buttonFont);
        load.setLayoutX(430);
        load.setLayoutY(500);
        load.setOnAction(event -> {
            try {
                fileChooser();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return load;
    }

    public Scene createScene() {
        Label welcome = buildlabel();
        Label jif = buildlabel2();
        Button load = buildbutton();
        AnchorPane gameScreen = new AnchorPane();
        gameScreen.setStyle("-fx-background-color: rgba(33,32,32,1);");

        gameScreen.getChildren().addAll(welcome, jif, load);

        Scene scene = new Scene(gameScreen, 1000, 750);

        return scene;

    }

    public Stage render(Stage stage) {
        this.stage = stage;
        stage.setTitle("JIF-8 Emulator");
        stage.setScene(createScene());
        return stage;
    }

    private File fileChooser() throws IOException {

        FileChooser fc = new FileChooser();

        File file = fc.showOpenDialog(stage);
        if (file != null) {
            emulator.loadRom(file.getAbsolutePath());
            System.out.println("File loaded: " + file.getAbsolutePath());
        } else {
            System.out.println("File selection cancelled.");
        }
        return file;
    }

}