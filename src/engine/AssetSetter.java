package src.engine;

import src.entity.NpcDummy;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        // Declare placement of objs here
    }

    public void setNPC() {
        gp.npc[0] = new NpcDummy(gp);
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 11;
    }
}
