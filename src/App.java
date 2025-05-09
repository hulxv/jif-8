import core.CPU;
import core.Emulator;
import core.Registers;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {
    private final TextField[] memoryFields = new TextField[256];
    private final TextField[] stackFields = new TextField[64];
    private final TextField[] registerFields = new TextField[16];
    private final TextField[] specialRegisterFields = new TextField[5];
    private final String[] specialRegisters = {"PC", "SP", "DT", "ST", " I"};

    private Font usedFont;
    private Font labelFont;
    private Font buttonFont;

    private int[] memory = new int[256];
    private int[] stack = new int[64];
    private int[] registers = new int[16];
    private int[] specialRegistersData = new int[5];

    @Override
    public void start(Stage primaryStage) {
        usedFont = Font.loadFont(getClass().getResource("/fonts/PressStart2P-Regular.ttf").toExternalForm(), 10);
        labelFont = Font.loadFont(getClass().getResource("/fonts/PressStart2P-Regular.ttf").toExternalForm(), 12);
        buttonFont = Font.loadFont(getClass().getResource("/fonts/PressStart2P-Regular.ttf").toExternalForm(), 14);

        VBox contentBox = buildContentBox();
        contentBox.setMinWidth(350);
        contentBox.setMaxWidth(350);

        AnchorPane gameScreen = new AnchorPane();
        gameScreen.setStyle("-fx-background-color: rgba(33,32,32,1);");

        SplitPane contentSplit = new SplitPane(gameScreen, contentBox);
        contentSplit.setOrientation(Orientation.HORIZONTAL);
        contentSplit.setDividerPositions(0.75);

        Scene scene = new Scene(contentSplit, 1000, 750);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Chip-8 Debugger");
        primaryStage.show();
    }

    private void initializeCoreData() {
        for (int i = 0; i < 256; i++) {
            memory[i] = i * 0x1111; // Example data (will be replaced by data from core)
        }
        for (int i = 0; i < 64; i++) {
            stack[i] = i * 0x2222; // Example data (will be replaced by data from core)
        }
        for (int i = 0; i < 16; i++) {
            registers[i] = i * 0x3333; // Example data (will be replaced by data from core)
        }
        for (int i = 0; i < 5; i++) {
            specialRegistersData[i] = i * 0x4444; // Example data (will be replaced by data from core)
        }
    }

    private VBox buildContentBox() {
        VBox contentBox = new VBox();
        SplitPane memoryAndStack = buildMemoryAndStackBoxes();
        memoryAndStack.setStyle("-fx-background-color: rgb(24,20,20);");

        VBox.setVgrow(memoryAndStack, Priority.ALWAYS);
        VBox.setVgrow(contentBox, Priority.ALWAYS);

        contentBox.getChildren().addAll(memoryAndStack, buildRegistersBox(), buildButtonBar());
        contentBox.setSpacing(10);
        contentBox.setPadding(new Insets(10));
        contentBox.setStyle("-fx-background-color: rgb(24,20,20);");

        return contentBox;
    }

    private SplitPane buildMemoryAndStackBoxes() {
        VBox memoryBox = buildSectionBox(memoryFields, "Memory", 256);
        VBox stackBox = buildSectionBox(stackFields, "Stack", 16);

        SplitPane memoryAndStackBox = new SplitPane(memoryBox, stackBox);
        memoryAndStackBox.setOrientation(Orientation.HORIZONTAL);
        memoryAndStackBox.setDividerPositions(0.5);

        VBox.setVgrow(memoryAndStackBox, Priority.NEVER);
        return memoryAndStackBox;
    }

    private VBox buildSectionBox(TextField[] fields, String label, int count) {
        VBox contentBox = new VBox(5);
        contentBox.setPadding(new Insets(5));
        contentBox.setStyle("-fx-background-color: rgb(24,20,20);");

            for (int i = 0; i < count; i++) {
                fields[i] = createStyledTextField(String.format("0x%03X: %08X", i, 0x00000000));
                fields[i].setFont(usedFont);
                contentBox.getChildren().add(fields[i]);
            }

        ScrollPane scrollPane = new ScrollPane(contentBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(250);
        scrollPane.setStyle("-fx-background: rgb(24,20,20); -fx-background-color: rgb(24,20,20); -fx-border-color: white;");
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        Label boxLabel = new Label(label);
        boxLabel.setStyle("-fx-text-fill: white;");
        boxLabel.setFont(labelFont);

        VBox box = new VBox(boxLabel, scrollPane);
        box.setAlignment(Pos.TOP_CENTER);
        box.setStyle("-fx-background-color: rgb(24,20,20); -fx-border-color: white;");
        VBox.setVgrow(box, Priority.ALWAYS);

        return box;
    }

    private VBox buildRegistersBox() {
        Label registersLabel = new Label("Registers");
        registersLabel.setStyle("-fx-text-fill: white;");
        registersLabel.setFont(labelFont);

        GridPane registersGrid = new GridPane();
        registersGrid.setStyle("-fx-background-color: rgb(24,20,20); -fx-border-color: white;");
        registersGrid.setHgap(5);
        registersGrid.setVgap(5);
        registersGrid.setPadding(new Insets(5));

        for (int i = 0; i < 16; i++) {
            registerFields[i] = createStyledTextField(String.format("V%X: %08X", i, 0x00000000));
            registersGrid.add(registerFields[i], i % 2, i / 2);
        }

        for (int i = 0; i < specialRegisters.length; i++) {
            specialRegisterFields[i] = createStyledTextField(String.format("%s: %08X", specialRegisters[i], 0x00000000));
            registersGrid.add(specialRegisterFields[i], 2, i);
        }

        ScrollPane registersScroll = new ScrollPane(registersGrid);
        registersScroll.setFitToWidth(true);
        registersScroll.setStyle("-fx-background-color: rgb(24,20,20); -fx-border-color: white;");

        VBox registersVBox = new VBox(registersLabel, registersScroll);
        registersVBox.setStyle("-fx-background-color: rgb(24,20,20); -fx-border-color: white;");
        registersVBox.setAlignment(Pos.TOP_CENTER);
        VBox.setVgrow(registersVBox, Priority.NEVER);

        return registersVBox;
    }

    private VBox buildButtonBar() {
        Button startButton = new Button("Start");
        Button pauseButton = new Button("Pause");
        Button resetButton = new Button("Reset");
        Button loadButton = new Button("Load");
        Button memoryHexViewButton = new Button("Memory Hex View");

        for (Button btn : new Button[]{startButton, pauseButton, resetButton, loadButton, memoryHexViewButton}) {
            btn.setMaxWidth(Double.MAX_VALUE);
            btn.setFont(buttonFont);
        }

        GridPane buttonBar = new GridPane();
        buttonBar.setHgap(5);
        buttonBar.setVgap(5);
        buttonBar.setPadding(new Insets(5));

        buttonBar.add(startButton, 0, 0);
        GridPane.setHgrow(startButton, Priority.ALWAYS);

        buttonBar.add(pauseButton, 1, 0);
        GridPane.setHgrow(pauseButton, Priority.ALWAYS);

        buttonBar.add(resetButton, 0, 1);
        GridPane.setHgrow(resetButton, Priority.ALWAYS);

        buttonBar.add(loadButton, 1, 1);
        GridPane.setHgrow(loadButton, Priority.ALWAYS);

        buttonBar.add(memoryHexViewButton, 0, 2, 2, 1);
        GridPane.setHgrow(memoryHexViewButton, Priority.ALWAYS);

        startButton.setOnAction(event -> System.out.println("Start is pressed"));
        pauseButton.setOnAction(event -> System.out.println("Pause is pressed"));
        resetButton.setOnAction(event -> System.out.println("Reset is pressed"));
        loadButton.setOnAction(event -> System.out.println("Load is pressed"));
        memoryHexViewButton.setOnAction(event -> System.out.println("Memory Hex View is pressed"));

        VBox buttonBox = new VBox(buttonBar);
        VBox.setVgrow(buttonBox, Priority.NEVER);

        return buttonBox;
    }

    private TextField createStyledTextField(String text) {
        TextField textField = new TextField(text);
        textField.setEditable(false);
        textField.setFocusTraversable(false);
        textField.setStyle("-fx-background-color: rgb(24,20,20); -fx-text-fill: white;");
        textField.setFont(usedFont);
        return textField;
    }

    public static void main(String[] args) {
        Emulator emulator = new Emulator();
        launch();
    }
}