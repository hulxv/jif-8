package core;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import java.util.function.Consumer;

public class Keyboard {

    private final boolean[] keys = new boolean[16];
    private Consumer<Byte> waitingKeyCallback = null;

    public Keyboard() {
        initialize();
    }

    public void initialize() {
        for (int i = 0; i < keys.length; i++) {
            keys[i] = false;
        }
        waitingKeyCallback = null;
    }

    public boolean isKeyPressed(byte key) {
        return keys[key & 0xF];
    }

    public void setKeyPressed(byte key, boolean pressed) {
        keys[key & 0xF] = pressed;
    }

    public boolean isWaitingForKey() {
        return waitingKeyCallback != null;
    }

    public void waitForKeyPress(Consumer<Byte> callback) {
        this.waitingKeyCallback = callback;
    }

    public void handleKeyPressed(KeyEvent event) {
        Byte key = mapToChip8Key(event.getCode());
        if (key != null) {
            setKeyPressed(key, true);
            if (isWaitingForKey()) {
                waitingKeyCallback.accept(key);
                waitingKeyCallback = null;
            }
        }
    }

    public void handleKeyReleased(KeyEvent event) {
        Byte key = mapToChip8Key(event.getCode());
        if (key != null) {
            setKeyPressed(key, false);
        }
    }

    private Byte mapToChip8Key(KeyCode code) {
        switch (code) {
            case DIGIT1: return 0x1;
            case DIGIT2: return 0x2;
            case DIGIT3: return 0x3;
            case DIGIT4: return 0xC;
    
            case Q: return 0x4;
            case W: return 0x5;
            case E: return 0x6;
            case R: return 0xD;
    
            case A: return 0x7;
            case S: return 0x8;
            case D: return 0x9;
            case F: return 0xE;
    
            case Z: return 0xA;
            case X: return 0x0;
            case C: return 0xB;
            case V: return 0xF;
    
            default: return null;
        }
    }
}