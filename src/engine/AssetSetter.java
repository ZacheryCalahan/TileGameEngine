package src.engine;

import src.object.ObjChest;
import src.object.ObjKey;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new ObjKey();
        gp.obj[0].worldX = 27 * gp.tileSize;
        gp.obj[0].worldY = 8 * gp.tileSize;

        gp.obj[1] = new ObjKey();
        gp.obj[1].worldX = 28 * gp.tileSize;
        gp.obj[1].worldY = 8 * gp.tileSize;

        gp.obj[2] = new ObjChest();
        gp.obj[2].worldX = 29 * gp.tileSize;
        gp.obj[2].worldY = 8 * gp.tileSize;

        gp.obj[3] = new ObjChest();
        gp.obj[3].worldX = 30 * gp.tileSize;
        gp.obj[3].worldY = 8 * gp.tileSize;
    }
}
