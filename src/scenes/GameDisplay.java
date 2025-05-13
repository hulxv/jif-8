package scenes;

import core.Display;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameDisplay extends Canvas {

    private static Display display;
    private boolean[][] pixels;
    private int scale;
    private int height;
    private int width;

    public GameDisplay(Display display) {
        this.display = display;
    }


    public void DisplayScale(boolean[][] pixels,int Width , int Height , int Scale){

        this.scale = Scale;
        this.width = Width;
        this.height = Height;
        this.pixels = display.getDisplayBuffer();
        setWidth (width * scale);
        setHeight  (height * scale);

    }

    public void clear(){
        for (int x = 0 ; x < width ; x++){
            for (int y = 0 ; y < height ; y++){
                display.setPixel(x , y , false);
            }
        }
        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0 , 0 , getWidth(), getHeight());
    }

    public boolean drawPixel(int x , int y){
        x = x % width;
        y = y % height;

        pixels[x][y] ^= true;

        return !pixels[x][y];
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






    @Override
    public void start(Stage primaryStage) {

    }
    public void main(String[] args) {launch(args);
    }


      public void setWidth(int width){
         this.width = width;}

    public void setHeight(int height){
        this.height = height;}



}




