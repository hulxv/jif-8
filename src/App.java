import core.CPU;
import core.Emulator;
import core.Registers;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        Label label = new Label("Hello, JavaFX!");
        Scene scene = new Scene(label, 400, 200);
        stage.setScene(scene);
        stage.setTitle("JavaFX Flat Maven Project");
        stage.show();
    }

    public static void main(String[] args) {
        Emulator emulator = new Emulator();
        launch();
    }
}
