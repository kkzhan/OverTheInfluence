package main;

import objects.*;

public class AssetSetter {
    public Level lvl;

    public AssetSetter(Level lvl) {
        this.lvl = lvl;
    }

    public void setObject() {
        //add objects
        if(lvl instanceof Exploration) {
            lvl.objects.add(new House(this, 0, 0));
            lvl.objects.get(0).setPosition(0, 0);
            lvl.objects.add(((House) lvl.objects.get(0)).door);
            //lvl.objects.add(((House) lvl.objects.get(0)).door);
        } else if(lvl instanceof Recovery) {

        }
    }
}
