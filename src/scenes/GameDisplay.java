package scenes;

import java.util.Stack;

import core.Display;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameDisplay extends Canvas {

    private GraphicsContext gc;
    private static Display display;
    private int scale = 10;

    public GameDisplay(Display _display, int scale) {
        super(display.getWidth() * scale, display.getHeight() * scale);
        this.gc = getGraphicsContext2D();
        this.scale = scale;
        display = _display;
    }

    public void setScale(int Scale) {
        this.scale = Scale;
    }

    public Canvas getCanvas() {
        render();
        return this;
    }

    public void render() {
        boolean[][] pixels = display.getDisplayBuffer();

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, getWidth(), getHeight());

        // Draw active pixels
        gc.setFill(Color.WHITE);
        for (int x = 0; x < display.getWidth(); x++) {
            for (int y = 0; y < display.getHeight(); y++) {
                if (pixels[x][y]) {
                    gc.fillRect(x * scale, y * scale, scale, scale);
                }
            }
        }
    }

}
