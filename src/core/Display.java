package core;

import java.util.Arrays;

public class Display {

    private boolean[][] pixels;
    private static final int width = 64;
    private static final int height = 32;
  
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
        pixels = new boolean[width][height];
        clearDrawFlag();
    }

    public void setPixel(int x, int y, boolean value) {
        if (x < width && x >= 0 && y < height && y >= 0) {
            pixels[x][y] = value;
        }
    }

    public boolean getPixel(int x, int y) {
        if (x < width && x >= 0 && y < height && y >= 0) {
            return pixels[x][y];
        }
        throw new IndexOutOfBoundsException("Invalid Pixel Coordinates");
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