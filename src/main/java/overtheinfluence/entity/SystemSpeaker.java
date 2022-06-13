package entity;

import main.*;

/**
 * <p>The SystemSpeaker class is responsible for "speaking" to the player and transmitting dialogue
 * even when no other characters or objects are being interacted with.</p>
 *
 * <p>Work Allocation:<ul>
 * <li>SystemSpeaker class -Alexander Peng</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */
public class SystemSpeaker extends Entity {

    /**
     * constructor for the SystemSpeaker class
     *
     * @param lvl the level the SystemSpeaker is in
     */
    public SystemSpeaker(Level lvl) {
        super(lvl);
    }

    /**
     * clears the SystemSpeaker's dialogue
     */
    public void clearDialogue() {
        dialogue.clear();
    }

    /**
     * adds a line of dialogue to the SystemSpeaker's dialogue
     *
     * @param dialogue the line of dialogue to add
     */
    public void addDialogue(String dialogue) {
        this.dialogue.add(dialogue);
    }
}
