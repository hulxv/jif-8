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

    public WelcomeScene(Emulator emulator, Stage stage) {
        this.stage = stage;
        this.emulator = emulator;
        labelFont = Font.loadFont(getClass().getResource("/fonts/PressStart2P-Regular.ttf").toExternalForm(),
                30);
        label2Font = Font.loadFont(getClass().getResource("/fonts/PressStart2P-Regular.ttf").toExternalForm(),
                40);
        buttonFont = Font.loadFont(getClass().getResource("/fonts/PressStart2P-Regular.ttf").toExternalForm(),
                14);
    }

    private Label buildWelcomeLabel() {

        Label welcome = new Label("Welcome to ");
        welcome.setFont(labelFont);
        welcome.setTextFill(Color.web("#24e804"));

        return welcome;
    }

    private Label buildJif8Label() {

        Label jif = new Label("JIF-8 Emulator");
        jif.setFont(label2Font);
        jif.setTextFill(Color.web("#24e804"));

        return jif;
    }

    private Button buildLoadbutton() {

        Button load = new Button("LOAD GAME");
        load.setFont(buttonFont);
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
        Label welcome = buildWelcomeLabel();
        Label jif = buildJif8Label();
        Button load = buildLoadbutton();
        VBox gameScreen = new VBox(welcome, jif, load);
        gameScreen.setSpacing(20);
        gameScreen.setStyle("-fx-background-color: rgba(33,32,32,1);");
        gameScreen.setAlignment(javafx.geometry.Pos.CENTER);

        Scene scene = new Scene(gameScreen);

        return scene;

    }

    public Stage render() {
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
            stage.close();
        } else {
            System.out.println("File selection cancelled.");
        }
        return file;
    }

}