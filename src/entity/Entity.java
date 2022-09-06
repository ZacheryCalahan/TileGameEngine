package src.entity;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Entity {
    
    public int worldX, worldY;
    public int speed;

    public BufferedImage upStill, up1, up2, downStill, down1, down2, left1, leftStill, right1, rightStill;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

}
