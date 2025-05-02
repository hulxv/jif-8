package core;

public class Display {

    private boolean[][] pixels;
    public static final int WIDTH = 64;
    public static final int HEIGHT = 32;
    
    public Display() {
        pixels = new boolean[WIDTH][HEIGHT];
    }

    public void clear() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                pixels[i][j] = false;
            }
        }
    }

    public void setPixel(int x, int y, boolean value) {
        if (x < WIDTH && x >= 0 && y < HEIGHT && y >=0) {
            pixels[x][y] = value;
        }
    }

    public boolean getPixel(int x, int y) {
        if (x < WIDTH && x >= 0 && y < HEIGHT && y >=0) {
            return pixels[x][y];
        }
        throw new IndexOutOfBoundsException("Invalid Pixle Coordinates");
    }

    public boolean[][] getDisplayBuffer() {
        return pixels;
    }
}