package src.engine;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class UtilityTool {
    public BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();
        
        return scaledImage;
    }

    public int findLastNonNull(final Object[] objects) {
        int i = - 1;
        if(objects != null) {
            for (i = objects.length - 1; i >= 0; i--) {
                if (objects[i] != null) {
                    break;
                }
            }
        }
        return i;
    }

    public String getOppositeDirection(String direction) {
        // Defaults to down when direction is not a direction. 
        switch (direction) {
            case "up":
                return "down";
            case "down":
                return "up";
            case "left":
                return "right";
            case "right":
                return "left";
            default:
                return "down";
        }
    }
}
