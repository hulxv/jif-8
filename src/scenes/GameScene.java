package scenes;

import core.Display;
import core.Emulator;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import scenes.components.DebuggerGUIComponents;

public class GameScene extends Scene {
    private GameDisplay gDisplay;
    private DebuggerGUIComponents debuggerGUIComponents;

    public GameScene(Emulator emulator) {
        super(new SplitPane(), 1000, 750);
        SplitPane root = (SplitPane) getRoot();
        debuggerGUIComponents = new DebuggerGUIComponents(emulator);
        debuggerGUIComponents.setMinWidth(440);
        debuggerGUIComponents.setMaxWidth(440);

        Display display = emulator.getCPU().getDisplay();
        gDisplay = new GameDisplay(emulator, display, 20);

        BorderPane gameScreenPane = new BorderPane(gDisplay.getDisplay());
        gameScreenPane.setStyle("-fx-background-color: rgba(33,32,32,1);");

        gameScreenPane.setMinWidth(gDisplay.getWidth() * 2);

        root.getItems().addAll(gameScreenPane, debuggerGUIComponents);
        root.setOrientation(Orientation.HORIZONTAL);
        root.setDividerPositions(0.66);

        this.setOnKeyPressed(e -> {
            emulator.getCPU().getKeyboard().handleKeyPressed(e);
        });
        this.setOnKeyReleased(e -> {
            emulator.getCPU().getKeyboard().handleKeyReleased(e);
        });
    }
}
