package src.object;

//external
import java.io.IOException;
import javax.imageio.ImageIO;

//internal
import src.engine.GamePanel;

public class ObjHeart extends SuperObject {
    GamePanel gp;
    
    public ObjHeart(GamePanel gp) {

        this.gp = gp;
        
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/obj_fullheart.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/res/objects/obj_halfheart.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/res/objects/obj_emptyheart.png"));
            image =  uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
            image3 = uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
