package core;

import javax.sound.sampled.*;
import java.io.IOException;

public class SoundSystem {
    private Clip clip;

    public SoundSystem() {
        //initialize();
    }

    public void initialize() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getResource("/home/mahmoudfathallah/Project/Project/resources/beep.wav"));

            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Failed to initialize sound: " + e.getMessage());
        }
    }

    public void playSound() {
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}