package main;

import java.io.*;

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
        BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/resources/saveData/lvl1.txt")));
    }

    public void processLevel2() {

    }

    public void processLevel3() {

    }

    public void saveLevel1() {

    }

    public void saveLevel2() {

    }

    public void saveLevel3() {

    }
}
