import core.CPU;
import core.Emulator;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chip-8 Debugger");
        primaryStage.show();
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
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        Emulator emulator = new Emulator();
        emulator.emulateCycle();
        launch();
    }
}