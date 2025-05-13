package core;

public class Display {

    private boolean[][] pixels;
    private static final int width = 64;
    private static final int height = 32;

    public Display() {
        pixels = new boolean[width][height];
    }

    public void clear() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixels[i][j] = false;
            }
        }
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