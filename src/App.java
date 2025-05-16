import core.Emulator;
import scenes.GameScene;
import scenes.WelcomeScene;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private static Emulator emulator = new Emulator();
    private AnimationTimer gameLoop;
    private long lastEmulationTime = 0;

    private final int CYCLES_PER_SECOND = 400;

    private final long CYCLE_INTERVAL = 1_000_000_000 / CYCLES_PER_SECOND;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new WelcomeScene(emulator));

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (emulator.isRunning() && now - lastEmulationTime >= CYCLE_INTERVAL) {
                    int cyclesToRun = Math.min(100, (int) ((now - lastEmulationTime) / CYCLE_INTERVAL));

                    for (int i = 0; i < cyclesToRun; i++) {
                        emulator.emulateCycle();
                    }

                    lastEmulationTime = now;
                }
            }
        };

        primaryStage.setOnCloseRequest(e -> {
            if (gameLoop != null) {
                gameLoop.stop();
            }
            Platform.exit();
        });

        primaryStage.sceneProperty().addListener((ob, oldScene, currentScene) -> {
            System.out.println("Scene was changed");
            if (emulator.isRunning()) {
                System.out.println("Gameloop is starting....");
                if (gameLoop != null) {
                    lastEmulationTime = System.nanoTime();
                    gameLoop.start();
                }
            }
        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}