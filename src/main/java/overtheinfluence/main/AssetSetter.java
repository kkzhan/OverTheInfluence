package main;

import objects.*;

public class AssetSetter {
    Level lvl;

    public AssetSetter(Level lvl) {
        this.lvl = lvl;
    }

    public void setObject() {
        //add objects
        if(lvl instanceof Exploration) {
            lvl.objects.add(new Door());
            lvl.objects.add(new Door());
            lvl.objects.add(new Door());
            lvl.objects.get(0).worldX = 12 * lvl.tileSize;
            lvl.objects.get(0).worldY = 12 * lvl.tileSize;
            lvl.objects.get(1).worldX = 30 * lvl.tileSize;
            lvl.objects.get(1).worldY = 30 * lvl.tileSize;
            lvl.objects.get(2).worldX = 25 * lvl.tileSize;
            lvl.objects.get(2).worldY = 25 * lvl.tileSize;
        } else if(lvl instanceof Recovery) {

        }
    }
}
