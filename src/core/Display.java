package core;

import java.util.Arrays;

public class Display {

    private boolean[][] pixels;
    private static final int width = 64;
    private static final int height = 32;

    public Display() {
        pixels = new boolean[width][height];
    }
  
    public static final int WIDTH = 64;
    public static final int HEIGHT = 32;
    public boolean drawFlag = false;

    public Display() {
        reset();
    }

    public boolean getDrawFlag() {
        return drawFlag;
    }

    public void setDrawFlag() {
        drawFlag = true;
    }

    public void clearDrawFlag() {
        drawFlag = false;
    }

    public void reset() {
        pixels = new boolean[WIDTH][HEIGHT];
    }

    public void setPixel(int x, int y, boolean value) {
        if (x < WIDTH && x >= 0 && y < HEIGHT && y >= 0) {
            pixels[x][y] = value;
        }
    }

    public boolean getPixel(int x, int y) {
        if (x < width && x >= 0 && y < height && y >= 0) {
            return pixels[x][y];
        }
        throw new IndexOutOfBoundsException("Invalid Pixle Coordinates");
    }

    public boolean[][] getDisplayBuffer() {
        return pixels;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}