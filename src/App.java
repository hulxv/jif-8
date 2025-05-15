import core.Emulator;
import scenes.GameScene;
import scenes.WelcomeScene;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application {
    private static Emulator emulator = new Emulator();
    private Timeline gameLoop;

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setScene(new WelcomeScene(emulator));

        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(Duration.millis((1000 / 25)), e -> {
            if (emulator.isRunning()) {
                emulator.emulateCycle();
                Scene currentScene = primaryStage.getScene();
                GameScene gameScene = (GameScene) currentScene;
                gameScene.updateDisplay();
            }
        });

        gameLoop.getKeyFrames().add(kf);
        primaryStage.setOnCloseRequest(e -> {
            gameLoop.stop();
            Platform.exit();
        });

        primaryStage.sceneProperty().addListener((ob, oldScene, currentScene) -> {
            System.out.println("Scene was changed");
            if (emulator.isRunning()) {
                System.out.println("Gameloop is starting....");
                gameLoop.play();
            }

        });
        primaryStage.show();

    }

    public static void main(String[] args) {
        emulator.emulateCycle();
        launch();
    }
}