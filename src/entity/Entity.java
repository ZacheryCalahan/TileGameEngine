package src.entity;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;

import src.engine.GamePanel;
import src.engine.UtilityTool;


public class Entity {

    GamePanel gp;
    public int worldX, worldY;
    public int speed;
    public BufferedImage upStill, up1, up2, downStill, down1, down2, left1, leftStill, right1, rightStill;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    String dialogues[] = new String[20]; // 20 max dialogues
    int dialogueIndex = 0;
    UtilityTool uTool = new UtilityTool();

    // Character status
    public int maxLife;
    public int life;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {}
    
    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = uTool.findLastNonNull(dialogues);
        }

        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        direction = uTool.getOppositeDirection(gp.player.direction);
    }

    public BufferedImage setup(String imagePath) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/" + imagePath + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void update() {
        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkPlayer(this);

            //if col is false, can move
            if (collisionOn == false) {
                if (direction == "up") {
                    worldY -= speed;
                } else if (direction == "down") {
                    worldY += speed;
                } if (direction == "left") {
                    worldX -= speed;
                } else if (direction == "right") {
                    worldX += speed;
                } else if (direction == "still") {

                }
            }

            // Animation
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

    }

    public void draw(Graphics2D g2) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        // draw current tile if on screen and ready for next tile
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                BufferedImage image = downStill;
                g2.drawImage(image, screenX, screenY, null);
            }
    }

}
