package main;

import java.io.*;
import java.util.*;

public class Question {
    UI ui;
    public ArrayList<String> lines = new ArrayList<>();
    public ArrayList<String> options = new ArrayList<>();
    public int answer;
    public int selected = -1;
    public boolean secondAttempt;
    public boolean complete = false;

    public Question(UI ui, int num) {
        this.ui = ui;
        BufferedReader br = null;
        if (ui.lvl.levelNum == 2) {
            br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/resources/questions/lvl2/question" + num + ".txt")));
        } else if (ui.lvl.levelNum == 3) {
            br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/resources/questions/lvl3/question" + num + ".txt")));
        }
        if (ui.lvl.levelNum == 2 || ui.lvl.levelNum == 3) {
            try {
                String line = br.readLine();
                while (line != null) {
                    lines.add(line);
                    line = br.readLine();
                }
            } catch (IOException e) {
            }
            answer = Integer.parseInt(lines.remove(lines.size() - 1));
            for (int i = 0; i < 6; i++) {
                options.add(lines.remove(lines.size() - 1));
            }
            Collections.reverse(options);
        }
    }
}
