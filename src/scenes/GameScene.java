package scenes;

import core.Display;
import core.Emulator;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import scenes.components.DebuggerGUIComponents;

public class GameScene extends Scene {
    private GameDisplay gDisplay;
    private DebuggerGUIComponents debuggerGUIComponents;

    public GameScene(Emulator emulator) {
        super(new SplitPane(), 1000, 750);
        SplitPane root = (SplitPane) getRoot();
        debuggerGUIComponents = new DebuggerGUIComponents(emulator);

        VBox debuggerbox = debuggerGUIComponents.buildContentBox();
        debuggerbox.setMinWidth(350);

        Display display = emulator.getCPU().getDisplay();
        gDisplay = new GameDisplay(display, 20);

        BorderPane gameScreenPane = new BorderPane(gDisplay.getCanvas());

        gameScreenPane.setStyle("-fx-background-color: rgba(33,32,32,1);");

        gameScreenPane.setMinWidth(gDisplay.getWidth());

        root.getItems().addAll(gameScreenPane, debuggerbox);
        root.setOrientation(Orientation.HORIZONTAL);
        root.setDividerPositions(0.66);
    }

    public void updateDisplay() {
        System.out.println("Updating display");
        if (gDisplay != null) {
            gDisplay.render();
        }
        if (debuggerGUIComponents != null) {
            debuggerGUIComponents.updateDebugInfo();
        }
    }
}
