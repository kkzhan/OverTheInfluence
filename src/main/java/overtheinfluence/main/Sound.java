package main;

import javax.sound.sampled.*;
import java.net.*;
import java.util.*;

public class Sound {
    Clip clip;
    URL[] soundPath = new URL[3];
    ArrayList<Clip> clips = new ArrayList<>();

    public Sound() {
        soundPath[0] = getClass().getResource("/resources/sound/Fluffing-a-Duck.wav");
        soundPath[1] = getClass().getResource("/resources/sound/Monkeys-Spinning-Monkeys.wav");
        soundPath[2] = getClass().getResource("/resources/sound/unlock.wav");
    }

    public void setFile(int i) {
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(soundPath[i]));
        } catch (Exception e) {
        }
    }

    public void play() {
        clip.setFramePosition(0);
        clip.start();
        clips.add(clip);
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        for(Clip clip : clips) {
            clip.stop();
        }
    }
}
