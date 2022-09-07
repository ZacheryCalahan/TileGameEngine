package src.object;

//internal

//external
import javax.imageio.ImageIO;

import src.engine.GamePanel;

import java.io.IOException;

public class ObjChest extends SuperObject {
    
    GamePanel gp;
    
    public ObjChest(GamePanel gp) {
        this.gp = gp;

        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/obj_chest.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
