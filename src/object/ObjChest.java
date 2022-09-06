package src.object;

//internal

//external
import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjChest extends SuperObject {
    public ObjChest() {
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/obj_chest.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
