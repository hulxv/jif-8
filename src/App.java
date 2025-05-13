import core.Emulator;
import scenes.GameScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    private static Emulator emulator = new Emulator();

    @Override
    public void start(Stage primaryStage) {
        // WelcomeScene welcome = new WelcomeScene(emulator);
        // Stage welcomeStage = welcome.render(new Stage());
        // welcomeStage.show();
        GameScene gameScene = new GameScene(emulator);
        Stage gameStage = gameScene.render(new Stage());
        gameStage.show();

    }

    public static void main(String[] args) {
        emulator.emulateCycle();
        launch();
    }
}