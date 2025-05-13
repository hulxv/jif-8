package scenes;

import core.Display;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameDisplay extends Canvas {

    private Canvas canvas;
    private StackPane box;
    private static Display display;
    private boolean[][] pixels;
    private int scale;
    private int height;
    private int width;

    public GameDisplay(Display display) {
        this.display = display;
        display.clear();
    }

    public void setScale(int Scale) {

        this.scale = Scale;
    }

    public void buildscreen() {
        this.canvas = new Canvas(width * scale, height * scale);

        this.box = new StackPane(canvas);
        box.setStyle("-fx-background-color: rgba(33,32,32,1);");

        render();

    }

    public Node getGameScreen() {

        return box;
    }

    public void render() {
        GraphicsContext gc = getGraphicsContext2D();

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, getWidth(), getHeight());

        // Draw active pixels
        gc.setFill(Color.WHITE);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (pixels[x][y]) {
                    gc.fillRect(x * scale, y * scale, scale, scale);
                }
            }
        }
    }

}
