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
            //lvl.objects.add();
        } else if(lvl instanceof Recovery) {

        }
    }
}
