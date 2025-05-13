package scenes;

import core.Display;
import core.Emulator;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import scenes.components.DebuggerGUIComponents;

public class GameScene {
    private static Emulator emu;

    public GameScene(Emulator emulator) {
        emu = emulator;
    }

    public Stage render(Stage stage) {
        stage.setTitle("JIF-8 Runner");
        stage.show();
        DebuggerGUIComponents debuggerGUIComponents = new DebuggerGUIComponents();

        VBox contentBox = debuggerGUIComponents.buildContentBox();
        contentBox.setMinWidth(350);

        Display display = emu.getCPU().getDisplay();
        GameDisplay gDisplay = new GameDisplay(display, 20);

        BorderPane gameScreenPane = new BorderPane(gDisplay.getCanvas());

        gameScreenPane.setStyle("-fx-background-color: rgba(33,32,32,1);");

        gameScreenPane.setMinWidth(gDisplay.getWidth());

        SplitPane contentSplit = new SplitPane(gameScreenPane, contentBox);
        contentSplit.setOrientation(Orientation.HORIZONTAL);
        contentSplit.setDividerPositions(0.66);

        Scene scene = new Scene(contentSplit, 1000, 750);
        stage.setScene(scene);
        stage.setResizable(true);

        return stage;
    }

}
