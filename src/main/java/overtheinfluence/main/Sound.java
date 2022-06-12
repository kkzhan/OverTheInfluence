package main;

import javax.sound.sampled.*;
import java.net.*;
import java.util.*;

/**
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p>This class is used for implementing sound into the levels.</p>
 *
 * <p>Work Allocation:<ul>
 * <li>Sound class - Kevin Zhan</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */


public class Sound {
    /**
     * the audio file to be played
     */
    Clip clip;
    /**
     * ArrayList storing the paths for the audio file
     */
    ArrayList<URL> soundPath = new ArrayList<>();
    /**
     * an ArrayList to store all clips for access
     */
    ArrayList<Clip> clips = new ArrayList<>();

    /**
     * constructor for the Sound class
     */
    public Sound() {
        soundPath.add(getClass().getResource("/resources/sound/Fluffing-a-Duck.wav"));
        soundPath.add(getClass().getResource("/resources/sound/Monkeys-Spinning-Monkeys.wav"));
        soundPath.add(getClass().getResource("/resources/sound/unlock.wav"));
    }

    /**
     * plays the sound
     *
     * @param i the index for the sound file
     */
    public void setFile(int i) {
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(soundPath.get(i)));
        } catch (Exception e) {
        }
    }

    /**
     * plays the sound clips
     */
    public void play() {
        clip.setFramePosition(0);
        FloatControl fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        fc.setValue(-5.0f);
        clip.start();
        clips.add(clip);
    }

    /**
     * loops the sound clip
     */
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * stops all sound clips
     */
    public void stop() {
        for (Clip clip : clips) {
            clip.stop();
        }
    }
}
