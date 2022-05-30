package main;

import objects.*;

public class AssetSetter {
    Level lvl;

    public AssetSetter(Level lvl) {
        this.lvl = lvl;
    }

    public void setObject() {
        //add objects
        lvl.objects.add(new Door());
        lvl.objects.get(0).worldX = 25 * lvl.tileSize;
        lvl.objects.get(0).worldY = 25 * lvl.tileSize;
    }
}
