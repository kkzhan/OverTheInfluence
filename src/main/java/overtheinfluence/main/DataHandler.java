package main;

import java.io.*;
import java.util.Objects;

public class DataHandler {

    Launcher launcher;

    public DataHandler(Launcher launcher) {
        this.launcher = launcher;
    }

    public void processData() {
        processLevel1();
        processLevel2();
        processLevel3();
    }

    public void saveData() {
        saveLevel1();
        saveLevel2();
        saveLevel3();
    }

    public void processLevel1() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/resources/saveData/lvl1.txt"))));
            if (br.readLine().split(" ")[1].equals("true")) {
                launcher.currentGame.started = true;
                launcher.gameStarted = true;
            }
            if (br.readLine().split(" ")[1].equals("true")) {
                launcher.currentGame.levels[0].complete = true;
            }
            launcher.currentGame.levels[0].player.worldX = Integer.parseInt(br.readLine().split(" ")[1]);
            launcher.currentGame.levels[0].player.worldY = Integer.parseInt(br.readLine().split(" ")[1]);
        } catch (Exception e) {
        }
    }

    public void processLevel2() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/resources/saveData/lvl2.txt"))));
            if (br.readLine().split(" ")[1].equals("true")) {
                launcher.currentGame.started = true;
                launcher.gameStarted = true;
                launcher.currentGame.levels[0].complete = true;
            }
            if (br.readLine().split(" ")[1].equals("true")) {
                launcher.currentGame.levels[1].complete = true;
            }
            launcher.currentGame.levels[1].player.worldX = Integer.parseInt(br.readLine().split(" ")[1]);
            launcher.currentGame.levels[1].player.worldY = Integer.parseInt(br.readLine().split(" ")[1]);
        } catch (Exception e) {
        }
    }

    public void processLevel3() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/resources/saveData/lvl3.txt"))));
            if (br.readLine().split(" ")[1].equals("true")) {
                launcher.currentGame.started = true;
                launcher.gameStarted = true;
                launcher.currentGame.levels[0].complete = true;
                launcher.currentGame.levels[1].complete = true;
            }
            if (br.readLine().split(" ")[1].equals("true")) {
                launcher.currentGame.levels[2].complete = true;
            }
            launcher.currentGame.levels[2].player.worldX = Integer.parseInt(br.readLine().split(" ")[1]);
            launcher.currentGame.levels[2].player.worldY = Integer.parseInt(br.readLine().split(" ")[1]);
        } catch (Exception e) {
        }
    }

    public void saveLevel1() {

    }

    public void saveLevel2() {

    }

    public void saveLevel3() {

    }
}
