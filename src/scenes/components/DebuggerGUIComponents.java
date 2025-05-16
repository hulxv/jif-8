package scenes.components;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import core.CPU;
import core.Emulator;
import core.Memory;
import core.Stack;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DebuggerGUIComponents extends VBox {
    private static Emulator emu;
    private final TextField[] memoryFields = new TextField[Memory.MEMORY_SIZE];
    private final TextField[] stackFields = new TextField[Stack.STACK_SIZE];
    private HashMap<String, Number> specialRegisters = new HashMap<>();
    private GridPane registersGrid = new GridPane();
    private Timeline timeline;

    private Font usedFont;
    private Font labelFont;
    private Font buttonFont;

    public DebuggerGUIComponents(Emulator emulator) {
        super();
        emu = emulator;
        CPU cpu = emu.getCPU();
        initializeFonts();

        specialRegisters.put(" I", (int) cpu.getI());
        specialRegisters.put("PC", (int) cpu.getPC());
        specialRegisters.put("DT", (int) cpu.getDelayTimer());
        specialRegisters.put("ST", (int) cpu.getSoundTimer());
        specialRegisters.put("SP", (int) cpu.getStack().getStackPointer());

        for (int i = 0; i < memoryFields.length; i++) {
            memoryFields[i] = createStyledTextField(String.format("0x%03X: 0x%02X", i, cpu.getMemory().get(i)));
        }
        for (int i = 0; i < stackFields.length; i++) {
            stackFields[i] = createStyledTextField(String.format("0x%03X: 0x%04X", i, (int) cpu.getStack().get(i)));
        }
        SplitPane memoryAndStack = buildMemoryAndStackBoxes();
        memoryAndStack.setStyle("-fx-background-color: rgb(24,20,20);");

        VBox.setVgrow(memoryAndStack, Priority.ALWAYS);
        VBox.setVgrow(this, Priority.ALWAYS);

        this.getChildren().addAll(memoryAndStack, buildRegistersBox(), buildButtonBar());
        this.setSpacing(10);
        this.setPadding(new Insets(10));
        this.setStyle("-fx-background-color: rgb(24,20,20);");

        timeline = new Timeline(new KeyFrame(Duration.millis(1000 / 2), e -> {
            if (emulator.isRunning()) {
                updateDebugInfo();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    Timeline getTimeline() {
        return timeline;
    }

    private void initializeFonts() {
        usedFont = Font.loadFont(getClass().getResource("/fonts/PressStart2P-Regular.ttf").toExternalForm(), 10);
        labelFont = Font.loadFont(getClass().getResource("/fonts/PressStart2P-Regular.ttf").toExternalForm(), 12);
        buttonFont = Font.loadFont(getClass().getResource("/fonts/PressStart2P-Regular.ttf").toExternalForm(), 14);
    }

    private SplitPane buildMemoryAndStackBoxes() {
        VBox memoryBox = buildSectionBox(memoryFields, "Memory");
        VBox stackBox = buildSectionBox(stackFields, "Stack");

        SplitPane memoryAndStackBox = new SplitPane(memoryBox, stackBox);
        memoryAndStackBox.setOrientation(Orientation.HORIZONTAL);
        memoryAndStackBox.setDividerPositions(0.5);

        VBox.setVgrow(memoryAndStackBox, Priority.NEVER);
        return memoryAndStackBox;
    }

    private VBox buildSectionBox(TextField[] fields, String label) {
        VBox contentBox = new VBox(5);
        contentBox.setPadding(new Insets(5));
        contentBox.setStyle("-fx-background-color: rgb(24,20,20);");

        for (int i = 0; i < fields.length; i++) {
            fields[i].setFont(usedFont);
            contentBox.getChildren().add(fields[i]);
        }

        ScrollPane scrollPane = new ScrollPane(contentBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(250);
        scrollPane.setStyle(
                "-fx-background: rgb(24,20,20); -fx-background-color: rgb(24,20,20); -fx-border-color: white;");
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

        registersGrid.setStyle("-fx-background-color: rgb(24,20,20); -fx-border-color: white;");
        registersGrid.setHgap(5);
        registersGrid.setVgap(5);
        registersGrid.setPadding(new Insets(5));

        for (int i = 0; i < emu.getCPU().getRegisters().getSize(); i++) {
            registersGrid.add(
                    createStyledTextField(
                            String.format("V%X: 0x%03X", i, emu.getCPU().getRegisters().getRegister(i))),
                    i % 2, i / 2);
        }

        for (int i = 0; i < specialRegisters.size(); i++) {
            String k = (String) specialRegisters.keySet().toArray()[i];
            registersGrid.add(
                    createStyledTextField(String.format("%s: 0x%03X", k, specialRegisters.get(k))), 2, i);
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

        for (Button btn : new Button[] { startButton, pauseButton, resetButton, loadButton }) {
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

        VBox buttonBox = new VBox(buttonBar);
        VBox.setVgrow(buttonBox, Priority.NEVER);

        loadButton.setOnAction(event -> {
            try {
                fileChooser();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        startButton.setOnAction(event -> emu.start());
        pauseButton.setOnAction(event -> emu.pause());
        resetButton.setOnAction(event -> {
            emu.reset();
            updateDebugInfo();
        });

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

    public void updateDebugInfo() {
        CPU cpu = emu.getCPU();

        specialRegisters.put(" I", (int) cpu.getI());
        specialRegisters.put("PC", (int) cpu.getPC());
        specialRegisters.put("DT", (int) cpu.getDelayTimer());
        specialRegisters.put("ST", (int) cpu.getSoundTimer());
        specialRegisters.put("SP", (int) cpu.getStack().getStackPointer());
        for (int i = 0; i < emu.getCPU().getRegisters().getSize(); i++) {
            registersGrid.add(
                    createStyledTextField(
                            String.format("V%X: 0x%03X", i, emu.getCPU().getRegisters().getRegister(i))),
                    i % 2, i / 2);
        }
        for (int i = 0; i < specialRegisters.size(); i++) {
            String k = (String) specialRegisters.keySet().toArray()[i];
            registersGrid.add(
                    createStyledTextField(String.format("%s: 0x%03X", k, specialRegisters.get(k))), 2, i);
        }

        for (int i = 0; i < stackFields.length; i++) {
            stackFields[i].setText(String.format("0x%03X: 0x%04X", i, (int) cpu.getStack().get(i)));
        }
    }

    private File fileChooser() throws IOException {

        Stage root = (Stage) Stage.getWindows().getLast();
        FileChooser fc = new FileChooser();

        File file = fc.showOpenDialog(root);
        if (file != null) {
            emu.loadRom(file.getAbsolutePath());
            System.out.println("File loaded: " + file.getAbsolutePath());
            for (int i = 0; i < memoryFields.length; i++) {
                memoryFields[i].setText(String.format("0x%03X: 0x%02X", i, emu.getCPU().getMemory().get(i)));
            }

            updateDebugInfo();
        } else {
            System.out.println("File selection cancelled.");
        }
        return file;
    }

}