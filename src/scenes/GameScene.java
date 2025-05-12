package scenes;

import core.Emulator;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import scenes.components.DebuggerGUIComponents;

public class GameScene {
    private static Emulator emulator;

    public GameScene(Emulator emulator) {
        this.emulator = emulator;
    }

    public Stage render(Stage stage) {
        stage.setTitle("JIF-8 Runner");
        stage.show();
        DebuggerGUIComponents debuggerGUIComponents = new DebuggerGUIComponents();

        VBox contentBox = debuggerGUIComponents.buildContentBox();
        contentBox.setMinWidth(350);
        contentBox.setMaxWidth(350);

        AnchorPane gameScreen = new AnchorPane();
        gameScreen.setStyle("-fx-background-color: rgba(33,32,32,1);");

        SplitPane contentSplit = new SplitPane(gameScreen, contentBox);
        contentSplit.setOrientation(Orientation.HORIZONTAL);
        contentSplit.setDividerPositions(0.66);

        Scene scene = new Scene(contentSplit, 1000, 750);
        stage.setScene(scene);

        return stage;
    }

}
