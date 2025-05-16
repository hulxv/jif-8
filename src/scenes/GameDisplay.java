package scenes;

import core.Display;
import core.Emulator;
import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelBuffer;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import java.nio.ByteBuffer;

public class GameDisplay extends BorderPane {
    private static Display display;
    private AnimationTimer animationTimer;
    private int scale = 10;
    private final int width;
    private final int height;
    private final ImageView imageView;
    private final PixelBuffer<ByteBuffer> pixelBuffer;
    private final ByteBuffer buffer;

    public GameDisplay(Emulator emulator, Display display, int scale) {
        this.scale = scale;
        this.display = display;
        this.width = display.getWidth() * scale;
        this.height = display.getHeight() * scale;
        this.setMinWidth(this.width * 2);

        // Create pixel buffer with correct size (width * height * 4 bytes for BGRA)
        buffer = ByteBuffer.allocate(width * height * 4);
        pixelBuffer = new PixelBuffer<>(width, height, buffer, PixelFormat.getByteBgraPreInstance());

        // Create image view to display the buffer
        imageView = new ImageView(new WritableImage(pixelBuffer));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        this.setCenter(imageView);

        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (emulator.getCPU().getDrawFlag()) {
                    render();
                    emulator.getCPU().setDrawFlag(false);
                }
            }
        };
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public BorderPane getDisplay() {
        render();
        animationTimer.start();
        return this;
    }

    public void render() {
        boolean[][] pixels = display.getDisplayBuffer();
        buffer.clear();

        for (int y = 0; y < display.getHeight(); y++) {
            for (int x = 0; x < display.getWidth(); x++) {
                byte color = pixels[x][y] ? (byte) 0xFF : 0x00;

                // Scale the pixel
                for (int sy = 0; sy < scale; sy++) {
                    for (int sx = 0; sx < scale; sx++) {
                        // Fixed position calculation
                        int pos = ((y * scale + sy) * width + (x * scale + sx)) * 4;
                        buffer.put(pos, color); // B
                        buffer.put(pos + 1, color); // G
                        buffer.put(pos + 2, color); // R
                        buffer.put(pos + 3, (byte) 0xFF); // A
                    }
                }
            }
        }

        // Update the pixel buffer
        pixelBuffer.updateBuffer(b -> null);
    }

    public void stop() {
        if (animationTimer != null) {
            animationTimer.stop();
        }
    }
}
