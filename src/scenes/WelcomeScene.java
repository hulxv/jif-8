package scenes;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import core.Emulator;

public class WelcomeScene extends Scene {

    private Font labelFont;
    private Font label2Font;
    private Font buttonFont;

    private Emulator emulator;

    public WelcomeScene(Emulator emulator) {
        super(new VBox(), 1000, 750);
        VBox root = (VBox) getRoot();
        ImageView logo = new ImageView(new Image(getClass().getResourceAsStream("/jif8-logo.png")));
        logo.setFitWidth(300);
        logo.setPreserveRatio(true);
        logo.setSmooth(true);

        this.emulator = emulator;
        labelFont = Font.loadFont(getClass().getResource("/fonts/PressStart2P-Regular.ttf").toExternalForm(),
                30);
        label2Font = Font.loadFont(getClass().getResource("/fonts/PressStart2P-Regular.ttf").toExternalForm(),
                40);
        buttonFont = Font.loadFont(getClass().getResource("/fonts/PressStart2P-Regular.ttf").toExternalForm(),
                14);

        Label welcome = buildWelcomeLabel();
        Label jif = buildJif8Label();
        Button load = buildLoadbutton();
        root.getChildren().addAll(logo, welcome, jif, load);
        root.setSpacing(20);
        root.setStyle("-fx-background-color: rgba(33,32,32,1);");
        root.setAlignment(javafx.geometry.Pos.CENTER);

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

    private File fileChooser() throws IOException {

        Stage root = (Stage) getRoot().getScene().getWindow();
        FileChooser fc = new FileChooser();

        File file = fc.showOpenDialog(root);
        if (file != null) {
            emulator.loadRom(file.getAbsolutePath());
            System.out.println("File loaded: " + file.getAbsolutePath());
            root.setTitle("JIF-8 Emulator - " + file.getName());
            root.setScene(new GameScene(emulator));

        } else {
            System.out.println("File selection cancelled.");
        }
        return file;
    }

}